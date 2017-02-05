package Tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class JUnitRunner {
    public static void main(String[] args){
        runClass(ValidationTests.class,"Validation Tests:");
        runClass(LeitnerTests.class,"LeitnerTests");
    }
    
    public static void runClass(Class Class,String Type){
        System.out.print(Type);
        Result result = JUnitCore.runClasses(Class);
        
        for(Failure failure : result.getFailures()){
            System.out.println("fail: " + failure.toString());
        }
        
        System.out.println("Passed?:" +result.wasSuccessful());
    }
}
