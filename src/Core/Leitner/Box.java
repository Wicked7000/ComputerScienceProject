package Core.Leitner;

import Core.Leitner.Holder;
import java.util.ArrayList;

public class Box {

    public int BoxNumber;
    public ArrayList<Holder> Questions;
    public int TimeToSee = 0;

    public Box(int _BN, ArrayList<Holder> _Q, int _Time) {
        BoxNumber = _BN;
        Questions = _Q;
        TimeToSee = _Time;
    }

    public void RemoveQuestion(Holder Question) {
        Questions.remove(Question);
    }

    public void AddQuestionHolder(Holder Question){
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
