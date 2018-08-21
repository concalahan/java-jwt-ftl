package com.example.jwt.jwt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int ID;
    
    @Column
	private String username;
    
    @Column
    private String password;
    
	public User() {
		super();
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
  
    /**
     * @return the iD
     */
    public int getID() {
      return ID;
    }
    
    /**
     * @param iD the iD to set
     */
    public void setID(int iD) {
      ID = iD;
    }
	
	
}
