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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        HttpSession session = request.getSession();
        
        int userType = LoginValidate.validateUser(user, pass);
        
        if(userType != 3){
            RequestDispatcher rs = request.getRequestDispatcher("QuizMenu.jsp");
            
            session.setAttribute("user", user);
            session.setAttribute("userType", userType);
            session.setAttribute("message", "User " + user + " logged in.");
            
            rs.forward(request, response);
        }else{
            session.setAttribute("message", "Invalid username or password.");
            
            session.invalidate();
            
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.include(request, response);
        }
        
    }

}
