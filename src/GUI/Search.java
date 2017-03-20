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
import javafx.concurrent.Task;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;

public class Search {
    private static VBox DeckHolder;
    private static Stage ThisStage;

   // public static void AddButtonToDeckHolder(List<Map<String,String>> DeckList){
    //    DeckListGlobal = DeckList;
    //    StartSearchGUI(ThisStage);
   // }
    
    public static void StartSearchGUI(Stage PrimaryStage){
        ThisStage = PrimaryStage;
        VBox VerticalHolder = new VBox();
        VerticalHolder.setAlignment(Pos.TOP_CENTER);
        VerticalHolder.setPadding(new Insets(0,0,20,0));
        Text Label = new Text("Search Decks:");
        Text Information = new Text("In order to use a deck, click on its name it will be downloaded and accessed from the main screen.");
        
        DeckHolder = new VBox(10);
        DeckHolder.setAlignment(Pos.CENTER);
        DeckHolder.setPadding(new Insets(10,0,0,0));
        GridPane grid = new GridPane();
        
        ScrollPane DeckPane = new ScrollPane();
        DeckPane.setHvalue(0.5);
        DeckPane.setVvalue(0.5);
        DeckPane.setPrefSize(100, 500);
        
        StackPane Center = new StackPane(DeckHolder);
        Center.minWidthProperty().bind(Bindings.createDoubleBinding(()->
        DeckPane.getViewportBounds().getWidth(),DeckPane.viewportBoundsProperty()));
        grid.getChildren().add(Center);
        DeckPane.setContent(Center);
        
        
        Task<Void> task = new Task<Void>(){
            protected Void call() throws Exception{
                APIHelper Helper = new APIHelper();
                List<Map<String,String>> DeckList = Helper.GetDecks();
                for(int X=0;X<DeckList.size();X++){
                    //If the process is happening too fast this slows it down to not 'flood' the UI thread.
                    Thread.sleep(10);
                    String DeckName = DeckList.get(X).get("name");
                    String XML = DeckList.get(X).get("xml");
                    String Author = DeckList.get(X).get("author");
                    
                    Button TempButton = new Button(DeckName +" : "+Author);
                    TempButton.setOnAction(new EventHandler<ActionEvent>(){
                        @Override public void handle(ActionEvent e){
                            FileSaver.DownloadFile(XML, DeckName);
                        }
                    });
                    TempButton.setPrefSize(200, 25);
                    Platform.runLater(new Runnable(){
                        @Override public void run(){
                            DeckHolder.getChildren().add(TempButton);
                        }
                    });
                }
                return null;
            }
        };
        
        //Progress bar for the loading of decks!
        ProgressBar Loading = new ProgressBar();
        Loading.progressProperty().bind(task.progressProperty());
        
        task.setOnScheduled(e -> {
            Loading.setVisible(false);
            Loading.setManaged(false);
        }); 
        
        new Thread(task).start();

        VBox ExitB = new VBox();
        
        Button Exit = new Button("Exit");
        Exit.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                Main.StartMainStage(PrimaryStage);
            }
        });
        ExitB.getChildren().add(Exit);
        ExitB.setPadding(new Insets(20,0,0,0));
        ExitB.setAlignment(Pos.CENTER);
        
        VerticalHolder.getChildren().addAll(Label,Information,Loading,DeckPane,ExitB);
        
        Scene RegisterScene = new Scene(VerticalHolder,450,350);
        PrimaryStage.setScene(RegisterScene);
        PrimaryStage.show();
    }
}
