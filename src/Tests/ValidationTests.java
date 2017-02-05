package Tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import Core.Register;

public class ValidationTests {
    String[] TestUsernames = new String[]{"Wicked","sam","HelloWorld5","HelloCat2","5 10 15 20","aaabbbcccdddeee","hello@world"};  
    int[] ExpectedResults = new int[]{1,-1,1,1,-1,-1,-1};
    
    @Test
    public void BasicUsername(){
        for(int X=0;X<TestUsernames.length;X++){
            int Result = Register.CheckUsername(TestUsernames[X]);
            assertEquals("Error Occured input:" + TestUsernames[X],ExpectedResults[X],Result);
        }
    }
    
    String[][] TestPasswords = new String[][]{{"SamPass","SamPass"},{"HelloWorld5346","HelloWorld5346"},{"Workedpass!","Workedpass!"},{"nocapital","nocapital"},{"aaabbbF!ioasdr","aaabbbF!ioasdr"},{"donotMatch!","match!Dat"}};
    int[] ExpectedResultsPass = new int[]{-1,-1,1,-1,-1,-1};
    
    @Test
    public void PasswordChecks(){
        for(int X=0;X<TestPasswords.length;X++){
            int Result = Register.CheckPassword(TestPasswords[X][0], TestPasswords[X][1]);
            assertEquals("Error Occured input:" + TestPasswords[X][0]+":"+TestPasswords[X][1],ExpectedResultsPass[X],Result);
        }

    }
    
}
