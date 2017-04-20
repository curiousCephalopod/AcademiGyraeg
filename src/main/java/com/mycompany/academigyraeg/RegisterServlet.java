/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.academigyraeg;

import java.io.IOException;
import java.io.PrintWriter;
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
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        // If username and password is valid
        if(LoginValidate.validateUsername(user) && LoginValidate.validatePassword(pass)){
            // Create a user of type 0
            LoginValidate.createUser(user, pass, 0);
        }
        
        HttpSession session = request.getSession();
        
        session.setAttribute("user", user);
        session.setAttribute("userType", 0);
        session.setAttribute("message", "User " + user + " logged in.");
        
        RequestDispatcher rs = request.getRequestDispatcher("QuizMenu.jsp");
        rs.forward(request, response);
    }
}
