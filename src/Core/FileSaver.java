package Core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Core.Leitner.Deck;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.io.IOException;
import java.util.stream.Collectors;
import java.io.BufferedReader;

public class FileSaver{
  public static void main(String[] args){
      LoadFile("test.xml");
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
  
  public static void LoadFile(String FilePath){
      Path FilePathFull = Paths.get(System.getenv("LOCALAPPDATA") + "\\" + FilePath);
      List<String> list = new ArrayList<>(); 
      try(BufferedReader Br = Files.newBufferedReader(FilePathFull)){
          list = Br.lines().collect(Collectors.toList());
      }catch(IOException e){
          //Error Occured
      }
      
      Core.Leitner.Deck CreatedDeck = new Deck();
      int SelectedBoxIndex = -1;
      Core.Leitner.Holder Temp = new Core.Leitner.Holder("Test","Test");
      
      for(int I=0;I < list.toArray().length;I++){
          String Text = list.toArray()[I].toString();
          Pattern regexString = Pattern.compile(Pattern.quote("<") + "(.*?)" + Pattern.quote(">"));
          Matcher M = regexString.matcher(Text);
          M.find();
          Pattern Between = Pattern.compile(Pattern.quote(">") + "(.*?)" + Pattern.quote("<"));
          Matcher Bet = Between.matcher(Text);
          Bet.find();
          System.out.println(M.group(1));
          System.out.println(Bet.groupCount());
          if(M.group(1).contains("Box") && M.group(1).charAt(0) != '/'){
              SelectedBoxIndex = Integer.parseInt(M.group(1).substring(3, 4));
          }else if(M.group(1).contains("Question") && M.group(1).charAt(0) != '/'){
              Temp.Question = Bet.group(0);
              String NewText = list.toArray()[I+1].toString();
              Matcher Bet2 = Between.matcher(NewText);
              Bet2.find();
              Temp.Answer = Bet2.group(0);
              CreatedDeck.Boxes[SelectedBoxIndex].AddQuestionHolder(Temp);
              I = I +1;
          }
      }
      System.out.println(CreatedDeck.Boxes[0].Questions.get(0).Question);
      
    }
  }