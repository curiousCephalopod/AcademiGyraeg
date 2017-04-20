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
public class ProfileServlet extends HttpServlet {

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
        // If a session exists, retrieve it
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            // If the session doesn't exist, or the user is not logged in
            // return to the index
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request, response);
            return;
        }

        // Form data results
        ArrayList results = new ArrayList<>();

        // Initialise the data source in case it wasn't already
        InputStream stream = LoginValidate.class.getResourceAsStream("/database.properties");
        try {
            // Initialise the data source using the properties
            SimpleDataSource.init(stream);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LoginValidate.class.getName()).log(Level.SEVERE, "Malformed Properties File", ex);
        }

        // Check search term
        String username;
        if ((String) request.getParameter("searchUser") != null) {
            username = (String) request.getParameter("searchUser");
        } else {
            // No search term
            username = "";
        }
        // Store the term to redisplay it
        session.setAttribute("searchUser", username);

        // Retrieve the user type
        int userType = (int) session.getAttribute("userType");
        // Username-based search (Student or search term)
        if ((userType == 0) || !username.isEmpty()) {
            // Connect to the database
            try (Connection conn = SimpleDataSource.getConnection()) {

                // Prepare the statement
                PreparedStatement ps = conn.prepareStatement("SELECT username, quizType, result, outOf, dateTaken FROM results WHERE username = ?");
                // If it's a student
                if (userType == 0) {
                    // Use their own username
                    ps.setString(1, (String) session.getAttribute("user"));
                } else {
                    // Otherwise use the search term
                    ps.setString(1, username);
                }
                
                // Execute the search
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    // For each retrieved record, transcribe the details to the array
                    String[] result = new String[5];

                    result[0] = rs.getString("username");
                    // Translate quiz type into text
                    switch (rs.getString("quizType").charAt(0)) {
                        case 'e':
                            result[1] = "Welsh to English";
                            break;
                        case 'w':
                            result[1] = "English to Welsh";
                            break;
                        case 'g':
                            result[1] = "Gender of Welsh";
                            break;
                    }
                    result[2] = rs.getString("result");
                    result[3] = rs.getString("outOf");
                    result[4] = rs.getString("dateTaken");

                    // Store the result
                    results.add(result);
                }

                // Store the results table for viewing
                session.setAttribute("results", results);
            } catch (SQLException ex) {
                Logger.getLogger(LoginValidate.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Instructor or Admin (no search term)

            // Display all results
            // Connect to the database
            try (Connection conn = SimpleDataSource.getConnection()) {
                // Prepare the statement
                PreparedStatement ps = conn.prepareStatement("SELECT username, quizType, result, outOf, dateTaken FROM results");

                // Execute the search
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    // For each retrieved record, transcribe the details to the array
                    String[] result = new String[5];

                    result[0] = rs.getString("username");
                    // Translate the quiz type to a string
                    switch (rs.getString("quizType").charAt(0)) {
                        case 'e':
                            result[1] = "Welsh to English";
                            break;
                        case 'w':
                            result[1] = "English to Welsh";
                            break;
                        case 'g':
                            result[1] = "Gender of Welsh";
                            break;
                    }
                    result[2] = rs.getString("result");
                    result[3] = rs.getString("outOf");
                    result[4] = rs.getString("dateTaken");

                    // Store the result
                    results.add(result);
                }

                // Save the table into the session for viewing
                session.setAttribute("results", results);
            } catch (SQLException ex) {
                Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Redirect to the viewing profile
        RequestDispatcher rs = request.getRequestDispatcher("ViewProfile.jsp");
        rs.forward(request, response);
    }

}
