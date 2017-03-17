package DeckCreation;

import Core.Leitner.Holder;
import Core.Leitner.Deck;
import Core.Leitner.Box;
import java.util.ArrayList;
import Core.FileSaver;

public class Creator {
    public ArrayList<Holder> Questions;
    public Deck ThisDeck;
    
    public Creator(){
        //This is the initialisation of the creation process
        ThisDeck = new Deck();
    }
    
    public void AddQuestion(String QuestionString,String AnswerString){
        //Adds Questions to the deck.
        ThisDeck.AddQuestion(QuestionString, AnswerString);
        System.out.print("Added a Question!");
    }
    
    public void FinishEdits(String _DeckName){
        //Save file
        ThisDeck.DeckName = _DeckName;
        FileSaver.CreateFile(ThisDeck);
        System.out.print("File Saved!");
    }
}
