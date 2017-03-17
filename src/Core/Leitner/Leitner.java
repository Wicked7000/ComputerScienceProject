package Core.Leitner;

import Core.FileSaver;
import java.util.*;

//TODO:DOCUMENT WHOLE FILE

public class Leitner {

    public static Deck SelectedDeck;
    public static Box CurrentBox;
    public static int CurrentQuestion;
    
    public static ArrayList<Holder> CorrectQuestions;
    public static ArrayList<Holder> IncorrectQuestions;
    
    public Leitner(Deck _SelectedDeck){
        SelectedDeck = _SelectedDeck;
        CorrectQuestions = new ArrayList<>();
        IncorrectQuestions = new ArrayList<>();
        CurrentBox = GetNextBox(SelectBox());
    }
    
    public int IsNextQuestion(){
        System.out.println(CurrentBox.Questions.size());
        System.out.println(CurrentQuestion);
        if(CurrentBox.Questions.size() <= CurrentQuestion){
         return -1;
        }
        else{ return 1;}
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
    
    public void NextQuestion(){
        CurrentQuestion++;
    }
    
    public String ReadQuestion(){
        if(CurrentQuestion < CurrentBox.Questions.size()){
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
        System.out.println(CurrentBox.Questions.get(CurrentQuestion));
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
}