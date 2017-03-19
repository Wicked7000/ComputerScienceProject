package Core.Leitner;

import Core.FileSaver;
import Core.Leitner.Holder;
import java.util.*;

public class Deck {

    public String DeckName;
    public Box[] Boxes;

    public Deck() {
        GenerateBoxes();
    }

    public void FinishSession(ArrayList<Holder> CorrectQuestions,ArrayList<Holder> IncorrectQuestions, int CurrentBox) {
        //Remove CorrectQuestions from their Current Box.
        for (Holder temp : CorrectQuestions) {
            Boxes[CurrentBox].RemoveQuestion(temp);
        }
        
        //Add those CorrectQuestions to the next box up.
        for(Holder temp2 : CorrectQuestions){
            int NewBox = CurrentBox+1;
            if((NewBox <= 4) == false){ NewBox = 4;}
            Boxes[NewBox].AddQuestionHolder(temp2);
        }
        
        //For the next review!
        Boxes[CurrentBox].UpdateTime();
        FileSaver.CreateFile(this,false);
    }

    ///TODO: DOCUMENT THIS FUNCTION
    public Map<String,Integer> NeedReview(){
        int BoxesForReview = 0;
        int TotalCards = 0;
        int CardsForReview = 0;
        for(int X=0;X < Boxes.length-1;X++){
            if(Boxes[X].TimeToSee <= (System.currentTimeMillis() / 1000L)){
                CardsForReview += Boxes[X].Questions.size();
                BoxesForReview += 1;
            }
            TotalCards += Boxes[X].Questions.size();
        }
        Map<String,Integer> Data = new HashMap<>();
        Data.put("Cards",CardsForReview);
        Data.put("TotalCards", TotalCards);
        return Data;
    }
    
    public void AddQuestion(String _Q, String _A) {
        Boxes[0].AddQuestion(_Q, _A);
    }
    
    //Document Function
    public void AddQuestionHolder(Holder TheHolder){
        Boxes[0].AddQuestionHolder(TheHolder);
    }
    

    public void AddBox(Box Y, int Number) {
        if (Boxes == null) {
            Boxes = new Box[4];
        }
        Boxes[Number] = Y;
    }

    public void GenerateBoxes() {
        for (int x = 0; x < 4; x++) {
            Box _Temp = new Core.Leitner.Box(x, null, x-1);
            _Temp.Questions = new ArrayList<>();
            AddBox(_Temp, x);       
        }
    }
}
