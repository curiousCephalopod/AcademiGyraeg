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
 * @author eeu67d
 */
public class QuizServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        
        Quiz quiz = (Quiz)session.getAttribute("quizObject");
        if(quiz == null){
            // First run
            
            char quizType = request.getParameter("quiz").charAt(0);
            String quizName;
            String firstLabel;
            String secondLabel;
            switch(quizType){
                case 'g':
                    quizName = "Gender of Welsh Word";
                    firstLabel = "Welsh Word";
                    secondLabel = "Gender";
                    break;
                case 'e':
                    quizName = "Welsh to English";
                    firstLabel = "Welsh Word";
                    secondLabel = "English Word";
                    break;
                case 'w':
                    quizName = "English to Welsh";
                    firstLabel = "English Word";
                    secondLabel = "Welsh Word";
                    break;
                default:
                    quizType = 'g';
                    quizName = "Gender of Welsh Word";
                    firstLabel = "Welsh Word";
                    secondLabel = "Gender";
                    break;
            }

            session.setAttribute("quizName", quizName);
            session.setAttribute("firstLabel", firstLabel);
            session.setAttribute("secondLabel", secondLabel);
            
            quiz = new Quiz((String)session.getAttribute("user"), quizType);
        }else{
            // Next question
            String answer = request.getParameter("answer");
            quiz.solve(answer);
        }
        
        session.setAttribute("question", quiz.getCurrentWord());
        
        RequestDispatcher rs = request.getRequestDispatcher("QuizView.jsp");

        rs.include(request, response);
    }

}
