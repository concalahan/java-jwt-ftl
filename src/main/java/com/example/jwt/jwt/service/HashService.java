/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.example.jwt.jwt.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * @author MAV81HC
 *
 */
@Service
public class HashService {
  public String hashPassword(String plainTextPassword){
    return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
  }
  
  public boolean checkPass(String plainPassword, String hashedPassword) {
    if (BCrypt.checkpw(plainPassword, hashedPassword)) {
      System.out.println("The password matches.");
      return true;
    } else {
      System.out.println("The password does not match.");
      return false;
    }
  }
}
