/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.example.jwt.jwt.service;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.example.jwt.jwt.model.User;

/**
 * @author MAV81HC
 *
 */
public class ConnectionUtil {
  private static SessionFactory sessionFactory = null;
  
  public static SessionFactory getSessionFactory() {
    if(sessionFactory == null) {
      Configuration configuration = new Configuration();
      
      System.out.println("Create sessionFactory!");
      
      // change file name configure here
      configuration.configure();
      configuration.addAnnotatedClass(User.class);
      
      ServiceRegistry registry = new StandardServiceRegistryBuilder()
          .applySettings(configuration.getProperties()).build();
      
      // only one sessionFactory
      sessionFactory = configuration.buildSessionFactory(registry);
    }
    
    return sessionFactory;
  }
}