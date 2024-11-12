import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Ships{
   //co-ordinate x of the ship's position
   double x;
   double y;
   //course coefficients
   double a;
   double b;
   double c;

   //constructor to initialise the coordinates and course coefficients
   public Ships(double a, double b, double c,double x, double y){
      this.a = a;
      this.b = b;
      this.c = c;
      this.x = x;
      this.y = y;
   }

   //default constructor
   public Ships(){}

   //returns the string representation of the ship's course and position
   public String toString(){
      return "course: " + a+"x+"+b+"b+"+c+" position: ("+x+","+y+")";
   }
   
   
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      Scanner fileInput = null;
      System.out.println("Enter the name of the ship data file:");
      //read in the file containing the ships data
      String fileName = sc.nextLine();

      //handle exceptions if the file isnt found
      try{
         fileInput = new Scanner(new File(fileName));
      }catch(FileNotFoundException e){
         System.out.println("File not found");
      }

      //counter for the pairs of ships processed
      int count = 0;
      
      while(fileInput.hasNextLine()){
         count++;

         //read in two lines of ship data to be processed
         String shipOneData = fileInput.nextLine();
         String shipTwoData = fileInput.nextLine();

         //split each line by the spaces to extract the data for the ships course (line equation) and position
         String[] shipOne = shipOneData.split(" ");
         String[] shipTwo = shipTwoData.split(" ");
         
         //parse data and create each ship object fir each ship
         Ships s1 = new Ships(Double.parseDouble(shipOne[0]), Double.parseDouble(shipOne[1]), Double.parseDouble(shipOne[2]), Double.parseDouble(shipOne[3]), Double.parseDouble(shipOne[4]));
         Ships s2 = new Ships(Double.parseDouble(shipTwo[0]), Double.parseDouble(shipTwo[1]), Double.parseDouble(shipTwo[2]), Double.parseDouble(shipTwo[3]), Double.parseDouble(shipTwo[4]));

         //calculate the intersection point (x1, y1) of the ship's course lines using cramer's rule
         double x1 = ((s1.b*s2.c)-(s2.b*s1.c))/((s1.a*s2.b)-(s2.a*s1.b)) + 0.0;
         double y1 = ((s2.a*s1.c)-(s1.a*s2.c))/((s1.a*s2.b)-(s2.a*s1.b)) + 0.0;

         //calculate the distance of each ship to the intersection point
         double s1Dist = Math.sqrt(Math.pow((s1.x-x1),2)+Math.pow((s1.y-y1),2))+ 0.0;
         double s2Dist = Math.sqrt(Math.pow((s2.x-x1),2)+Math.pow((s2.y-y1),2))+ 0.0;

         //output the intersection point and distances to the console
         System.out.print("Pair " + count + ": Intersection point: (" + String.format("%.1f", x1) + ", " + String.format("%.1f", y1) + "); ");
         System.out.print("Ship S1's distance is " + String.format("%.1f",s1Dist) + "; Ship S2's distance is " + String.format("%.1f",s2Dist) +"; ");
         
         //determine which ship is closer to the intersection point, or if they are approximately tied
         double diff = s1Dist - s2Dist;

         //treat distances as tied if difference is within 0.5
         if(diff > -0.5 && diff < 0.5 ){
            System.out.println("Tied.");
         }else if(s1Dist<s2Dist){
            System.out.println("Ship S1 is nearest.");
         }else{
            System.out.println("Ship S2 is nearest.");
         }
      }

      //indicate the program has completed processing
      System.out.println("Done");
      
   }
}
