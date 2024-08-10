import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Ships{
   double x;
   double y;
   double a;
   double b;
   double c;
   
   public Ships(double a, double b, double c,double x, double y){
      this.a = a;
      this.b = b;
      this.c = c;
      this.x = x;
      this.y = y;
   }
   public Ships(){}
   
   public String toString(){
      return "course: " + a+"x+"+b+"b+"+c+" position: ("+x+","+y+")";
   }
   
   
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      Scanner fileInput = null;
      System.out.println("Enter the name of the ship data file:");
      String fileName = sc.nextLine();
      try{
         fileInput = new Scanner(new File(fileName));
      }catch(FileNotFoundException e){
         System.out.println("File not found");
      }
      int count = 0;
      while(fileInput.hasNextLine()){
         count++;
         String shipOneData = fileInput.nextLine();
         String shipTwoData = fileInput.nextLine();
         String[] shipOne = shipOneData.split(" ");
         String[] shipTwo = shipTwoData.split(" ");
         Ships s1 = new Ships(Double.parseDouble(shipOne[0]), Double.parseDouble(shipOne[1]), Double.parseDouble(shipOne[2]), Double.parseDouble(shipOne[3]), Double.parseDouble(shipOne[4]));
         Ships s2 = new Ships(Double.parseDouble(shipTwo[0]), Double.parseDouble(shipTwo[1]), Double.parseDouble(shipTwo[2]), Double.parseDouble(shipTwo[3]), Double.parseDouble(shipTwo[4]));
         
         double x1 = ((s1.b*s2.c)-(s2.b*s1.c))/((s1.a*s2.b)-(s2.a*s1.b)) + 0.0;
         double y1 = ((s2.a*s1.c)-(s1.a*s2.c))/((s1.a*s2.b)-(s2.a*s1.b)) + 0.0;
         
         double s1Dist = Math.sqrt(Math.pow((s1.x-x1),2)+Math.pow((s1.y-y1),2))+ 0.0;
         double s2Dist = Math.sqrt(Math.pow((s2.x-x1),2)+Math.pow((s2.y-y1),2))+ 0.0;
         //double yDist = 
         
         System.out.print("Pair " + count + ": Intersection point: (" + String.format("%.1f", x1) + ", " + String.format("%.1f", y1) + "); ");
         System.out.print("Ship S1's distance is " + String.format("%.1f",s1Dist) + "; Ship S2's distance is " + String.format("%.1f",s2Dist) +"; ");
         double diff = s1Dist - s2Dist;
         if(diff > -0.5 && diff < 0.5 ){
            System.out.println("Tied.");
         }else if(s1Dist<s2Dist){
            System.out.println("Ship S1 is nearest.");
         }else{
            System.out.println("Ship S2 is nearest.");
         }
      }
      System.out.println("Done");
      
   }
}