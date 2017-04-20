/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.academigyraeg;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Josh
 */
public class LoginServlet extends HttpServlet {

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
        // Retrieve the entered username and password
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        // Retrieve the current session, or create a new one
        HttpSession session = request.getSession();

        // Determine if the user exists, and it's type
        int userType = LoginValidate.validateUser(user, pass);

        // If the user doe exist
        if (userType != 3) {
            // Return to the index
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");

            // Save the user details into the session
            session.setAttribute("user", user);
            session.setAttribute("userType", userType);
            session.setAttribute("message", "User " + user + " logged in.");

            rs.forward(request, response);
        } else {
            // If the user doesn't exist
            // Send a message
            session.setAttribute("message", "Invalid username or password.");

            // Return to the index
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.include(request, response);
        }
    }
}
