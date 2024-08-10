import java.util.*;
import java.io.*;

public class Convertor{
   public static final String[] hours = {"one", "two", "three", "four", "five", "six","seven","eight","nine","ten","eleven","twelve"};
   public static String[] minutes = {"o'clock","five past","ten past","quarter past","twenty past","twenty five past","half past","twenty five to","twenty to","quarter to","ten to","five to"};
   
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a time or 'quit':");
      String timeString = sc.nextLine();
      
      do{
         if(timeString.equals("quit"))
            break;
         String[] time = timeString.split(":");
         int hour = Integer.parseInt(time[0]);
         int minute = Integer.parseInt(time[1]);
         
         String h = "";
         if (minute>=33){
            if(hour==12)
               h=hours[0];
            else
               h = hours[hour];
         }
         else{
            h = hours[hour-1];
         }
         
         String m = "";
         int min = (int)Math.round((double)minute/5);
         if (min == 0 || min == 12){
            m = minutes[0];
            if(minute%5!=0){
               System.out.println("about " + h + " " + m);
            }else{
               System.out.println(h + " " + m);
            }
         }else{
            m = minutes[min];
            if(minute%5!=0){
               System.out.println("about " + m + " " + h);
            }else{
               System.out.println(m+ " " + h);
            }
         }
         System.out.println("Enter a time or 'quit':");
         timeString = sc.nextLine();
      }while(!timeString.equals("quit"));
      System.out.println("Done");
      
   }
}