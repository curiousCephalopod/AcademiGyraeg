/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.academigyraeg;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ed
 */
public class DataEditor {
    
    InputStream stream;
    Connection conn;
    Statement st;
    
    
    String username;
    PreparedStatement checkWordExists = null; 
    PreparedStatement updateWord = null;
    PreparedStatement deleteWord = null;
    PreparedStatement addWord = null;
    PreparedStatement addUser = null;
    
    String addUserString = "INSERT INTO login VALUES(username, password, usertype) VALUES (?, ?, ?);";
    
    /**
     * Constructs data editor object
     * @param username username of current user
     */
    public DataEditor(String username)
    {
        try{
        checkWordExists = conn.prepareStatement("SELECT wordID FROM words WHERE ? = ?");
        updateWord = conn.prepareStatement("UPDATE words SET english = ?, welsh = ?, wordType = ?, wordGender = ? WHERE wordID = ?");
        deleteWord = conn.prepareStatement("DELETE FROM words WHERE wordID = ?");
        addWord = conn.prepareStatement("INSERT INTO words(english, welsh, wordType, wordGender) VALUES (?,?,?,?)");
        }
        catch(SQLException exception)
        {
            System.out.println("sql error");
        }
        this.username = username;
    }
    /**
     * updates a word within the database
     * @param ID of word to be edited
     * @param english translation of the word
     * @param welsh word
     * @param type of word
     * @param gender of word
     * @return update success status
     */
    public boolean updateWord(int ID, String english, String welsh, char type, char gender)
    {
        try
        {
            updateWord.setString(1, english);
            updateWord.setString(2, welsh);
            updateWord.setString(3, ""+type);
            updateWord.setString(4, ""+gender);
            updateWord.setString(5, ""+ID);
            updateWord.executeUpdate();
            return true;
        }
        catch(SQLException exception)
        {
            System.out.println("sql update error");
            return false;
        }
        
    }
    /**
     * Delete word from database
     * @param ID of word to be deleted
     * @return delete success status
     */
    public boolean deleteWord(int ID)
    {
        try
        {
            deleteWord.setString(1,""+ID);
            deleteWord.executeUpdate();
            return true;
        }
        catch(SQLException exception)
        {
            System.out.println("SQL delete error");
            return false;
        }
    }
    /**
     * add new word to database
     * @param english translation
     * @param welsh word
     * @param type of word
     * @param gender of word
     * @return status of word addition
     */
    public boolean addWord(String english, String welsh, char type, char gender)
    {
        
        try
        {
            addWord.setString(1, english);
            addWord.setString(2, welsh);
            addWord.setString(3, ""+type);
            addWord.setString(4, ""+gender);
            addWord.executeUpdate();
            return true;
        }
        catch(SQLException exception)
        {
            System.out.println("sql insert error");
            return false;
        }
    }
    /**
     * adds new account to the database
     * @param username of new account
     * @param password of new account
     * @param userType user classification
     * @return status of user addition
     */
    public boolean addAccount(String username, String password, String userType)
    {
        try
        {
            addUser = conn.prepareStatement(addUserString);
            addUser.setString(1, username);
            addUser.setString(2, password);
            addUser.setString(3, userType);
            addUser.executeQuery();
            return true;
        }
        catch(SQLException exception)
        {
            System.out.println("error adding user");
            return false;
        }
    }
    
    
}


