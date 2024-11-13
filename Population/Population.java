import java.io.*;
import java.util.*;

public class Population{
   int number; //identifier for each critter
   String gene; //gene sequence of the critter

   //constructor to initialize Population object with a given number and gene
   public Population(int num, String gene){
      number = num;
      this.gene = gene;
   }

   //default constructor
   public Population(){}

   //returns a string representation of the Population object
   public String toString(){
      return "C"+number+":"+gene;
   }
   
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      Scanner fileInput = null;
      System.out.println("Enter the file name:");
      String fileName = sc.nextLine();

      //open the file and catch any exceptions associated with it
      try{
         fileInput = new Scanner(new File(fileName));
      }catch(FileNotFoundException e){
         System.out.println("File not found.");
      }
      
      int numCritters = Integer.parseInt(fileInput.nextLine()); //the number of initial critters
      int count = 1;
      
      List<Population> population = new ArrayList<>(); //list to store all the critters

      //read and create each critter in the initial population
      while(count<=numCritters){
         String gene = fileInput.nextLine();//read the gene sequence
         Population critter = new Population(count, gene);
         population.add(critter); //add the criter the population like
         count++;
      }
      
      int numMatings = Integer.parseInt(fileInput.nextLine()); //number of mating pairs
      int mating = 1;
      
      List<String> matings = new ArrayList<>();//list to store all the maiting pairs

      //read each maiting pair from the file abd store in hte maiting list
      while(mating<=numMatings){
         String matingPair = fileInput.nextLine();
         matings.add(matingPair);
         mating++;
      }

      //process each maiting pair to generate an offspring
      for(String maitingPair: matings){
         String[] pairs = maitingPair.split(" "); //split the maiting pairi to get the critter IDs
         int c1 = Integer.parseInt(pairs[0]); //first critter in pair
         int c2 = Integer.parseInt(pairs[1]); //second critter in pair

         //variables to hold the gene sequences of the maiting critters
         String c1Gene = "";
         String c2Gene = "";

         //find the genes of the critters involved in the maiting pair
         for(Population critter: population){
            if(c1 == critter.number){
               c1Gene = critter.gene;
            }
            
            if(c2 == critter.number){
               c2Gene = critter.gene;
            }
         }
         
         Population pop = new Population();

         //generate offspring with combined genes and add to the population
         Population offSpring = pop.mate(c1, c2, c1Gene, c2Gene, numCritters);
         numCritters++;
         population.add(offSpring);
      }

      //print the final population including the initial critters and all offspring
      for(Population critter: population){
         System.out.println("["+critter.gene+"]");
      }
   }

   //method that combines genes from both parents to create the offspring
   public Population mate(int c1, int c2, String c1Gene, String c2Gene, int numCritters){
      //split each parent's gene into an array of individual characters
      String[] g1 = c1Gene.split("");//gene sequence of parent1
      String[] g2 = c2Gene.split("");//gene sequence of parent2
      int count1 = g1.length;//length of parent 1's gene
      int count2 = g2.length;//length of parent 2's gene

      //string to store the combined gene sequence of the offspring
      String offSpringGene = ""; 

      //if parent 1's gene is longer, add characters from both geners up to count2
      //then add remaining characters from parent 1's gene only
      if(count1>count2){
         for(int i = 0; i<=count1-1; i++){
            if(i<count2){
               //add both parents gene if within bounds of both
               offSpringGene+=g1[i]+g2[i];
            }else{
               //add remaining characters of parent1
               offSpringGene+=g1[i];
            }
         }
      }
      //if parent 2's gene is longer, add characters from both geners up to count1,
      //then add remaining characters from parent 2's gene obnly
      else if(count1<count2){
         for(int i = 0; i<=count2-1; i++){
            if(i<count1){
               //add both parents gene if within bounds of both
               offSpringGene+=g1[i]+g2[i];
            }else{
               //add remaining characters of parent2
               offSpringGene+=g2[i];
            }
         }
      }
      //if both parents are the same length, combine characters from both genes 
      else{
         for(int i = 0; i<=count1-1; i++){
            offSpringGene+=g1[i]+g2[i];
         }
      }

      //limit offspring gene to maximum of 10 characters
      if(offSpringGene.length()>10){
         offSpringGene = offSpringGene.substring(0,10);
      }

      //increment the critter counter and create a new Population object for the offspring
      numCritters++;
      Population offSpring = new Population(numCritters, offSpringGene);
      return offSpring;
   }
}
