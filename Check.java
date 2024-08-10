import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Check{
   public String shape;
   public String colour;
   public String pattern;
   
   
   public Check(){}
   
   public Check(String s, String c, String p){
      shape = s;
      colour = c;
      pattern = p;
   }
   
   public static Check makeData(String line){
      String[] data = line.split(",");
      Check card = new Check(data[0], data[1], data[2]);
      return card;
   }
   
   public String toString(){
      return shape + "," + colour + "," + pattern;
   }
   
   public static void main(String [] args){
      
      Scanner fileInput = null;
      Scanner sc = new Scanner(System.in);
      
      try{
         System.out.println("Enter the name of the cards file:");
         String fileName = sc.nextLine();
         fileInput = new Scanner(new File(fileName));
      }catch(FileNotFoundException e){
         System.out.println("File not Found");
      }
      
      
      boolean match = true;
      while(fileInput.hasNextLine()){
         String line = fileInput.nextLine().trim();
         String[] card = line.split(" ");
         
         System.out.print("Processing: ");
         
         List<Check> cards = new ArrayList<>();
         for(String c: card){
            Check newCard = makeData(c);
            cards.add(newCard);
            System.out.print( c + " ");
         }
         System.out.println();
         
         Check ck = new Check();
         if(!ck.anagrams(cards.get(0).shape, cards.get(1).shape) && !ck.anagrams(cards.get(0).colour, cards.get(1).colour) && !ck.anagrams(cards.get(0).pattern, cards.get(1).pattern) && !ck.anagrams(cards.get(1).shape, cards.get(2).shape) && !ck.anagrams(cards.get(1).colour, cards.get(2).colour) && !ck.anagrams(cards.get(1).pattern, cards.get(2).pattern) && !ck.anagrams(cards.get(0).shape, cards.get(2).shape) && !ck.anagrams(cards.get(0).colour, cards.get(2).colour) && !ck.anagrams(cards.get(0).pattern, cards.get(2).pattern)){
            match = false;
         }
         if(ck.anagrams(cards.get(0).shape, cards.get(1).shape) && ck.anagrams(cards.get(0).colour, cards.get(1).colour) && ck.anagrams(cards.get(0).pattern, cards.get(1).pattern) && ck.anagrams(cards.get(1).shape, cards.get(2).shape) && ck.anagrams(cards.get(1).colour, cards.get(2).colour) && ck.anagrams(cards.get(1).pattern, cards.get(2).pattern) && ck.anagrams(cards.get(0).shape, cards.get(2).shape) && ck.anagrams(cards.get(0).colour, cards.get(2).colour) && ck.anagrams(cards.get(0).pattern, cards.get(2).pattern)){
            match = false;
         }
         
         if (match){ 
            System.out.println("Invalid");
         }
         else{
            System.out.println("Valid");
         }
         match = true;
      }
      
      
      System.out.println("Done");
      fileInput.close();
      
     
   }
   public boolean anagrams(String s1, String s2){
      List<Character> string1 = new ArrayList<>();
      for (int i = 0; i<s1.length(); i++){
         string1.add(s1.charAt(i));
      }
      Collections.sort(string1);
      
      List<Character> string2 = new ArrayList<>();
      for (int i = 0; i<s2.length(); i++){
         string2.add(s2.charAt(i));
      }
      Collections.sort(string2);
      String word1 = "";

      for(Character i : string1){
         word1 = word1 + i;
      }
      
      String word2 = "";
      for(Character i : string2){
         word2 = word2 + i;
      }
      
      if (word1.equalsIgnoreCase(word2))
         return true;
      else
         return false;
      
   }
}