/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.sql.PreparedStatement;
import java.util.Random;

/**
 *
 * @author Ed
 */
public class Quiz {
    int outOf = 20;
    TestWord[] wordList = new TestWord[outOf];
    int wordIndex[] = new int[outOf];
    Random rand = new Random();
    int noWords = 0;
    
    PreparedStatement retrieve = null;
    String wordString = "SELECT * FROM words WHERE wordID = ?";
    String getAllIDS = "SELECT wordID FROM words";
    
    public Quiz()
    {
        //run get all IDs
        for(int i=0;i<outOf;i++)
        {
            wordIndex[i] = rand.nextInt()%noWords;
            //add wordID from RS[rand.nextInt()%noWords] into wordIndex
        }
        //add words of IDS to wordList
        for(int i=0;i<outOf;i++)
        {
            //wordList[i] = statement[wordindex[i]]
        }
    }
    
    //q = wordlist index
    //e = english
    //w = welsh
    //g = gender
    //t = type
    public String get(int q, char x)
    {
        switch(x)
        {
            case 'e':
                return wordList[q].getEnglish();
            case 'w':
                return wordList[q].getWelsh();
            case 'g':
                return wordList[q].getGender();
            case 't':
                return wordList[q].getWordType();
            default:
                return "error";
        }
    }
}
