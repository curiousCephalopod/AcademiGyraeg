/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 *
 * @author Ed
 */
public class TestWord {
    String wordID;
    String wordE;
    String wordW;
    String type;
    String gender;
    
    public TestWord(String wordID, String wordE, String wordW, String type, String gender)
    {
        this.wordID = wordID;
        this.wordE = wordE;
        this.wordW = wordW;
        this.type = type;
        this.gender = gender;
    }
        
    public String getEnglish()
    {
        return wordE;
    }
    
    public String getWelsh()
    {
        return wordW;
    }
    
    public String getWordType()
    {
        return type;
    }
    
    public String getGender()
    {
        return gender;
    }
}
