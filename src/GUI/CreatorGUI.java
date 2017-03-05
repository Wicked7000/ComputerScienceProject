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

        //First HBox for Question and input
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        Label QuestionLabel = new Label("Question/Hint:");
        QuestionLabel.setMaxWidth(Double.MAX_VALUE);
        
        TextField QuestionField = new TextField();
        QuestionField.setMaxWidth(Double.MAX_VALUE);
        
        //Second HBox for Answer and input
        HBox hbox2 = new HBox();
        hbox.setAlignment(Pos.CENTER);
        Label AnswerLabel = new Label("Answer:");
        AnswerLabel.setMaxWidth(Double.MAX_VALUE);
        
        TextField AnswerField = new TextField();
        AnswerField.setMaxWidth(Double.MAX_VALUE);
        
        QuestionLabel.setPrefWidth(150);
        QuestionField.setPrefWidth(200);
        
        AnswerLabel.setPrefWidth(150);
        AnswerField.setPrefWidth(200);
        
        hbox2.setPadding(new Insets(0,0,25,0));
        
        
        Button AddQuestion = new Button("Add");
        AddQuestion.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                   CreatorInstance.AddQuestion(QuestionField.getText(), AnswerField.getText());
            }
        });
        
        Button FinishDeck = new Button("Finish and Save");
        FinishDeck.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                   CreatorInstance.FinishEdits();
            }
        });
        
        Button Quit = new Button("Quit");
        Quit.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                   Main.StartMainStage(primaryStage);
            }
        });
        
        hbox.getChildren().addAll(QuestionLabel,QuestionField);
        hbox2.getChildren().addAll(AnswerLabel,AnswerField);
        vbox.getChildren().addAll(Title,hbox,hbox2,AddQuestion,FinishDeck,Quit);
        
        Scene RegisterScene = new Scene(vbox,450,350);
        primaryStage.setScene(RegisterScene);
        primaryStage.show();
    }
}
