package Core.Leitner;

import Core.Leitner.Holder;
import java.util.ArrayList;

public class Deck {

    public String DeckName;
    public Box[] Boxes;

    public Deck() {
        GenerateBoxes();
    }

    public void FinishSession(ArrayList<Holder> CorrectQuestions, int CurrentBox) {
        for (Holder temp : CorrectQuestions) {
            Boxes[CurrentBox].RemoveQuestion(temp);
        }
        
        for(Holder temp2 : CorrectQuestions){
            Boxes[CurrentBox].AddQuestionHolder(temp2);
        }
    }

    ///TODO: DOCUMENT THIS FUNCTION
    public boolean NeedReview(){
        int BoxesForReview = 0;
        int CardsForReview = 0;
        for(int X=0;X < Boxes.length-1;X++){
            if(Boxes[X].TimeToSee <= System.currentTimeMillis() / 1000L){
                CardsForReview += Boxes[X].Questions.size();
                BoxesForReview += 1;
            }
        }
        if(BoxesForReview > 1){
            return true;
        }
        return false;
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
            AddBox(_Temp, x);
        }
    }
}
