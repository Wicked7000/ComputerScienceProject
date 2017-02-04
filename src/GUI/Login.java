package GUI;
 
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;

public class Login extends Application {
    
    public Scene LoginScene = null;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void RegisterStage(Stage primaryStage){
        primaryStage.setTitle("Register");
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(55,50,0,50));

        Label LoginText = new Label("ReviseMore");
        Label LoginText2 = new Label("Remember more!");
        LoginText.setAlignment(Pos.CENTER);
        LoginText2.setAlignment(Pos.CENTER);
        LoginText2.setPadding(new Insets(0,0,50,0));

        VBox InputFields = new VBox();
        TextField Username = new TextField();
        Username.setPromptText("Enter desired Username");
        Username.setAlignment(Pos.CENTER);
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter desired Password");
        passwordField.setAlignment(Pos.CENTER);
        
        PasswordField passwordField2 = new PasswordField();
        passwordField2.setPromptText("Re-type password");
        passwordField2.setAlignment(Pos.CENTER);
        InputFields.getChildren().addAll(Username,passwordField,passwordField2);
        InputFields.setSpacing(7.5);
        
        VBox SubmitVbox = new VBox();
        SubmitVbox.setAlignment(Pos.CENTER);
        Button Login = new Button("Back to Login");
        
        Login.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                primaryStage.setScene(LoginScene);
                primaryStage.setTitle("Login");
            }
        });
        
        Button SubmitData = new Button("Sign-up");
        SubmitVbox.getChildren().addAll(SubmitData,Login);
        SubmitVbox.setPadding(new Insets(50,0,0,0));
        SubmitVbox.setSpacing(9);
        
        vbox.getChildren().addAll(LoginText,LoginText2,InputFields,SubmitVbox);
        
        Scene RegisterScene = new Scene(vbox,250,350);
        primaryStage.setScene(RegisterScene);
        primaryStage.show();
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");
        
        //Create a 'Root' Vbox with a alignment of TOP_CENTER
        //Also add a padding of 50 to Left and Right and 55 to to top
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(55,50,0,50));
        
        //Changed both text objects to labels as they have the .setAlignmentMethod
        //Set both of them to POS.CENTER so they get centered
        //added padding to the bottom to seperate it from the input fields.
        Label LoginText = new Label("ReviseMore");
        Label LoginText2 = new Label("Remember more!");
        LoginText.setAlignment(Pos.CENTER);
        LoginText2.setAlignment(Pos.CENTER);
        LoginText2.setPadding(new Insets(0,0,50,0));

        //Create a new VBox for the input fields to allow spacing 
        VBox InputFields = new VBox();
        TextField Username = new TextField();
        Username.setPromptText("Username...");
        Username.setAlignment(Pos.CENTER);
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password...");
        passwordField.setAlignment(Pos.CENTER);
        InputFields.getChildren().addAll(Username,passwordField);
        //Set the spacing between the fields to 7.5 Units
        InputFields.setSpacing(7.5);
        
        //Again create a new Vbox to allow for the seperation of elements
        VBox SubmitVbox = new VBox();
        SubmitVbox.setAlignment(Pos.CENTER);
        Button Register = new Button("Don't have an account?");
        
        Register.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                RegisterStage(primaryStage);
            }
        });
        
        Button SubmitData = new Button("Log-in");
        SubmitVbox.getChildren().addAll(SubmitData,Register);
        SubmitVbox.setPadding(new Insets(50,0,0,0));
        SubmitVbox.setSpacing(9);
        
        //Get the root vBox and add everything in order
        vbox.getChildren().addAll(LoginText,LoginText2,InputFields,SubmitVbox);
        
        //append it to the scene and show it.
        Scene scene = new Scene(vbox,250,350);
        LoginScene = scene;
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}