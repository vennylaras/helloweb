/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Venny
 */
public class DatabaseConnector {
    // JDBC driver name and database URL
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/hello_web";
    
    // Database credentials
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "";
    
    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            // Establish connection to db
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
