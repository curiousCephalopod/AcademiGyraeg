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
public class TestResult {
    String testID;
    String user;
    int result;
    int outOf;
    public TestResult(String testID, String user, int result, int outOf)
    {
        this.testID = testID;
        this.user = user;
        this.result = result;
        outOf = 20;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public int getResult()
    {
        return result;
    }
    
    public double getPercentage()
    {
        return result / outOf;
    }
}
