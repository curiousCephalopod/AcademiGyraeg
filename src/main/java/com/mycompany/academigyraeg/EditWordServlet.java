/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.academigyraeg;

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
        
        if(session == null || session.getAttribute("user") == null){
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
        
        String english;
        String welsh;
        String type;
        String gender;
        
        
        if(request.getParameter("url").equals("dictionary")){
            // From dictionary
            String wordID = (String)request.getParameter("wordID");
            
            if(request.getParameter("submit").equals("Delete")){
                if(!wordID.equals("new")){
                    try (Connection conn = SimpleDataSource.getConnection()){
                        PreparedStatement ps = conn.prepareStatement("DELETE FROM words WHERE wordID = ?");
                        
                        ps.setString(1, wordID);
                        ps.executeUpdate();
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(EditWordServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                RequestDispatcher rs = request.getRequestDispatcher("DictionaryServlet");
                rs.forward(request, response);
            }else{
            
            if(wordID.equals("new")){
                // New word
                english = "english";
                welsh = "welsh";
                type = "adjective";
                gender = "male";
            }else{
                // Reuse the dictionary
                ArrayList words = (ArrayList)session.getAttribute("words");
                // Search the dictionary for the ID
                String[] target = null;
                for(Object word : words){
                    String[] wordArray = (String[])word;
                    if(wordArray[0].equals(wordID))
                        target = wordArray;
                }
                
                english = target[1];
                welsh = target[2];
                type = target[3];
                gender = target[4];
            }
            
            session.setAttribute("wordID", wordID);
            session.setAttribute("wordEnglish", english);
            session.setAttribute("wordWelsh", welsh);
            System.out.println("Wordtype "+ type);
            session.setAttribute("wordType", type);
            System.out.println("gender " + gender);
            session.setAttribute("wordGender", gender);
            
            RequestDispatcher rs = request.getRequestDispatcher("EditWord.jsp");
            rs.forward(request, response);
            }
        }else{
            // From edit menu
            
            String wordID = (String)session.getAttribute("wordID");
            english = (String)request.getParameter("wordEnglish");
            welsh = (String)request.getParameter("wordWelsh");
            type = (String)request.getParameter("wordType");
            gender = (String)request.getParameter("wordGender");
            
            try (Connection conn = SimpleDataSource.getConnection()){
                if(wordID.equals("new")){
                    // New word
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO words(english, welsh, wordType, wordGender) VALUES (?,?,?,?)");

                    ps.setString(1, english);
                    ps.setString(2, welsh);
                    ps.setString(3, type);
                    ps.setString(4, gender.substring(0, 0));
                    ps.executeUpdate();
                    ps.close();
                }else{
                    // Existing word
                    PreparedStatement ps = conn.prepareStatement("UPDATE words SET english = ?, welsh = ?, wordType = ?, wordGender = ? WHERE wordID = ?");
                    System.out.println(english);
                    ps.setString(1, english);
                    System.out.println(welsh);
                    ps.setString(2, welsh);
                    System.out.println(type);
                    ps.setString(3, type);
                    System.out.println(gender);
                    System.out.println(gender.substring(0, 1));
                    ps.setString(4, gender.substring(0, 1));
                    System.out.println(wordID);
                    ps.setString(5, wordID);
                    ps.executeUpdate();
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditWordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            RequestDispatcher rs = request.getRequestDispatcher("DictionaryServlet");
            rs.forward(request, response);
        }
    }

}
