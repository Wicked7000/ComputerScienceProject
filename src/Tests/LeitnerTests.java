package Tests;

import Core.Leitner.Box;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import Core.Register;

public class LeitnerTests {
    Box TestingBox = new Box(0,null,0);
    
    @Test
    public void TestBoxAddQuestions(){
        Box Instance = TestingBox;
        Instance.AddQuestion("This is a Question", "Answer1");
        assertEquals("Error Occured adding a question! ",1,Instance.Questions.size());
        assertEquals("Error Occured with data in the Box","Answer1",Instance.Questions.get(0).Answer);
    }
    
    @Test
    public void TestBoxRemoveQuestions(){
        Box Instance = TestingBox;
        Instance.AddQuestion("This is a Question", "Answer1");
        Instance.RemoveQuestion(Instance.Questions.get(0));
        assertEquals("Error Occured removing a question!",0,Instance.Questions.size());
    }
}
