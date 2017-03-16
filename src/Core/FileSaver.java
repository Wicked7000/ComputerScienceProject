package Core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Core.Leitner.Deck;
import java.util.*;
import java.nio.file.*;
import java.io.File;
import java.nio.charset.Charset;
import java.io.IOException;
import java.util.stream.Collectors;
import java.io.BufferedReader;

public class FileSaver{
    
  public static Path FilePath = Paths.get(System.getenv("LOCALAPPDATA") + "\\ReviseFaster");
    
  public static void main(String[] args){
      Core.Leitner.Deck NewDeck = new Core.Leitner.Deck();
      NewDeck.AddQuestionHolder(new Core.Leitner.Holder("abcd","1234"));

      CreateFile(NewDeck,"Maths");
  }
    
  public static void CreateFilePath(){
      File Dir = new File(FilePath.toString());
      if(!Dir.exists()){
          Dir.mkdir();
      }    
  }
  
  public static ArrayList<String> ListDecks(){
      CreateFilePath();
      File Dir = new File(FilePath.toString());
      ArrayList<String> Files = new ArrayList<String>();
      for(File FileEntry : Dir.listFiles()){
          System.out.println(FileEntry.getName());
          Files.add(FileEntry.getName().toString());
      }
      return Files;
  }
  
  public static void CreateFile(Core.Leitner.Deck TheDeck,String DeckName){
    List<String> XMLFileLines = new ArrayList<String>();
    System.out.println(TheDeck.Boxes[0].TimeToSee);
  	XMLFileLines.add("<deck>");
  	System.out.println(TheDeck.Boxes.length);
    	for(int X=0;X < TheDeck.Boxes.length;X++){
           XMLFileLines.add("<Box" + X + ">");
           System.out.println(X);
           XMLFileLines.add("<TimeToSee>"+ TheDeck.Boxes[X].TimeToSee +"</TimeToSee>");
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
        
        Path file = Paths.get(System.getenv("LOCALAPPDATA") + "\\ReviseFaster\\"+DeckName+".xml");
        try{
            Files.write(file,XMLFileLines,Charset.forName("UTF-8"));
            System.out.println("File Created");
        }catch(IOException e){
            e.printStackTrace();
        }
  }
  
  public static Core.Leitner.Deck LoadFile(String FileName){
      Path FilePathFull = Paths.get(System.getenv("LOCALAPPDATA") + "\\ReviseFaster\\" + FileName);
      List<String> list = new ArrayList<>(); 
      try(BufferedReader Br = Files.newBufferedReader(FilePathFull)){
          list = Br.lines().collect(Collectors.toList());
      }catch(IOException e){
          //Error Occured
      }
      
      //Create an Empty deck to append to!
      Core.Leitner.Deck CreatedDeck = new Deck();
      int SelectedBoxIndex = -1;
      Core.Leitner.Holder Temp = new Core.Leitner.Holder("Test","Test");
      
      for(int I=0;I < list.toArray().length;I++){
          String Text = list.toArray()[I].toString();
          
          //Get First Set of Text Between <HERE>
          Pattern regexString = Pattern.compile(Pattern.quote("<") + "(.*?)" + Pattern.quote(">"));
          Matcher M = regexString.matcher(Text);
          M.find();
          
          //Get the Text Between the two <>HERE<>
          Pattern Between = Pattern.compile(Pattern.quote(">") + "(.*?)" + Pattern.quote("<"));
          Matcher Bet = Between.matcher(Text);
          Bet.find();
          
          //Get the first Bracket that contains box and set the current selected box to that Index!
          if(M.group(1).contains("Box") && M.group(1).charAt(0) != '/'){
              SelectedBoxIndex = Integer.parseInt(M.group(1).substring(3, 4));  
          }
          else if(M.group(1).contains("Question") && M.group(1).charAt(0) != '/'){
              //Sets the Question
              Temp.Question = Bet.group(0).substring(1,Bet.group(0).length()-1);
              
              //Sets the Answer ( And Finds it! )
              String NewText = list.toArray()[I+1].toString();
              Matcher Bet2 = Between.matcher(NewText);
              Bet2.find();
              Temp.Answer = Bet2.group(0).substring(1,Bet2.group(0).length()-1);
              CreatedDeck.Boxes[SelectedBoxIndex].AddQuestionHolder(Temp);
              I = I +1;
          }
      }
      return CreatedDeck;
    }
  }