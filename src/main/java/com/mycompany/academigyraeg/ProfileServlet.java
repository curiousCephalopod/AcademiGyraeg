/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.academigyraeg;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
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

        String userType = (String) session.getAttribute("userType");
        // UserType 0 Student
        if (userType.equals("0")) {
            // Connect to the database
            try (Connection conn = SimpleDataSource.getConnection()) {

                PreparedStatement ps = conn.prepareStatement("SELECT quizType, result, outOf, dateTaken FROM results WHERE username = ?");
                ps.setString(1, (String) session.getAttribute("user"));

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    // For each retrieved record, transcribe the details to the array
                    String[] result = new String[4];

                    switch (rs.getString("quizType").charAt(0)) {
                        case 'e':
                            result[0] = "Welsh to English";
                            break;
                        case 'w':
                            result[0] = "English to Welsh";
                            break;
                        case 'g':
                            result[0] = "Gender of Welsh";
                            break;
                    }
                    result[1] = rs.getString("result");
                    result[2] = rs.getString("outOf");
                    result[3] = rs.getString("dateTaken");

                    results.add(result);
                }

                session.setAttribute("results", results);

            } catch (SQLException ex) {
                Logger.getLogger(LoginValidate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher rs = request.getRequestDispatcher("ViewProfile.jsp");
        rs.forward(request, response);
    }

}
