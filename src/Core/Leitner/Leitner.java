package Core.Leitner;

import Core.FileSaver;
import java.util.*;

public class Leitner {

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