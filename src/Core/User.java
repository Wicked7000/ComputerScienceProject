package Core;

public class User {
    private static User instance = null;
    private static String Username = null;
    
    protected User(){}
    
    public static void setUsername(String _User){
        Username = _User;
    }
    
    public static String getUsername(){
        return Username;
    }
    
    public static User getInstance(){
        if(instance == null){
            instance = new User();
        }
        return instance;
    }
}
