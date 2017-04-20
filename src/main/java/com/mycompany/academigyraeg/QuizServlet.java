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
        // If a session exists, retrieve it
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            // If the session doesn't exist, or the user is not logged in
            // return to the index
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.forward(request, response);
            return;
        }

        // If possible, retrieve the current running quiz
        Quiz quiz = (Quiz) session.getAttribute("quizObject");
        if (request.getParameter("url").equals("menu")) {
            // First question / from menu

            // Convert the type string to a character
            char quizType = request.getParameter("quiz").charAt(0);
            // Question details
            String quizName;
            String firstLabel;
            String secondLabel;

            // Form the labels based on the current quiz
            switch (quizType) {
                case 'g':
                    quizName = "Gender of Welsh Word";
                    firstLabel = "Welsh Word";
                    secondLabel = "Gender (f/m)";
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
                    secondLabel = "Gender (f/m)";
                    break;
            }

            // Set all of the labels
            session.setAttribute("quizName", quizName);
            session.setAttribute("firstLabel", firstLabel);
            session.setAttribute("secondLabel", secondLabel);
            session.setAttribute("answer", "");

            // Form a new quiz
            quiz = new Quiz((String) session.getAttribute("user"), quizType);
            // Save the quiz details
            session.setAttribute("outOf", quiz.getOutOf());
            session.setAttribute("quizObject", quiz);
        } else {
            // Next question
            // Retrieve the answer and test it
            String answer = request.getParameter("answer");
            session.setAttribute("answer", quiz.solve(answer));
        }

        // Save the current question number
        session.setAttribute("current", quiz.getCurrentWordNo());

        RequestDispatcher rs;
        if (quiz.getCurrentWordNo() == (quiz.getOutOf() - 1)) {
            // If we are at the end of the quiz
            // Save the score to session and database
            session.setAttribute("score", quiz.getScore());
            quiz.storeResult();
            // Redirect to the results pages
            rs = request.getRequestDispatcher("QuizResult.jsp");
        } else {
            // Retrieve the next question and reload the page
            session.setAttribute("question", quiz.getCurrentWord());
            rs = request.getRequestDispatcher("QuizView.jsp");
        }

        // Redirect
        rs.include(request, response);
    }

}
