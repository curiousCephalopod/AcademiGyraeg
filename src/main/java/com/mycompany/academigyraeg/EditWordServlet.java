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
public class EditWordServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if a user is logged in, otherwise redirect
        if (session == null || session.getAttribute("user") == null) {
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request, response);
            return;
        }

        // Initialise the data source in case it wasn't already
        InputStream stream = LoginValidate.class.getResourceAsStream("/database.properties");
        try {
            // Initialise the data source using the properties
            SimpleDataSource.init(stream);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LoginValidate.class.getName()).log(Level.SEVERE, "Malformed Properties File", ex);
        }

        // Initialise descriptors for the word
        String english;
        String welsh;
        String type;
        String gender;

        if (request.getParameter("url").equals("dictionary")) {
            // From dictionary
            // Retrieve the wordID
            String wordID = (String) request.getParameter("wordID");

            // If we want to delete a word
            if (request.getParameter("submit").equals("Delete")) {
                // Ensure a word is selected
                if (!wordID.equals("new")) {
                    try (Connection conn = SimpleDataSource.getConnection()) {
                        // Prepare the delete statement
                        PreparedStatement ps = conn.prepareStatement("DELETE FROM words WHERE wordID = ?");

                        // Set and execute the statement
                        ps.setString(1, wordID);
                        ps.executeUpdate();
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(EditWordServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                // Redirect back to the dictionary
                RequestDispatcher rs = request.getRequestDispatcher("DictionaryServlet");
                rs.forward(request, response);
            } else {
                // Adding a new word
                if (wordID.equals("new")) {
                    // Prepare default values to avoid nulls
                    english = "english";
                    welsh = "welsh";
                    type = "adjective";
                    gender = "male";
                } else {
                    // Reuse the dictionary
                    ArrayList words = (ArrayList) session.getAttribute("words");
                    // Search the dictionary for the ID
                    String[] target = null;
                    for (Object word : words) {
                        // Convert the object
                        String[] wordArray = (String[]) word;
                        // If it is the desired word
                        if (wordArray[0].equals(wordID)) {
                            target = wordArray;
                        }
                    }

                    // Record the current values
                    english = target[1];
                    welsh = target[2];
                    type = target[3];
                    gender = target[4];
                }

                // Set the values into the session for use
                session.setAttribute("wordID", wordID);
                session.setAttribute("wordEnglish", english);
                session.setAttribute("wordWelsh", welsh);
                session.setAttribute("wordType", type);
                session.setAttribute("wordGender", gender);

                // Redirect to the edit menu
                RequestDispatcher rs = request.getRequestDispatcher("EditWord.jsp");
                rs.forward(request, response);
            }
        } else {
            // From edit menu
            // Retrieve the values from session and request
            String wordID = (String) session.getAttribute("wordID");
            english = (String) request.getParameter("wordEnglish");
            welsh = (String) request.getParameter("wordWelsh");
            type = (String) request.getParameter("wordType");
            gender = (String) request.getParameter("wordGender");

            try (Connection conn = SimpleDataSource.getConnection()) {
                // Adding a new word
                if (wordID.equals("new")) {
                    // Prepare the statement
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO words(english, welsh, wordType, wordGender) VALUES (?,?,?,?)");

                    // Set the values
                    ps.setString(1, english);
                    ps.setString(2, welsh);
                    ps.setString(3, type);
                    // Substring gender to obtain f or m
                    ps.setString(4, gender.substring(0, 1));
                    // Execute and close the statement
                    ps.executeUpdate();
                    ps.close();
                } else {
                    // Change an existing word
                    PreparedStatement ps = conn.prepareStatement("UPDATE words SET english = ?, welsh = ?, wordType = ?, wordGender = ? WHERE wordID = ?");

                    // Set the values
                    ps.setString(1, english);
                    ps.setString(2, welsh);
                    ps.setString(3, type);
                    // Substring the gender to obtain f or m
                    ps.setString(4, gender.substring(0, 1));
                    ps.setString(5, wordID);
                    // Execute and close the statement
                    ps.executeUpdate();
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditWordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Redirect back to the dictionary
            RequestDispatcher rs = request.getRequestDispatcher("DictionaryServlet");
            rs.forward(request, response);
        }
    }
}
