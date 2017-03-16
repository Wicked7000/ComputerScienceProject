package Core.Leitner;

import Core.FileSaver;
import java.util.*;

public class Leitner {

    public static Deck SelectedDeck;
    public static Box CurrentBox;
    public static int CurrentQuestion;
    
    public Leitner(Deck _SelectedDeck){
        SelectedDeck = _SelectedDeck;
        CurrentBox = GetNextBox(SelectBox());
    }
    
    public static ArrayList<Box> SelectBox(){
        ArrayList<Box> StackBoxes = new ArrayList<>();
        for(int I=0;I < SelectedDeck.Boxes.length;I++){
            long CurrentTime = System.currentTimeMillis() / 1000L;
            if(CurrentTime >= SelectedDeck.Boxes[I].TimeToSee){
                StackBoxes.add(SelectedDeck.Boxes[I]);
            }
        }
        return StackBoxes;
    }
    
    public Core.Leitner.Box GetNextBox(ArrayList<Box> Stack){
        if(Stack.size() > 0){
            CurrentQuestion = 0;
            return Stack.get(0);
        }
        return null;
    }
    
    public String ReadQuestion(){
        if(CurrentQuestion < CurrentBox.Questions.size()){
            Holder QuestionHolder = CurrentBox.Questions.get(CurrentQuestion);
            return QuestionHolder.Question;
        }else{
            return null;
        }
    }
}