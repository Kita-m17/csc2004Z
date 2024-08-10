import java.io.*;
import java.util.*;

public class Check{
   public String shape;
   public String colour;
   public String pattern;
   
   public Check(String shape, String colour, String pattern){
      this.shape = shape;
      this.colour = colour;
      this.pattern = pattern;
   }
   
   public Check(){}
   
   public String toString(){
      return shape+","+colour+","+pattern;
   }
   
   public static void main(String[] args){
      Scanner scanner = new Scanner(System.in);
      Scanner fileInput = null;
      System.out.println("Enter the name of the cards file:");
      String fileName = scanner.nextLine();
      
      try{
         fileInput = new Scanner(new File(fileName));
      }catch(FileNotFoundException e){
         System.out.println("File not found");
      }
      
      while(fileInput.hasNextLine()){
         String line = fileInput.nextLine();
         String[] cards = line.split(" ");
         
         List<Check> allCards = new ArrayList<>();
         
         for(String c: cards){
            String[] attributes = c.split(",");
            Check card = new Check(attributes[0], attributes[1], attributes[2]);
            allCards.add(card);
         }
         
         boolean same = false;
         Check ck = new Check();
         if(ck.isAnagram((allCards.get(0).shape),(allCards.get(1).shape))==true && ck.isAnagram((allCards.get(1).shape),(allCards.get(2).shape))==true && ck.isAnagram((allCards.get(0).shape),(allCards.get(2).shape))==true && ck.isAnagram((allCards.get(0).colour),(allCards.get(1).colour))==true && ck.isAnagram((allCards.get(1).colour),(allCards.get(2).colour))==true && ck.isAnagram((allCards.get(0).colour),(allCards.get(2).colour))==true  && ck.isAnagram((allCards.get(0).pattern),(allCards.get(1).pattern))==true && ck.isAnagram((allCards.get(1).pattern),(allCards.get(2).pattern))==true && ck.isAnagram((allCards.get(0).pattern),(allCards.get(2).pattern))==true ){
            same = true;
         }
         if(ck.isAnagram((allCards.get(0).shape),(allCards.get(1).shape))==false && ck.isAnagram((allCards.get(1).shape),(allCards.get(2).shape))==false && ck.isAnagram((allCards.get(0).shape),(allCards.get(2).shape))==false && ck.isAnagram((allCards.get(0).colour),(allCards.get(1).colour))==false && ck.isAnagram((allCards.get(1).colour),(allCards.get(2).colour))==false && ck.isAnagram((allCards.get(0).colour),(allCards.get(2).colour))==false && ck.isAnagram((allCards.get(0).pattern),(allCards.get(1).pattern))==false && ck.isAnagram((allCards.get(1).pattern),(allCards.get(2).pattern))==false && ck.isAnagram((allCards.get(0).pattern),(allCards.get(2).pattern))==false){
            same = true;
         }
         System.out.println("Processing: " + line);
         if(same==true){
            System.out.println("Valid");
         }else{
            System.out.println("Invalid");
         }
         
      }
      System.out.println("Done");
      
   }
   
   public boolean isAnagram(String word1, String word2){
      List<Character> w1 = new ArrayList<>();
      List<Character> w2 = new ArrayList<>();
      
      for(int i = 0; i<word1.length(); i++){
         w1.add(word1.charAt(i));
      }
      for(int i = 0; i<word2.length(); i++){
         w2.add(word2.charAt(i));
      }
      Collections.sort(w1);
      Collections.sort(w2);
      
      word1 = "";
      word2 = "";
      
      for(Character i: w1){
         word1+=i;
      }
      for(Character i: w2){
         word2+=i;
      }
      return word1.equalsIgnoreCase(word2)==true;
      
   }
   
   
}