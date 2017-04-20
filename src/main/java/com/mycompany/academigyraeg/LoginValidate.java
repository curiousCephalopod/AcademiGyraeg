/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.academigyraeg;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josh
 */
public class LoginValidate {
    
    
    public static int validateUser(String username, String pass){
        // Retrieve the properties
        InputStream stream = LoginValidate.class.getResourceAsStream("/database.properties");
        
        try {
            // Initialise the data source using the properties
            SimpleDataSource.init(stream);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LoginValidate.class.getName()).log(Level.SEVERE, "Malformed Properties File", ex);
        }
        
        int userType = 3;
        try (Connection conn = SimpleDataSource.getConnection()){
           
           PreparedStatement ps = conn.prepareStatement("SELECT * FROM login WHERE username = ? AND password = ?");
           ps.setString(1, username);
           ps.setString(2, pass);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               // User exists
               userType = rs.getInt("userType");
           }

        } catch (SQLException ex) {
            Logger.getLogger(LoginValidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userType;
    }
}
