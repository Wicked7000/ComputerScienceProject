package Core;

import java.util.*;

class Holder {

    public String Question = null;
    public String Answer = null;
}

class LeitnerSystem {

    public static Deck SelectedDeck;

    public static void main(String[] args) {
        LoadDeck();
        StartSession();
        StartSession();
    }

    public static void StartSession() {
        System.out.println(SelectedDeck.DeckName);

        Box SelectedBox = null;
        int SelectedBoxInt = 0;

        for (int x = 0; x < 9; x++) {
            if (SelectedDeck.Boxes[x].TimeToSee == 0) {
                SelectedBox = SelectedDeck.Boxes[x];
                SelectedBoxInt = x;
                break;
            }
        }
        if (SelectedBox.Questions.size() <= 0) {
            System.out.println("No Reviews to be done!");
            return;
        }

        Scanner terminalInput = new Scanner(System.in);

        ArrayList<Holder> CorrectQuestions = new ArrayList<Holder>();
        for (int X = 0; X < SelectedBox.Questions.size(); X++) {
            System.out.println(SelectedBox.Questions.get(X).Question);
            String s = terminalInput.nextLine();
            if (s.trim().toLowerCase().equals(SelectedBox.Questions.get(X).Answer.toLowerCase())) {
                System.out.println("Correct!");
                CorrectQuestions.add(SelectedBox.Questions.get(X));
            }
        }

        SelectedDeck.FinishSession(CorrectQuestions, SelectedBoxInt);
        FileSaver.CreateFile(SelectedDeck);
    }

    public static void LoadDeck() {
        Deck TheDeck = new Deck();
        TheDeck.DeckName = "Physics";

        TheDeck.AddQuestion("What is the unit between the distance and sun", "AU");
        TheDeck.AddQuestion("Will the Train fit in the tunnel Yes or No", "Yes");

        SelectedDeck = TheDeck;

    }

}

class Deck {

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

    public void AddQuestion(String _Q, String _A) {
        Boxes[0].AddQuestion(_Q, _A);
    }

    public void AddBox(Box Y, int Number) {
        if (Boxes == null) {
            Boxes = new Box[10];
        }
        Boxes[Number] = Y;
    }

    public void GenerateBoxes() {
        for (int x = 0; x < 9; x++) {
            Box _Temp = new Box(x, null, x * 5);
            AddBox(_Temp, x);
        }
    }
}

class Box {

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
        Holder AddTo = new Holder();
        AddTo.Question = Question;
        AddTo.Answer = AnswerPara;
        Questions.add(AddTo);
    }
}
