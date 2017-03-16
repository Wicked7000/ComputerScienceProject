package GUI;

import Core.FileSaver;
import java.util.*;
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
import javafx.*;
import javafx.scene.control.Alert.AlertType;

import GUI.CreatorGUI;

public class Main {
    public static void StartMainStage(Stage primaryStage){
        primaryStage.setTitle("Register");
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(55,50,0,50));

        Label LoginText = new Label("ReviseMore");
        Label LoginText2 = new Label("Remember more!");
        LoginText.setAlignment(Pos.CENTER);
        LoginText2.setAlignment(Pos.CENTER);
        LoginText2.setPadding(new Insets(0,0,50,0));

        VBox DecksVbox = new VBox();
        //Get Decks and add to GUI
        DecksVbox.setAlignment(Pos.CENTER);
        ArrayList<String> Files = FileSaver.ListDecks();
        for(int X =0; X< Files.size();X++){
            String FileName = Files.get(X);
            Core.Leitner.Deck ThisDeck = FileSaver.LoadFile(FileName);
            Button OpenDeckTemp = new Button(FileName.substring(0,FileName.length()-4));
            OpenDeckTemp.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle(ActionEvent e){
                    Session.StartSessionStage(primaryStage, FileName, ThisDeck);
                }
            });
            DecksVbox.getChildren().addAll(OpenDeckTemp);
        }
        
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        Button CreateNewDeck = new Button("Create New Deck");
        CreateNewDeck.setMaxWidth(Double.MAX_VALUE);
        Button SearchDeck = new Button("Search For Decks");
        SearchDeck.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(CreateNewDeck,Priority.ALWAYS);
        HBox.setHgrow(SearchDeck,Priority.ALWAYS);
        
        CreateNewDeck.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
               CreatorGUI.StartCreator(primaryStage); 
            }
        });
        
        hbox.getChildren().addAll(CreateNewDeck,SearchDeck);
        vbox.getChildren().addAll(LoginText,LoginText2,DecksVbox,hbox);
        
        Scene RegisterScene = new Scene(vbox,450,350);
        primaryStage.setScene(RegisterScene);
        primaryStage.show();
    }
}
