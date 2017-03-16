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
import javafx.scene.paint.*;
import Core.Leitner.*;

public class Session {
    
    static Text QuestionText = null;
    static Text AnswerText = null;
    static Button RevealButton = null;
    static HBox Options = null;
    
    public static void SessionHandler(Deck PassedDeck){
        Leitner SessionOBJ = new Leitner(PassedDeck);
        RevealButton.setVisible(true);
        RevealButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                AnswerText.setText(SessionOBJ.ReadAnswer());
                AnswerText.setVisible(true);
                Options.setVisible(true);
                RevealButton.setVisible(false);
            }
        });
                
        QuestionText.setText(SessionOBJ.ReadQuestion());
    }
    
    public static void StartSessionStage(Stage primaryStage,String DeckName,Core.Leitner.Deck TheDeck){
        primaryStage.setTitle("Register");
        
        VBox vbox = new VBox(0);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(55,50,0,50));

        Label LoginText = new Label("ReviseMore");
        Label LoginText2 = new Label("Remember more!");
        LoginText.setAlignment(Pos.CENTER);
        LoginText2.setAlignment(Pos.CENTER);
        LoginText2.setPadding(new Insets(0,0,50,0));
        
        QuestionText = new Text("Question Text Here!");
        AnswerText = new Text("");
        AnswerText.managedProperty().bind(AnswerText.visibleProperty());
        AnswerText.setVisible(false);
        AnswerText.setFill(Paint.valueOf("red"));
        
        RevealButton = new Button("Reveal");
        RevealButton.managedProperty().bind(RevealButton.visibleProperty());
        RevealButton.setVisible(false);
        RevealButton.setPadding(new Insets(10,50,10,50));
        
        Options = new HBox(10);
        Button Good = new Button("Good");
        Button Bad = new Button("Bad");
        Options.getChildren().addAll(Good,Bad);
        Options.managedProperty().bind(Options.visibleProperty());
        Options.setVisible(false);
        Options.setAlignment(Pos.CENTER);
        Options.setPadding(new Insets(10,0,10,0));
                
        vbox.getChildren().addAll(LoginText,LoginText2,QuestionText,AnswerText,RevealButton,Options);
        
        Scene RegisterScene = new Scene(vbox,450,350);
        primaryStage.setScene(RegisterScene);
        primaryStage.show();
        
        SessionHandler(TheDeck);
    }
}
