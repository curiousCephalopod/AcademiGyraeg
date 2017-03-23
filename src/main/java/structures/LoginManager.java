/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ed
 */
public class LoginManager {
    String username;
    int userType = 0;
    //1 = student
    //2 = instructor
    //3 = admin
    String pass;
    Boolean userExists = false;
    Boolean loggedIn = false;
    
    ResultSet rs;
    
    PreparedStatement checkUsername = null;
    PreparedStatement checkPass = null;
    PreparedStatement checkType = null;
    String userString = "SELECT * FROM login WHERE username = ?";
    String passString = "SELECT * FROM login WHERE username = ? AND password = ?";
    
    String typeString = "SELECT userType FROM login WHERE username = ?";
    
    
    public LoginManager(String username, String pass) 
    {
        this.username = username;
        this.pass = pass;
        
        //checkUsername = con.prepareStatement(userString);
        //checkPass = con.prepareStatement(passString);
        //checkType = con.prepareStatement(typeString);
        
        try
            {
            if  (checkUsername.execute())
            {
                userExists=true;
                if (checkPass.execute())
                {
                    loggedIn=true;
                    rs = checkType.executeQuery();
                    //equate user type from rs here
                }
            }
        }
        catch(SQLException exception)
        {
            System.out.println("Database SQL error");
        }
    }
    
    public boolean exists()
    {
        return userExists;
    }
    
    public boolean isLoggedIn()
    {
        return loggedIn;
    }
    
    public String getUser()
    {
        return username;
    }
    
    public boolean canEditWords()
    {
        if (userType == 2)
                return true;
        else
            return false;
    }
    
    public boolean canRegisterUsers()
    {
        if (userType == 3)
            return true;
        else
            return false;
    }
}
