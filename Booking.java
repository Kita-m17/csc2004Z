import java.io.*;
import java.util.*;

public class Booking{
   
   class Time{
      int hour;
      int minute;
      String dayTime;
      
      public Time(int hour, int minute, String dayTime){
         this.hour = hour;
         this.minute = minute;
         this.dayTime = dayTime;
         
         if (dayTime.equals("pm")){
            if(this.hour!=12){
               this.hour+=12;
            }
         }
         
      }
      
      public Time(String string){
         String[] timeSplit = string.split(":");
         int hours = Integer.parseInt(timeSplit[0]);
         int minute = Integer.parseInt(timeSplit[1]);
         String dayTime = timeSplit[2];
         Time time = new Time(hours, minute, dayTime);
      }
      
      public int getHours(){
         return hour;
      }
      
      public int getMinutes(){
         return minute;
      }
      
      public int getTotalMinutes(Time time){
         if(time.dayTime.equals("pm"))
            return (time.hour*60 + time.minute) + (12*60);
         return (time.hour*60 + time.minute) + (12*60);
      }
      
      public int compareTo(Time other){
         if(this.hour > other.hour){
            return 1;
         }else if(this.hour < other.hour){
            return -1;
         }else{
            if(this.minute<other.minute){
               return 1;
            }else if(this.minute>other.minute){
               return -1;
            }else{
               return 0;
            }
         }
      }
      
   }
   
   class TimeSlot{
      Time start;
      Time end;
      
      public TimeSlot(Time start, Time end){
         this.start = start;
         this.end = end;
      }
      
      public boolean clash(TimeSlot other){
         if((this.end).compareTo(other.start)<=0 || (this.start).compareTo(other.end) > 0){
            return false;
         }else{
            return true;
         }
      }
   }
   
  
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the number of bookings:");
      int numBookings = Integer.parseInt(sc.nextLine());
      
      int counter = 0;
      List<String> bookings = new ArrayList<>();
      System.out.println("Enter the bookings, one per line:");
      int clashes = 0;
      List<Time> times = new ArrayList<>();
      List<TimeSlot> bookings = new ArrayList<>();
      
      while(counter<numBookings){
         times.clear();
         String booking = sc.nextLine();
         
         bookings.add(booking);
         counter++;
      }
      
      for(String i: bookings){
         System.out.println(
      }
      
   }
}