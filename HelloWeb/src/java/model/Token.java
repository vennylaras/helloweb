/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Venny
 */
public class Token {
    private int id;
    private int user_id;
    private String token;
    private Date expired_at;
    
    public Token(int id, int user_id, String token, Date expired_at) {
        this.id = id;
        this.user_id = user_id;
        this.token = token;
        this.expired_at = expired_at;
    }
    
    public int getKey() {
        return this.id;
    }
    
    public int getUserId() {
        return this.user_id;
    }
    
    public String getToken() {
        return this.token;
    }
    
    public Date getExpiredAt() {
        return this.expired_at;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public void setExpiredAt(Date expired_at) {
        this.expired_at = expired_at;
    }
}
