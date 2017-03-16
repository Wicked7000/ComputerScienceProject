/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Core.FileSaver;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import Core.Leitner.*;

public class Session {
    
    static Text QuestionText = null;
    static Button RevealButton = null;
    
    public static void SessionHandler(Deck PassedDeck){
        Leitner SessionOBJ = new Leitner(PassedDeck);
        RevealButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                
            }
        });
                
        QuestionText.setText(SessionOBJ.ReadQuestion());
    }
    
    public static void StartSessionStage(Stage primaryStage,String DeckName,Core.Leitner.Deck TheDeck){
        primaryStage.setTitle("Register");
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(55,50,0,50));

        Label LoginText = new Label("ReviseMore");
        Label LoginText2 = new Label("Remember more!");
        LoginText.setAlignment(Pos.CENTER);
        LoginText2.setAlignment(Pos.CENTER);
        LoginText2.setPadding(new Insets(0,0,50,0));
        
        QuestionText = new Text("Question Text Here!");
        
        RevealButton = new Button("Reveal");

        vbox.getChildren().addAll(LoginText,LoginText2,QuestionText,RevealButton);
        
        Scene RegisterScene = new Scene(vbox,450,350);
        primaryStage.setScene(RegisterScene);
        primaryStage.show();
        
        SessionHandler(TheDeck);
    }
}
