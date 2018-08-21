/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.example.jwt.jwt.service;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwt.jwt.model.User;

/**
 * @author MAV81HC
 *
 */
@Service
public class DatabaseSaveService {
  
  @Autowired
  private HashService hashService;
  
  public void createUser(String username, String plainTextPassword) {
    SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
        
    // use bcrypt to encode
    String hashPassword = hashService.hashPassword(plainTextPassword);
    
    User user = new User(username, hashPassword);
        
    try {
      Session session = sessionFactory.openSession();
      
      session.beginTransaction();

      session.save(user);
            
      session.getTransaction().commit(); // push code
      
    } catch(Exception e) {
      System.out.println("Exception " + e);
    }
  }
  
  public boolean checkAuth(String username, String plainTextPassword) {
    SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
            
    boolean result = false;
    
    try {
      Session session = sessionFactory.openSession();
      
      session.beginTransaction();

      String queryStr = "From User WHERE username like :username";
      
      Query query = session.createQuery(queryStr);
            
      query.setParameter("username", username);
      
      List<User> users = (List<User>) query.getResultList();
      
      String databasePassword = users.get(0).getPassword();
      
      result = hashService.checkPass(plainTextPassword, databasePassword);
      
      session.getTransaction().commit(); // push code
      
    } catch(Exception e) {
      System.out.println("Exception " + e);
    }
    
    return result;
  }
}
