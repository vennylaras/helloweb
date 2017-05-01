/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Venny
 */
public class User {
    private int id;
    private String username;
    private String first_name;
    private String last_name;
    private String password;
    
    public User(int id, String username, String first_name, String last_name, String password) {
        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
    }
    
    public int getKey() {
        return this.id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getFirstName() {
        return this.first_name;
    }
    
    public String getLastName() {
        return this.last_name;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
    
    public void setPassword (String password) {
        this.password = password;
    }
}
