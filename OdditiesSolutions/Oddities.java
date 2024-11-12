import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class Oddities{
   //array storing the number of days in each month(Febrary is default 28 days, not accounting for leap years initially)
   static int[] months = {31,28,31,30,31,30,31,31,30,31,30,31};

   //method to check if a given integer is odd
   public static boolean isOdd(int value){
      return (value%2==1);
   }

   //method to adjust the daysin Febrauary, given that the year is a leap year
   public static void isLeap(int year){
      //Febrary has 29 days in a leap year
      if((year%400 == 0 || year%4 == 0)&& year%100!=0){
         months[1] = 29;
      }
   }

   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      Scanner fileInput = null;
      System.out.println("Enter the name of the text file of dates:");
      String fileName = sc.nextLine();

      //attempt to open the file and handle any exceptions
      try{
         fileInput = new Scanner(new File(fileName));
      }catch(FileNotFoundException e){
         System.out.println("file not found");
      }

      //process each line in the file, whereby each line contains a date in the format day/month/year
      while(fileInput.hasNextLine()){
         String line = fileInput.nextLine();

         //split the date into day, month, year
         String date[] = line.split("/");

         //convert the day, month and year into integers
         int day = Integer.parseInt(date[0]);
         int month = Integer.parseInt(date[1]);
         int year = Integer.parseInt(date[2]);
         
         //check if day month and year are all odd numbers
         if(isOdd(day) == true && isOdd(month) == true && isOdd(year)==true ){
            System.out.println("The date " +  line + " has all odd digits.");
         }
         
         int count = 0;

         //if the year is even, adjust the coubt by iterating through the months, staring with the next year's first month
         if(year%2==0){
            for(int i = 0; i<months.length; i++){
               count = count + months[month - 1] + months[0] - day;
            }
            year++;//increment to the next year
            //resert the day and month
            month = 1;
            day = 1;
         }

         //if the month is uneven, adjust the count and move to the next month, but to the next year if month is December
         if(month%2==0){
            if(month == 12){
               for(int i = 0; i<months.length; i++){
                  count = count + months[0] - day;
               }
               year++;//increment to the next year
               month++;//move to january (overflow from December_
            }
            month++;//move to january
            count = count + months[month-1] - day;//add remaining days in the  current month to count
         }

         //if the day is even, increment to the next day, or next month, if its the last day of the month
         if(day%2 == 0){
            if(day == months[month]){
               month++; //move to next month
            }
            day++; //move to next day
            count += 1; //increment count for single day advancement
         }
         
      }
   }
}
