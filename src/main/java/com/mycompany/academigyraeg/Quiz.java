/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.academigyraeg;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ed
 */
public class Quiz {

    InputStream stream = Quiz.class.getResourceAsStream("/database.properties");
    
    
        
    
    int outOf = 20; //total words of quiz
    int currentWord = 0; //current word incrementer
    String username; //user working current test
    int wordIndex[] = new int[outOf]; //index of ID's for the quiz
    Random rand = new Random();
    int noWords = 0; //words in DB
    int score;
    char type;
    
    
    //SQL statement list
    String getRandID = "SELECT wordID FROM words ORDER BY RAND() LIMIT 1";
    String wordGet = "SELECT * FROM words WHERE wordID = ?";
    String resultStore = "INSERT INTO results(username, quizType, result, outOf, dateTaken) VALUES (?, ?, ?, ?, now())";
    String resultCheck = "SELECT ? FROM words WHERE wordID = ?";
    
    /**
     * initialise prepared statements
     * initialise random words from database
     * @param username user running quiz
     * @param Quiztype type of quiz running
     */
    public Quiz(String username, char Quiztype)
    {
        
        try {
            SimpleDataSource.init(stream);
        } catch (IOException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.username = username;
        this.type = Quiztype;
        
        try (Connection conn = SimpleDataSource.getConnection()){
            Statement st = conn.createStatement();
            
            //fill array with random wordID's from DB
            for(int i=0;i<outOf;i++)
            {
                ResultSet res = st.executeQuery(getRandID);
                res.next();
                wordIndex[i] = res.getInt("wordID");
            }
            
            st.close();
        }
        catch (SQLException exception)
        {
            System.out.println("DB error(constructor)");
        }
    }
    
    
    /**
     * e = english of welsh noun
     * w = welsh of english noun
     * g = gender of welsh noun
     * ** ^ words to display on question
     * @return array of words to display in quiz
     */
    public String getCurrentWord(){
        String output = "";
        String column;
        //set column name based on type of quiz running
        switch(type){
            case 'e':
                column = "welsh";
                break;
            case 'w':
                column = "english";
                break;
            case 'g':
                column = "welsh";
                break;
            default:
                column = "welsh";
                System.out.println("error");
                break;
        }
        try (Connection conn = SimpleDataSource.getConnection()){
            // Form query
            PreparedStatement getWordPart = conn.prepareStatement(wordGet);
            getWordPart.setInt(1, wordIndex[currentWord]);
            // Execute query
            ResultSet rs = getWordPart.executeQuery();
            // Retrieve required word
            rs.next();
            output = rs.getString(column);
            getWordPart.close();
        }catch(SQLException exception){
            System.out.println("SQL error(getCurrentWord)");
        }

        return output;
    }
    
    /**
     * Enter solution to the made quiz, to be run sequentially
     * e = english of welsh noun
     * w = welsh of english noun
     * g = gender of welsh noun
     * ** ^ words to compare against for answers
     * @param solution user entered solution
     */
    public boolean solve(String solution)
    {
        //check if running for question that does not exist
        if (currentWord < outOf)
        {
            String column = "";
            String input;
            //set column name based on type of quiz running
            switch(type)
            {
                case 'e':
                    column = "english";
                    break;

                case 'w':
                    column = "welsh";
                    break;
                case 'g':
                    column = "gender";
                default:
                    System.out.println("error");
            }
            try (Connection conn = SimpleDataSource.getConnection()){
                PreparedStatement getWordPart = conn.prepareStatement(wordGet);
                getWordPart.setInt(1,wordIndex[currentWord]);
                ResultSet rs = getWordPart.executeQuery();
                rs.next();
                //check user input against required column of current word
                if(solution.equals(rs.getString(column)))
                {
                    //if true increment score and current word index
                    score++;
                    currentWord++;
                    return true;
                }
                else
                {
                    currentWord++;
                    return false;
                }
            }
            catch(SQLException exception)
            {
                System.out.println("answer retrieval error");
                return false;
            }
        }
        else
        {
            System.out.println("too many solutions");
            return false;
        }
    }
    
    
    /**
     * store final data of quiz into database
     * @return successful store or not
     */
    public boolean storeResult()
    {
        
        try (Connection conn = SimpleDataSource.getConnection()){
            PreparedStatement storeResult = conn.prepareStatement(resultStore);
            //set data from current quiz
            storeResult.setString(1, username);
            storeResult.setString(2, String.valueOf(type));
            storeResult.setInt(3, score);
            storeResult.setInt(4, outOf);
            //submit result to DB
            storeResult.executeUpdate();
            storeResult.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
}
