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

    /**
     * Check user credentials against the database.
     * @param username Username to check
     * @param pass Password to check
     * @return User's type
     */
    public static int validateUser(String username, String pass) {
        // Retrieve the properties
        InputStream stream = LoginValidate.class.getResourceAsStream("/database.properties");

        try {
            // Initialise the data source using the properties
            SimpleDataSource.init(stream);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LoginValidate.class.getName()).log(Level.SEVERE, "Malformed Properties File", ex);
        }

        // Default to user type 3 (no user)
        int userType = 3;
        // Connect to the database
        try (Connection conn = SimpleDataSource.getConnection()) {

            // Form the statement
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM login WHERE username = ? AND password = ?");
            // Enter user details
            ps.setString(1, username);
            ps.setString(2, pass);
            // Search for the user
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // User exists, save it's type
                userType = rs.getInt("userType");
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginValidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return the user type
        return userType;
    }

    /**
     * Create a new user in the database.
     * @param username Username to add
     * @param password Password to add
     * @param userType User type to add
     */
    public static void createUser(String username, String password, String userType) {
        // Retrieve the properties
        InputStream stream = LoginValidate.class.getResourceAsStream("/database.properties");

        try {
            // Initialise the data source using the properties
            SimpleDataSource.init(stream);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LoginValidate.class.getName()).log(Level.SEVERE, "Malformed Properties File", ex);
        }

        // Connect to the database
        try (Connection conn = SimpleDataSource.getConnection()) {
            // Form the statement
            PreparedStatement ps = conn.prepareStatement("INSERT INTO login(username, password, userType) VALUES (?, ?, ?)");
            // Set the credentials
            ps.setString(1, username);
            ps.setString(2, password);
            // Parse user type as an integer
            ps.setInt(3, Integer.parseInt(userType));
            // Add user and close the statement
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(LoginValidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Validate a username.
     * @param username username to validate
     * @return 
     */
    public static boolean validateUsername(String username) {
        return username.length() <= 56;
    }

    /**
     * Validate a password.
     * @param password password to validate
     * @return 
     */
    public static boolean validatePassword(String password) {
        return password.length() <= 256;
    }
}
