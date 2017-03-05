package Core;

import Core.Leitner.Deck;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.io.IOException;

public class FileSaver{
  public static void main(String[] args){
      Deck TestDeck = new Deck();
      TestDeck.Boxes[0].AddQuestion("Test123", "TestingThisFileSaver");
      TestDeck.Boxes[1].AddQuestion("ThisIsInBox2", "Box2Save");
      CreateFile(TestDeck);
  }
    
  public static void CreateFile(Core.Leitner.Deck TheDeck){
        List<String> XMLFileLines = new ArrayList<String>();
  	XMLFileLines.add("<deck>");
  	System.out.println(TheDeck.Boxes.length);
    	for(int X=0;X < TheDeck.Boxes.length;X++){
           XMLFileLines.add("<Box" + X + ">");
           	try{
	           for(int S=0;S < TheDeck.Boxes[X].Questions.size();S++){
	             XMLFileLines.add("<Card>");
	             XMLFileLines.add("<Question>" + TheDeck.Boxes[X].Questions.get(S).Question + "</Question>");
	             XMLFileLines.add("<Answer>" + TheDeck.Boxes[X].Questions.get(S).Answer + "</Answer>");
	             XMLFileLines.add("</Card>");
	           }
           	}
           	catch(Exception e){
           		System.out.println(e);
           	}
          XMLFileLines.add("</Box" + X + ">");
    	}
    	XMLFileLines.add("</deck>");
        
        System.out.print(System.getenv("LOCALAPPDATA") + "\\test.xml");
        Path file = Paths.get(System.getenv("LOCALAPPDATA") + "\\test.xml");
        try{
            Files.write(file,XMLFileLines,Charset.forName("UTF-8"));
            System.out.println("File Created");
        }catch(IOException e){
            e.printStackTrace();
        }
  }
}