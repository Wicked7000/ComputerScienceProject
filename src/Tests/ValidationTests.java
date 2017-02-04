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
    
}
