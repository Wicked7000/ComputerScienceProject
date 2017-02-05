package Core;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
/**
 *
 * @author Jason
 */
public class Register {

  	public static void main(String[] args){
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Enter a username: ");
            String Username = reader.nextLine();
  	}
        
        public static String HashPassword(String Password){
            MessageDigest messageDigest = null;
            try{
                messageDigest = MessageDigest.getInstance("SHA-256");
            }catch(NoSuchAlgorithmException e){
                System.out.println("No Algo");
            }
            messageDigest.update(Password.getBytes());
            String encryptedString = new String(messageDigest.digest());
            return encryptedString;
        }
  
  	//This checks a vaild username
	public static int CheckUsername(String Username){
            if(Username.length() > 5 && Username.length() < 12){
                if(Username.contains(" ") == false){
                    //Use Regex to exclude any special characters from being in the username
                    Pattern pattern = Pattern.compile("(\\w*)");
                    Matcher matcher = pattern.matcher(Username);


                    if(matcher.matches() == true){
                          return 1;
                    }
                }else{
                    return -1;
                }
            }
            return -1;
	}
  
  	//Checks if both passwords match and if it includes a special character
  	public static int CheckPassword(String Pass1,String Pass2){             
            if(Pass1.equals(Pass2) && (Pass1.length() < 13 && Pass1.length() > 7)){
      		Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Pattern CapitalLetters = Pattern.compile("([A-Z])");
                
          	Matcher matcher = pattern.matcher(Pass1);
                
                //Check for special Characters
          	if(matcher.find() == true){
                    Matcher Capital = CapitalLetters.matcher(Pass1);
                    //Check for capital Letters
                    if(Capital.find() == true){
                        return 1;
                    }
          	}
            }
            return -1;
  	}

}