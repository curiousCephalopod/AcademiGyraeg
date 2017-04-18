/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.academigyraeg;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // Set response type
        response.setContentType("text/html");
        // Get the http writer
        PrintWriter out = response.getWriter();
        
        // Retrieve the username parameter
        String username = request.getParameter("username");
        String text;
        // Form the return text
        if(username.isEmpty())
            text = "Please enter valid credentials.";
        else
            text = "Hi user " + username + ". You are now logged into the system.";
        
        // Form the HTML script
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>" + text + "</h1>");
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }
}
