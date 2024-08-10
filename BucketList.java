// import java.io.*;
// import java.util.*;
// import java.io.File;
// import java.io.FileNotFoundException;
// 
// public class BucketList{
//    public static void main(String[] args){
//       Scanner sc = new Scanner(System.in);
//       Scanner fileInput = null;
//       System.out.println("Enter the name of the categories file:");
//       String fileName = sc.nextLine();
//       try{
//          fileInput = new Scanner(new File(fileName));
//       }catch(FileNotFoundException e){
//          System.out.println("File not found");
//       }
//       System.out.println("Enter a comma-separated list of words:");
//       String wordList = sc.nextLine();
//       String[] words = wordList.split(", ");
//       System.out.println("Categorised:");
//       List<String> categories = new ArrayList<>();
//       List<String> copy = new ArrayList<>();
//       
//       Map<String, List<String>> categoriesMap = new HashMap<>();
//       while(fileInput.hasNextLine()){
//          String word = fileInput.nextLine();
//          categories.add(word);
//          copy.add(word);
//       }
//       
//       Collections.sort(copy);
//       Collections.reverse(copy);
//       System.out.println(copy);
//       
//       List<String> wordes = new ArrayList<>();
//       
//       for(int i = 0; i<copy.size()-1; i++){
//          for(String w: words){
//             if(w.startsWith(copy.get(i)) && w.startsWith(copy.get(i+i)) && copy.get(i).compareTo(copy.get(i+1))>0){
//                wordes.add(w);
//             }
//          }
//          wordes.clear();
//          categoriesMap.put(copy.get(i), wordes);
//       }
//       for(Map.Entry m: categoriesMap.entrySet()){
//          System.out.println(m.getKey() + ": " +m.getValue());
//       }
//       System.out.println("Done");
//       
//    }
// }

// import java.io.*;
// import java.util.*;
// 
// public class BucketList{

   // public String getBeginning(List<String> categories, String word){
//       String start = "";
//       int length = 0;
//       for(String category: categories){
//          if(word.startsWith(category)){
//             if(category.length()>length){
//                length = category.length();
//                start = category;
//             }
//          }
//       }
//       return start;
//    }
 //   public static void main(String[] args){
//       Scanner sc = new Scanner(System.in);
//       Scanner fileInput = null;
//       System.out.println("Enter the name of the categories file:");
//       String fileName = sc.nextLine();
//       
//       try{
//          fileInput = new Scanner(new File(fileName));
//       }catch(FileNotFoundException e){
//          System.out.println("File not found");
//       }
//       
//       List<String> categories = new ArrayList<>();
//       Map<String, String> categorised = new HashMap<String, String>();
//       
//       while(fileInput.hasNextLine()){
//          String word = fileInput.nextLine();
//          categories.add(word);
//          categorised.put(word, new ArrayList<>(););
//       }
//       
//       System.out.println("Enter a comma-separated list of words:");
//       String line = sc.nextLine();
//       String[] strings = line.split(", ");
//       
//       System.out.println("Categorised:");
//       BucketList bl = new BucketList();
//       
//       for(String word: strings){
//          String start = bl.getBeginning(categories, word);
//          String categoryVal = categorised.get(start);
//          String output = String.join(", ", categoryVal, word);
//          if(categoryVal == "" || categoryVal == null){
//             output = word;
//          }
//          categorised.put(start, output);
//       }
//       
//       for(Map.Entry<String, String> c: categorised.entrySet()){
//          if(c.getKey() != ""){
//             String o = String.format("%s:", c.getKey());
//             if(c.getValue() != ""){
//                o = String.format("%s: %s.", c.getKey(), c.getValue());
//             }
//             System.out.println(o);
//          }
//       }
//       
//       if(categorised.get("") != null){
//          System.out.println("Uncategorised:");
//          System.out.printf("%s.\n", categorised.get(""));
//       }
//       
//       fileInput.close();
//       System.out.println("Done");
//    }
// }
import java.io.*;
import java.util.*;

public class BucketList{
   // public String getStart(List<String>
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      Scanner fileInput = null;
      System.out.println("Enter the name of the categories file:");
      String fileName = sc.nextLine();
      try{
         fileInput = new Scanner(new File(fileName));
      }catch(FileNotFoundException e){
         System.out.println("File not found");
      }
      List<String> categories = new ArrayList<>();
      List<String> copy = new ArrayList<>();
      while(fileInput.hasNextLine()){
         String word = fileInput.nextLine();
         categories.add(word);
         copy.add(word);
      }
      categories.add("Uncategorised");
      copy.add("Uncategorised");
      Collections.sort(copy);
      Collections.reverse(copy);
      
      System.out.println("Enter a comma-separated list of words:");
      String line = sc.nextLine();
      String[] strings = line.split(", ");
      
      System.out.println("Categoriesed:");
      Map<String, List<String>> categorised = new HashMap<String, List<String>>();
      for(String category: copy){
         categorised.put(category, new ArrayList<>());
      }
      
      
      for(String w: strings){
         boolean categorise = false;
         int length = 0;
         for(String c: categories){
            if(w.startsWith(c)){
               if(length<c.length()){
                  length = c.length();
                  categorised.get(c).add(w);
                  categorise = true;
               }
            }
         }
         if(categorise == false){
            categorised.get("Uncategorised").add(w);
         }
      }
      
      //for(String c: categories){
//          //System.out.println(c + ": " + categorised.get(c));
//          System.out.print(c + ": ");
         for(Map.Entry<String, List<String>> set: categorised.entrySet()){
            if (set.getKey() != "Uncategorised") {
                String output = String.format("%s:",set.getKey());
                if (set.getValue() != null) {
                    output = String.format("%s: %s.", set.getKey(), set.getValue());
                }
                System.out.println(output);   
            }
         }
         if(categorised.get("Uncategorised")!=null){
            System.out.print("Uncategorised:");
            System.out.printf("%s.%n", categorised.get("Uncategorised"));
         }
      //}
   }
}