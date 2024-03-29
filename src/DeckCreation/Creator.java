package DeckCreation;

import Core.Leitner.Holder;
import Core.Leitner.Deck;
import Core.Leitner.Box;
import java.util.ArrayList;
import Core.FileSaver;
import Core.APIHelper;

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
    }
    
    public void FinishEdits(String _DeckName){
        //Save file
        ThisDeck.DeckName = _DeckName;
        APIHelper Helper = new APIHelper();
        Helper.PostDeck(_DeckName,ThisDeck);
        FileSaver.CreateFile(ThisDeck,false);
        System.out.print("File Saved!");
    }
}
