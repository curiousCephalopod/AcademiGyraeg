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
public class RegisterServlet extends HttpServlet {

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
        // Retrieve a session if it exists
        HttpSession session = request.getSession(false);

        // Check if a user is logged in, otherwise redirect to index
        if (session == null || session.getAttribute("user") == null) {
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request, response);
            return;
        }

        // Retrieve the new user details
        String user = request.getParameter("newUser");
        String pass = request.getParameter("newPass");
        String type = request.getParameter("type");

        // If username and password is valid
        if (LoginValidate.validateUsername(user) && LoginValidate.validatePassword(pass)) {
            // Create a user of specified type
            LoginValidate.createUser(user, pass, type);
        }

        // Set a confirmation message
        session.setAttribute("message", "New user, " + user + ", has been added.");

        // Redirect back to the index
        RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
        rs.forward(request, response);
    }
}
