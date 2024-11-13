import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class Region{
    public String region;
    public int damNumber;

    public Region(String dam, int damNumber){
        this.region = dam;
        this.damNumber = damNumber;
    }

    public Region(){}

    public String toString(){
        return region;
    }
    
    public int isSame(String region, List<String> damList){
      int numDams = 0;
      boolean same = false;
      
      for(String dam: damList){
         if(region.equals(dam)){
            numDams++;
         }
      }
      return numDams;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the data file:");
        String fileName = sc.nextLine();
        Scanner fileInput = null;
        try{
            fileInput = new Scanner(new File(fileName));
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        List<String> dams = new ArrayList<>();
        
        while(fileInput.hasNextLine()){
           int damNum = 1;
           String damLine = fileInput.nextLine();
           String[] dam = damLine.split(" ");
           damNum++;
           dams.add(dam[1]);
        }
        Region r = new Region();
        
        System.out.println("Number of dams: " + dams.size() + ".");
        System.out.println("Distribution:");
        
        String result = "";
        for(int i = 0; i<dams.size(); i++){
           int n = 1;
           boolean found = false;
           if(i==dams.indexOf(dams.get(i))){
              int num = r.isSame(dams.get(i), dams);
              double percentage = ((double)num*100)/dams.size();
              result += dams.get(i) + " : " + String.format("%.1f",percentage) +"\n";
           }
        }
        System.out.println(result);
        
     }
}       
