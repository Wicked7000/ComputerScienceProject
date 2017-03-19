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
import Core.APIHelper;

public class Search {
    public static void StartSearchGUI(Stage PrimaryStage){
        
        VBox VerticalHolder = new VBox();
        VerticalHolder.setAlignment(Pos.TOP_CENTER);
        VerticalHolder.setPadding(new Insets(0,0,20,0));
        Text Label = new Text("Search Decks:");
        
        APIHelper Helper = new APIHelper();
        List<Map<String,String>> Decks = Helper.GetDecks();
        
        VBox DeckHolder = new VBox(10);
        DeckHolder.setAlignment(Pos.CENTER);
        DeckHolder.setPadding(new Insets(10,0,0,0));
        
        for(int I=0;I < Decks.size();I++){
            final String DeckXML = Decks.get(I).get("xml");
            final String DeckName = Decks.get(I).get("name");
            Button TempDeck = new Button(DeckName);
            TempDeck.setPrefSize(250,25);
            DeckHolder.getChildren().add(TempDeck);
            TempDeck.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle(ActionEvent e){
                    FileSaver.DownloadFile(DeckXML,DeckName);
                }
            });
        }
        
        VerticalHolder.getChildren().addAll(Label,DeckHolder);
        
        Scene RegisterScene = new Scene(VerticalHolder,450,350);
        PrimaryStage.setScene(RegisterScene);
        PrimaryStage.show();
    }
}
