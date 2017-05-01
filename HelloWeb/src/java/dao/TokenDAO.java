/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.DatabaseConnector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Token;

/**
 *
 * @author Venny
 */
public class TokenDAO {
    private Connection conn = DatabaseConnector.connect();
    private Token token;
    
    public Token getToken() {
        return this.token;
    }
    
    public Token find(String tokenkey) {
        Token token = null;
        try {
            String query = "SELECT * FROM tokens WHERE token = ?";
            PreparedStatement dbStatement = conn.prepareStatement(query);
            dbStatement.setString(1, tokenkey);
            ResultSet rs = dbStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                Date expired_at =rs.getDate("expired_at");
                token = new Token(id, user_id, tokenkey, expired_at);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TokenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.token = token;
        return token;
    }
    
    public Token find(int user_id) {
        Token token = null;
        try {
            String query = "SELECT * FROM tokens WHERE user_id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(query);
            dbStatement.setInt(1, user_id);
            ResultSet rs = dbStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                String tokenkey = rs.getString("token");
                Date expired_at =rs.getDate("expired_at");
                token = new Token(id, user_id, tokenkey, expired_at);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TokenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.token = token;
        return token;
    }
    
    public void create(int user_id) throws SQLException {
        String tokenkey = UUID.randomUUID().toString().replaceAll("-","");
        java.util.Date date = new java.util.Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 30);
        date = cal.getTime();
                
        String query = "INSERT INTO tokens(user_id, token, expired_at) VALUES(?,?,?)";
        PreparedStatement dbStatement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        dbStatement.setInt(1, user_id);
        dbStatement.setString(2, tokenkey);
        dbStatement.setTimestamp(3, new Timestamp(date.getTime()));  
        int id = dbStatement.executeUpdate();
        dbStatement.close();
        
        this.token = new Token(id, user_id, tokenkey, date);
    }
    
    public void delete(int user_id) throws SQLException {
        String query = "DELETE FROM tokens WHERE user_id = ?";
        PreparedStatement dbStatement = conn.prepareStatement(query);
        dbStatement.setInt(1, user_id);
        dbStatement.executeUpdate();
        dbStatement.close();
        this.token = null;
    }
}
