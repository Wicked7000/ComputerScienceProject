package Core;

import java.sql.*;

public class SQL {
    static Connection con = null;
    
    public void Setup(){
        RegisterDriver();
        Connect();
        GetAllData();
        RegisterUser("Kill","Bill");
    }
    
    static void RegisterDriver(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            System.out.println("Unable to load driver");
        }
    }
    
    static void WipeUserTable(){
        PreparedStatement stmt = null;
        try {
            String SQL = "DELETE FROM Users";
            stmt = con.prepareStatement(SQL);
            stmt.execute();
            
            System.out.println("Deleted all data!");
        }
        catch (SQLException e) {
            System.out.println("Error Deleting!" + e);
        }
    }
    
    public static boolean Login(String Username,String HashedPassword){
        Statement stmt = null;
        try {
            stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
            String SQL = String.format("SELECT Username,Password FROM Users WHERE Username = '%s'",Username);
            ResultSet rs = stmt.executeQuery(SQL);
            rs.first();
            System.out.println(rs.getString("Username")); 
            System.out.println(rs.getString("Password"));
            if(rs.getString("Password").equals(HashedPassword)){
               stmt.close();
               return true;
            }
            else{
               stmt.close();
               return false;
            }
        }
        catch (Exception e) {
           System.out.println("ERROR LOGIN! : " + e);
           return false;
        }
    }
    
    public static void Connect(){
        RegisterDriver();
        String URL = "jdbc:mysql://mydbinstance.c2j94hxi2irl.eu-west-2.rds.amazonaws.com:3306";
        String User = "wicked";
        String Pass = "happyface123";
        try{
            con = DriverManager.getConnection(URL, User, Pass);
        }catch(SQLException e){
            System.out.println("Cannot connect to DB");
        }
        CreateTable();
    }
    
    //Development Only
    public static void GetAllData(){
        Statement stmt = null;
        try {
            stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
            String SQL = "SELECT Username,Password FROM Users";
            ResultSet rs = stmt.executeQuery(SQL);
            rs.last();
            int LastRow = rs.getRow();
            rs.beforeFirst();
            for(int X=0;X<LastRow;X++){
                rs.next();
                System.out.println(String.format("Row %s:%2s,%3s",X,rs.getString("Username"),rs.getString("Password")));
            }
        }
        catch (Exception e) {
           System.out.println(e);
        }
        finally {
        }
    }
    
    public static void RegisterUser(String Username,String HashedPassword){
        PreparedStatement stmt = null;
        try {
            String SQL = String.format("INSERT INTO Users(Username,Password) VALUES('%s','%2s')",Username,HashedPassword);
            System.out.println(SQL);
            String SQLSelect = "USE Users";
            stmt = con.prepareStatement(SQLSelect);
            stmt.execute();
            
            stmt = con.prepareStatement(SQL);
            stmt.execute();
        }
        catch (SQLException e) {
           System.out.println(e);
        }
        finally {
        }
        GetAllData();
    }
    
    public static void CreateTable(){
        PreparedStatement stmt = null;
        try {
            String SQL = "CREATE TABLE Users(UserID int,Username varchar(255),Password varchar(255));";
            String SQLSelect = "USE Users";
            stmt = con.prepareStatement(SQLSelect);
            stmt.execute();
            
            stmt = con.prepareStatement(SQL);
            stmt.execute();
            stmt.close();
        }
        catch (SQLException e) {
           System.out.println(e);
        }
        finally {
        }
    }
}
