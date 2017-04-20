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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eeu67d
 */
public class DictionaryServlet extends HttpServlet {

    /**
     * Dictionary can be accessed via POST or GET, so use a helper method.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the session, if it exists
        HttpSession session = request.getSession(false);

        // Check if a user is logged in, otherwise redirect
        if (session == null || session.getAttribute("user") == null) {
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request, response);
            return;
        }

        // Form data results
        ArrayList words = new ArrayList<>();

        // Initialise the data source in case it wasn't already
        InputStream stream = LoginValidate.class.getResourceAsStream("/database.properties");
        try {
            // Initialise the data source using the properties
            SimpleDataSource.init(stream);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LoginValidate.class.getName()).log(Level.SEVERE, "Malformed Properties File", ex);
        }

        // Connect to the database
        try (Connection conn = SimpleDataSource.getConnection()) {
            
            // Form the statement to retrieve words
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM words");
            
            // Execute the query
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // For each retrieved record, transcribe the details to the array
                String[] word = new String[5];

                word[0] = rs.getString("wordID");
                word[1] = rs.getString("english");
                word[2] = rs.getString("welsh");
                word[3] = rs.getString("wordType");
                // Replace the gender character with a string
                switch (rs.getString("wordGender")) {
                    case "f":
                        word[4] = "female";
                        break;
                    case "m":
                        word[4] = "male";
                        break;
                    default:
                        word[4] = "male";
                        break;
                }

                words.add(word);
            }
            // Close the statement
            ps.close();

            // Store the words
            session.setAttribute("words", words);
        } catch (SQLException ex) {
            Logger.getLogger(LoginValidate.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Redirect to the dictionary jsp
        RequestDispatcher rs = request.getRequestDispatcher("EditDict.jsp");
        rs.forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

}
