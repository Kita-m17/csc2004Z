import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class Oddities{
   static int[] months = {31,28,31,30,31,30,31,31,30,31,30,31};
   
   public static boolean isOdd(int value){
      if(value%2==1)
         return true;
      else
         return false;
   }
   
   public static void isLeap(int year){
      if((year%400 == 0 || year%4 == 0)&& year%100!=0){
         months[1] = 29;
      }
   }

   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      Scanner fileInput = null;
      System.out.println("Enter the name of the text file of dates:");
      String fileName = sc.nextLine();
      
      try{
         fileInput = new Scanner(new File(fileName));
         //fileInput.close();
      }catch(FileNotFoundException e){
         System.out.println("file not found");
      }
      while(fileInput.hasNextLine()){
         String line = fileInput.nextLine();
         String date[] = line.split("/");
         
         int day = Integer.parseInt(date[0]);
         int month = Integer.parseInt(date[1]);
         int year = Integer.parseInt(date[2]);
         
         
         if(isOdd(day) == true && isOdd(month) == true && isOdd(year)==true ){
            System.out.println("The date " +  line + " has all odd digits.");
         }
         
         int count = 0;
         
         if(year%2==0){
            for(int i = 0; i<months.length; i++){
               count = count + months[month - 1] + months[0] - day;
            }
            year++;
            
            month = 1;
            day = 1;
         }
         
         if(month%2==0){
            if(month == 12){
               for(int i = 0; i<months.length; i++){
                  count = count + months[0] - day;
               }
               year++;
               month++;
            }
            month++;
            count = count + months[month-1] - day;
         }
         if(day%2 == 0){
            if(day == months[month]){
               month++;
            }
            day++;
            count += 1;
         }
         
      }
   }
}