package Core.Leitner;

import Core.FileSaver;
import java.util.*;

//TODO:DOCUMENT WHOLE FILE

public class Leitner {

    public static Deck SelectedDeck;
    public static Box CurrentBox;
    public static int BoxNumber;
    public static int CurrentQuestion;
    public static ArrayList<Integer> Stack;
    
    public static ArrayList<Holder> CorrectQuestions;
    public static ArrayList<Holder> IncorrectQuestions;
    
    public Leitner(Deck _SelectedDeck){
        SelectedDeck = _SelectedDeck;
        CorrectQuestions = new ArrayList<>();
        IncorrectQuestions = new ArrayList<>();
        CurrentBox = GetNextBox(SelectBox());
    }
    
    public void ResetForNextBox(){
        CurrentQuestion = 0;
        CorrectQuestions = new ArrayList<>();
        IncorrectQuestions = new ArrayList<>();
    }
    
    public int IsNextQuestion(){
        if(CurrentBox.Questions.size() <= CurrentQuestion){
            //If the curretn Box has run out move onto the next one!
            if(Stack.size() > 0){
                FinishAndSave();
                ResetForNextBox();
                CurrentBox = GetNextBox(Stack);
                return 1;
            }
            return -1;
        }
        else{ return 1;}
    }
    
    public static ArrayList<Integer> SelectBox(){
        ArrayList<Integer> StackBoxes = new ArrayList<>();
        for(int I=0;I < SelectedDeck.Boxes.length;I++){
            long CurrentTime = System.currentTimeMillis() / 1000L;
            if(CurrentTime >= SelectedDeck.Boxes[I].TimeToSee && SelectedDeck.Boxes[I].Questions.size() > 0){
                StackBoxes.add(I);
            }
        }
        Stack = StackBoxes;
        return StackBoxes;
    }
    
    public Core.Leitner.Box GetNextBox(ArrayList<Integer> Stack){
        if(Stack.size() > 0){
            for(int X=0;X < Stack.size();X++){
                CurrentQuestion = 0;
                BoxNumber = Stack.get(X);
                Stack.remove(X);
                return SelectedDeck.Boxes[BoxNumber];
            }
        }
        return null;
    }
    
    public void NextQuestion(){
        CurrentQuestion+= 1;
    }
    
    public String ReadQuestion(){
        if(CurrentQuestion <= CurrentBox.Questions.size()){
            Holder QuestionHolder = CurrentBox.Questions.get(CurrentQuestion);
            return QuestionHolder.Question;
        }else{
            return null;
        }
    }
    
    public void AddCorrectQuestion(){
        CorrectQuestions.add(CurrentBox.Questions.get(CurrentQuestion));
    }
    
    public void AddIncorrectQuestion(){
        IncorrectQuestions.add(CurrentBox.Questions.get(CurrentQuestion));
    }
    
    public Map<Boolean,String> ReadAndCheckAnswer(String InputAnswer){
        if(CurrentQuestion < CurrentBox.Questions.size()){
            Holder AnswerHolder = CurrentBox.Questions.get(CurrentQuestion);
            Map<Boolean,String> map = new HashMap<>();
            if(InputAnswer.equals(AnswerHolder.Answer)){
                map.put(true,AnswerHolder.Answer);
                return map;
            }else{
                map.put(false,AnswerHolder.Answer);
                return map;
            }
        }else{
            return null;
        }
    }
    
    //Save Progress!
    public void FinishAndSave(){
        SelectedDeck.FinishSession(CorrectQuestions, IncorrectQuestions,BoxNumber);
    }
}