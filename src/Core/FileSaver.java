package Core;

import java.util.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.io.IOException;

public class FileSaver{
  static void CreateFile(Core.Deck TheDeck){
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
        
        Path file = Paths.get("test.xml");
        try{
            Files.write(file,XMLFileLines,Charset.forName("UTF-8"));
            System.out.println("File Created");
        }catch(IOException e){
            e.printStackTrace();
        }
  }
}