/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.academigyraeg;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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

    // Properties stream
    private InputStream stream;

    // Quiz parameters
    private int outOf;
    private int currentWord;
    private int score;
    private char type;

    private String username;
    private int wordIndex[];

    // SQL Statements
    String getRandID = "SELECT wordID FROM words ORDER BY RAND() LIMIT 1";
    String wordGet = "SELECT * FROM words WHERE wordID = ?";
    String resultStore = "INSERT INTO results(username, quizType, result, outOf, dateTaken) VALUES (?, ?, ?, ?, now())";

    /**
     * Initialise prepared statements initialise random words from database.
     *
     * @param username user running quiz
     * @param quizType type of quiz running
     */
    public Quiz(String username, char quizType) {
        // Set defaults
        this.outOf = 20;
        this.wordIndex = new int[outOf];
        this.currentWord = 0;
        this.stream = Quiz.class.getResourceAsStream("/database.properties");

        // Retrieve parameters
        this.username = username;
        type = quizType;

        try {
            SimpleDataSource.init(stream);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (Connection conn = SimpleDataSource.getConnection()) {
            // Create the statement
            Statement st = conn.createStatement();

            // Fill array with random wordID's from DB
            for (int i = 0; i < outOf; i++) {
                ResultSet res = st.executeQuery(getRandID);
                res.next();
                wordIndex[i] = res.getInt("wordID");
            }

            // Close the statement
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the outOf
     */
    public int getOutOf() {
        return outOf;
    }

    /**
     * @return the currentWord
     */
    public int getCurrentWordNo() {
        return currentWord;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * e = english of welsh noun w = welsh of english noun g = gender of welsh
     * noun ** ^ words to display on question
     *
     * @return array of words to display in quiz
     */
    public String getCurrentWord() {
        String output = "";
        String column;
        // Set column name based on type of quiz running
        switch (type) {
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

        // Connect to the database
        try (Connection conn = SimpleDataSource.getConnection()) {
            // Form query
            PreparedStatement getWordPart = conn.prepareStatement(wordGet);
            getWordPart.setInt(1, wordIndex[getCurrentWordNo()]);
            // Execute query
            ResultSet rs = getWordPart.executeQuery();
            // Retrieve required word
            rs.next();
            output = rs.getString(column);

            // Close the statement
            getWordPart.close();
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }

        return output;
    }

    /**
     * Enter solution to the made quiz, to be run sequentially e = english of
     * welsh noun w = welsh of english noun g = gender of welsh noun ** ^ words
     * to compare against for answers
     *
     * @param solution user entered solution
     * @return Correct or false
     */
    public boolean solve(String solution) {
        // Check if running for question that does not exist
        if (currentWord < outOf) {
            String column = "";
            // Set column name based on type of quiz running
            switch (type) {
                case 'e':
                    column = "english";
                    break;
                case 'w':
                    column = "welsh";
                    break;
                case 'g':
                    column = "wordGender";
                    break;
                default:
                    System.out.println("error");
            }

            // Connect to the database
            try (Connection conn = SimpleDataSource.getConnection()) {
                // Prepare the statement
                PreparedStatement getWordPart = conn.prepareStatement(wordGet);
                // Set the the wordID
                getWordPart.setInt(1, wordIndex[getCurrentWordNo()]);
                // Search for the solution
                ResultSet rs = getWordPart.executeQuery();
                rs.next();
                // Check user input against required column of current word
                if (solution.equals(rs.getString(column))) {
                    // If correct, increment score and index
                    score++;
                    currentWord++;
                    return true;
                } else {
                    // Otherwise just increment index
                    currentWord++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Too many solutions");
        }

        // Any other route is a failure
        return false;
    }

    /**
     * Store final data of quiz into database.
     *
     * @return successful store or not
     */
    public void storeResult() {

        // Connect to the database
        try (Connection conn = SimpleDataSource.getConnection()) {
            // Form the statement
            PreparedStatement storeResult = conn.prepareStatement(resultStore);

            //Set data from current quiz
            storeResult.setString(1, username);
            storeResult.setString(2, String.valueOf(type));
            storeResult.setInt(3, getScore());
            storeResult.setInt(4, getOutOf());

            // Add the result and close the statement
            storeResult.executeUpdate();
            storeResult.close();
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
