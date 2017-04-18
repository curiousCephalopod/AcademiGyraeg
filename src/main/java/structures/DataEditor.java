/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

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
    
    public DataEditor(String username)
    {
        try{
        checkWordExists = conn.prepareStatement("SELECT wordID FROM words WHERE ? = ?");
        updateWord = conn.prepareStatement("UPDATE words SET english = ?, welsh = ?, wordType = ?, wordGender = ? WHERE wordID = ?");
        deleteWord = conn.prepareStatement("DELETE FROM words WHERE wordID = ?");
        addWord = conn.prepareStatement("INSERT INTO words(english, welsh, wordType, wordGender) VALUES ('?','?','?','?')");
        }
        catch(SQLException exception)
        {
            System.out.println("sql error");
        }
        this.username = username;
    }
    
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
}
