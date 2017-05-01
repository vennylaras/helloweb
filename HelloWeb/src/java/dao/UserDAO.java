/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Venny
 */
public class UserDAO {
    private Connection conn = DatabaseConnector.connect();
    private User user;
    
    public User find(int id) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(query);
            dbStatement.setInt(1, id);
            ResultSet rs = dbStatement.executeQuery();
            if(rs.next()) {
                String username = rs.getString("username");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String password = rs.getString("password");
                user = new User(id, username, first_name, last_name, password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.user = user;
        return user;
    }
    
    public User find(String username) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement dbStatement = conn.prepareStatement(query);
            dbStatement.setString(1, username);
            ResultSet rs = dbStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String password = rs.getString("password");
                user = new User(id, username, first_name, last_name, password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.user = user;
        return user;
    }
}
