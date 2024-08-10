import java.io.*;
import java.util.*;

public class Population{
   int number;
   String gene;
   
   public Population(int num, String gene){
      number = num;
      this.gene = gene;
   }
   public Population(){}
   
   public String toString(){
      return "C"+number+":"+gene;
   }
   
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      Scanner fileInput = null;
      System.out.println("Enter the file name:");
      String fileName = sc.nextLine();
      
      try{
         fileInput = new Scanner(new File(fileName));
      }catch(FileNotFoundException e){
         System.out.println("File not found.");
      }
      
      int numCritters = Integer.parseInt(fileInput.nextLine());
      int count = 1;
      List<Population> population = new ArrayList<>();
      while(count<=numCritters){
         String gene = fileInput.nextLine();
         Population critter = new Population(count, gene);
         population.add(critter);
         count++;
      }
      // for(Population i: population){
//          System.out.println(i.toString());
//       }
      
      int numMatings = Integer.parseInt(fileInput.nextLine());
      int mating = 1;
      
      List<String> matings = new ArrayList<>();
      
      while(mating<=numMatings){
         String matingPair = fileInput.nextLine();
         matings.add(matingPair);
         mating++;
      }
      // for(String i: matings){
//          System.out.println(i);
//       }
//       
      for(String i: matings){
         String[] pairs = i.split(" ");
         int c1 = Integer.parseInt(pairs[0]);
         int c2 = Integer.parseInt(pairs[1]);
         String c1Gene = "";
         String c2Gene = "";
         for(Population critter: population){
            if(c1 == critter.number){
               c1Gene = critter.gene;
            }
            
            if(c2 == critter.number){
               c2Gene = critter.gene;
            }
         }
         Population pop = new Population();
         
         Population offSpring = pop.mate(c1, c2, c1Gene, c2Gene, numCritters);
         numCritters++;
         population.add(offSpring);
      }
      
      for(Population critter: population){
         System.out.println("["+critter.gene+"]");
      }
   }
   public Population mate(int c1, int c2, String c1Gene, String c2Gene, int numCritters){
      
      String[] g1 = c1Gene.split("");
      String[] g2 = c2Gene.split("");
      int count1 = g1.length;
      int count2 = g2.length;
      String offSpringGene = ""; 
      if(count1>count2){
     for(int i = 0; i<=count1-1; i++){
        if(i<count2){
           offSpringGene+=g1[i]+g2[i];
        }else{
           offSpringGene+=g1[i];
        }
     }
   }else if(count1<count2){
     for(int i = 0; i<=count2-1; i++){
        if(i<count1){
           offSpringGene+=g1[i]+g2[i];
        }else{
           offSpringGene+=g2[i];
        }
     }
   }else{
      for(int i = 0; i<=count1-1; i++){
         offSpringGene+=g1[i]+g2[i];
      }
   }
   if(offSpringGene.length()>10){
      offSpringGene = offSpringGene.substring(0,10);
   }
      numCritters++;
      Population offSpring = new Population(numCritters, offSpringGene);
      return offSpring;
   }
}