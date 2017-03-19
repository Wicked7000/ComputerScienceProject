package Core.Leitner;

import Core.Leitner.Holder;
import java.util.ArrayList;

public class Box {

    public int BoxNumber;
    public ArrayList<Holder> Questions;
    public int TimeConstant;
    public long TimeToSee = 0;

    public Box(int _BN, ArrayList<Holder> _Q, int _Time) {
        TimeConstant = _Time;
        BoxNumber = _BN;
        Questions = _Q;
        TimeToSee = System.currentTimeMillis() / 1000L + GetSpacing(TimeConstant);
    }

    //Used when a session has been completed!
    public void UpdateTime(){
        TimeToSee = System.currentTimeMillis() / 1000L + GetSpacing(TimeConstant);
    }
    
    public long GetSpacing(int TimeConstant){
        long Temp = 0;
        switch(TimeConstant){
            case -1:
                Temp = 0;
                break;
            case 0:
                //Add a hour!
                Temp = 3600;
                break;
            case 1:
                //Add a Day!
                Temp = 86400; 
                break;
            case 3:
                //Add a Week!
                Temp = 604800;
                break;
            case 4:
                //Add a month!
                Temp = 2629743;
                break;
            case 5:
                //Add a year!
                Temp = 31556926;
                break;
        }
        return Temp;
    }

    public void RemoveQuestion(Holder Question) {
        Questions.remove(Question);
    }

    public void AddQuestionHolder(Holder Question){
        if (Questions == null) {
            Questions = new ArrayList<Holder>();
        }
        Questions.add(Question);
    }
    
    public void AddQuestion(String Question, String AnswerPara) {
        if (Questions == null) {
            Questions = new ArrayList<Holder>();
        }
        Holder AddTo = new Holder(Question,AnswerPara);
        Questions.add(AddTo);
    }
}
