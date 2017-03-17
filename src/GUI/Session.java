/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.*;
import Core.FileSaver;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.paint.*;
import Core.Leitner.*;
import javafx.scene.text.TextAlignment;

public class Session {
    
    static Text QuestionText = null;
    static Text AnswerText = null;
    static TextField AnswerField = null;
    
    static Button ExitButton = null;
    static Button SubmitButton = null;
    static Button NextQuestion = null;
    static Leitner SessionOBJ = null;
    
    ///
    /// CAN CURRENTLY NOT LOAD MORE THAN ONE QUESTION PLEASE FIX SOON
    /// MAKE SURE TO DOC THIS WHOLE PAGE!
    ///
    
    public static void NextQuestionPrep(){
        AnswerText.setText("");
        QuestionText.setText(SessionOBJ.ReadQuestion());
    }
    
    public static boolean CheckSession(){
        if(SessionOBJ.IsNextQuestion() == -1){
            System.out.println("Testing");
            ExitButton.setVisible(true);
            NextQuestion.setVisible(false);
            QuestionText.setVisible(false);
            SubmitButton.setVisible(false);
            AnswerField.setVisible(false);
            AnswerText.setText("Session finished please exit!");
            return false;
        }else{
            QuestionText.setVisible(true);
            SubmitButton.setVisible(true);
            AnswerField.setVisible(true);
            SubmitButton.setVisible(true);
            return true;
        }
    }
    
    public static void SessionHandler(Deck PassedDeck){
        if(SessionOBJ == null){
            SessionOBJ = new Leitner(PassedDeck);
        }
        boolean Proceed = CheckSession();
        
        NextQuestion.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                SessionOBJ.NextQuestion();
                SessionHandler(PassedDeck);
            }
        });
        
        if(Proceed == true){
        SubmitButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                Map<Boolean,String> SessionAns = SessionOBJ.ReadAndCheckAnswer(AnswerField.getText());
                String Answer = SessionAns.get(true);
                QuestionText.setVisible(false);
                SubmitButton.setVisible(false);
                AnswerField.setVisible(false);
                if(Answer != null){
                    //They got the answer correct
                    AnswerText.setFill(Paint.valueOf("green"));
                    AnswerText.setText(Answer + "\nCorrect!");
                    AnswerText.setVisible(true);
                    SessionOBJ.AddCorrectQuestion();
                }else{
                    //They got the question wrong
                    AnswerText.setFill(Paint.valueOf("red"));
                    AnswerText.setText(SessionAns.get(false) + "\n Incorrect!");
                    AnswerText.setVisible(true);
                    SessionOBJ.AddIncorrectQuestion();
                }
                NextQuestion.setVisible(true);
            }
        });
                
        QuestionText.setText(SessionOBJ.ReadQuestion());
        }
    }
    
    public static void StartSessionStage(Stage primaryStage,String DeckName,Core.Leitner.Deck TheDeck){
        primaryStage.setTitle("Session:"+ TheDeck.DeckName);
        
        VBox vbox = new VBox(0);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(55,50,0,50));

        Label LoginText = new Label("ReviseMore");
        Label LoginText2 = new Label("Remember more!");
        LoginText.setAlignment(Pos.CENTER);
        LoginText2.setAlignment(Pos.CENTER);
        LoginText2.setPadding(new Insets(0,0,50,0));
        
        QuestionText = new Text("Question Text Here!");
        QuestionText.managedProperty().bind(QuestionText.visibleProperty());
        
        VBox ElementHolder = new VBox(5);
        
        AnswerText = new Text("");
        AnswerText.setTextAlignment(TextAlignment.CENTER);
        AnswerText.managedProperty().bind(AnswerText.visibleProperty());
        AnswerText.setVisible(false);
        AnswerText.setFill(Paint.valueOf("red"));
        
        SubmitButton = new Button("Submit");
        SubmitButton.managedProperty().bind(SubmitButton.visibleProperty());
        SubmitButton.setVisible(false);
        SubmitButton.setPadding(new Insets(10,50,10,50));
        
        AnswerField = new TextField();
        AnswerField.setPadding(new Insets(10,0,10,0));
        AnswerField.managedProperty().bind(AnswerField.visibleProperty());
        
        NextQuestion = new Button("Next");
        NextQuestion.managedProperty().bind(NextQuestion.visibleProperty());
        NextQuestion.setVisible(false);
        
        ExitButton = new Button("Exit");
        ExitButton.managedProperty().bind(ExitButton.visibleProperty());
        ExitButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                SessionOBJ = null;
                Main.StartMainStage(primaryStage);
            }
        });
        ExitButton.setPadding(new Insets(10,30,10,30));
        ExitButton.setVisible(false);
        
        ElementHolder.getChildren().addAll(QuestionText,AnswerText,AnswerField,SubmitButton,NextQuestion,ExitButton);
        ElementHolder.setAlignment(Pos.CENTER);
        
        vbox.getChildren().addAll(LoginText,LoginText2,ElementHolder);
        
        Scene RegisterScene = new Scene(vbox,450,350);
        primaryStage.setScene(RegisterScene);
        primaryStage.show();
        
        SessionHandler(TheDeck);
    }
}
