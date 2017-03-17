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
import Core.Register;
import Core.SQL;
import GUI.Main;
import javafx.*;
import javafx.scene.control.Alert.AlertType;

import DeckCreation.Creator;

///
/// TODO: DOC THE CHANGES ON THIS PAGE
///

public class CreatorGUI {
    public static void StartCreator(Stage primaryStage){
        primaryStage.setTitle("Create new deck");
        
        DeckCreation.Creator CreatorInstance = new Creator();
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(55,50,0,50));

        Label Title = new Label("Deck Creation");
        Title.setAlignment(Pos.CENTER);
        Title.setPadding(new Insets(0,0,50,0));

        HBox NameOfDeck = new HBox(20);
        Label NameDeck = new Label("Deck Name:");
        TextField NameField = new TextField();
        NameOfDeck.getChildren().addAll(NameDeck,NameField);
        NameOfDeck.setPadding(new Insets(0,0,20,0));
        
        //First HBox for Question and input
        HBox QuestionHBox = new HBox();
        QuestionHBox.setAlignment(Pos.CENTER);
        Label QuestionLabel = new Label("Question/Hint:");
        QuestionLabel.setMaxWidth(Double.MAX_VALUE);
        
        TextField QuestionField = new TextField();
        QuestionField.setMaxWidth(Double.MAX_VALUE);
        
        //Second HBox for Answer and input
        HBox AnswerHBox = new HBox();
        AnswerHBox.setAlignment(Pos.CENTER);
        Label AnswerLabel = new Label("Answer:");
        AnswerLabel.setMaxWidth(Double.MAX_VALUE);
        
        TextField AnswerField = new TextField();
        AnswerField.setMaxWidth(Double.MAX_VALUE);
        
        QuestionLabel.setPrefWidth(150);
        QuestionField.setPrefWidth(200);
        
        AnswerLabel.setPrefWidth(150);
        AnswerField.setPrefWidth(200);
  
        VBox ButtonsHolder = new VBox(10);
        
        Button AddQuestion = new Button("Add");
        AddQuestion.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                   CreatorInstance.AddQuestion(QuestionField.getText(), AnswerField.getText());
            }
        });
        
        Button FinishDeck = new Button("Finish and Save");
        FinishDeck.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                   CreatorInstance.FinishEdits(NameField.getText());
            }
        });
        
        Button Quit = new Button("Quit");
        Quit.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                   Main.StartMainStage(primaryStage);
            }
        });
        ButtonsHolder.getChildren().addAll(AddQuestion,FinishDeck,Quit);
        ButtonsHolder.setAlignment(Pos.CENTER);
        ButtonsHolder.setPadding(new Insets(20,0,0,0));
        
        
        QuestionHBox.getChildren().addAll(QuestionLabel,QuestionField);
        AnswerHBox.getChildren().addAll(AnswerLabel,AnswerField);
        vbox.getChildren().addAll(Title,NameOfDeck,QuestionHBox,AnswerHBox,ButtonsHolder);
        
        Scene RegisterScene = new Scene(vbox,450,350);
        primaryStage.setScene(RegisterScene);
        primaryStage.show();
    }
}
