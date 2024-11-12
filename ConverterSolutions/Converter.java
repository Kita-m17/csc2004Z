import java.io.*;
import java.util.*;

public class Converter {
    HashMap<String, String> allMin;
    String hours;
    String minute;

    public static final String[] allHours  = {"twelve", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven"};
    public Converter(){
        allMin = new HashMap<String, String>();
    }

    public String getHours(String hour){
        int h = Integer.parseInt(hour);
        String finHour = "";
        for (int i = 0; i<allHours.length; i++){
            if (i==h){
                finHour = allHours[h];
            }
        }
        return finHour;
    }

    public HashMap<String, String> allMinutes(){
        allMin.put("00", " o'clock");
        allMin.put("0", " o'clock");
        allMin.put("5", "five past ");
        allMin.put("05", "five past ");
        allMin.put("10", "ten past ");
        allMin.put("15", "quarter past ");
        allMin.put("20", "twenty past ");
        allMin.put("25", "twenty five past ");
        allMin.put("30", "half past ");
        allMin.put("55", "five to ");
        allMin.put("50", "ten to ");
        allMin.put("45", "quarter to ");
        allMin.put("40", "twenty to ");
        allMin.put("35", "twenty five to ");
        return allMin;
    }
    
    public String getMin(String minutess){
        Converter conV = new Converter();
        allMinutes();
        
        // String minutes = ""+minute
        int min = Integer.parseInt(minutess);
        
        int res = min%5;
        String newMin;
        String minutes = "";

        if(minutess.equals("0")){
            minutes = this.hours + allMin.get(minutess);
            return minutes;
        }

        if (res == 0){
            minutes = allMin.get(minutess);
            return minutes;
        }
        
        if (res < 3 ){
            newMin = String.valueOf(min-res);
            if(allMin.get(newMin).equals(" o'clock"))
                minutes =  "about " + this.hours + allMin.get(newMin);
            else
                minutes = "about " + allMin.get(newMin);
            return minutes;
        }
        else if(res >=3){
            int ans = 5-res;
            newMin = String.valueOf(min+ans);
            minutes =  "about " + allMin.get(newMin);
            return minutes;
        }
        return minutes;
        
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String time;
        do{
            Converter conV = new Converter();
            
            System.out.println("Enter a time or 'quit':");
            time = scanner.nextLine();
            String[] getTime = time.split(":");
            String hour = getTime[0];
            String min = getTime[1];
            
            if(min.charAt(1) == '0'){
                min = min.substring(1);
            }

            int intMin = Integer.parseInt(min);
            if(intMin > 30){
                int intHour = Integer.parseInt(hour);
                intHour = intHour + 1;
                if (intHour == 24){
                    intHour = 0;
                }
                String newHour = "" + intHour;
                conV.hours = conV.getHours(newHour);
            }
            else{
                conV.hours = conV.getHours(hour);
            }
            conV.minute = conV.getMin(min);
            if (conV.minute.contains(" o'clock")){
                System.out.println(conV.minute);
            }
            else{
                System.out.print(conV.minute);
                System.out.println(conV.hours);
            }
        }
        while(!time.equals("quit"));
        scanner.close();
    }
}