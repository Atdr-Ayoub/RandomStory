
/**
 * Write a description of myClass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
public class myClass {
    
    private HashMap<String,Integer> map = new HashMap<String,Integer>();
    
    public myClass(){
        map = new HashMap();
    }
    
    public void buildCodonMap(String dna, int start){
        map.clear();
        int length = dna.length();
        String new_dna = "";
        //System.out.println(length);
        int sum = 0;
        if(length > 3){
            new_dna = dna.substring(start, length);
            for (int i=0 ; i<length-3; i+=3){
                String newCodon = new_dna.substring(i, i+3);
                if(map.keySet().contains(newCodon)){
                   map.put(newCodon, map.get(newCodon) + 1);
                }
                else{
                   map.put(newCodon, 1) ;
                } 
                sum = sum+1;
            }
            //System.out.println(sum);
        }
       
    }
    
    public String getMostCommonCodon(){
        int mostCodon = 0 ;
        String mostKey = "" ;
        for(String w : map.keySet()){
            int currCodon = map.get(w) ;
            if(mostCodon < currCodon){
                mostCodon = currCodon ;
                mostKey = w ;
            }
        }
        return mostKey ;
    }
    
    public void printCodonCounts(int start, int end){
        for(String w : map.keySet()){
            if(map.get(w)> start && map.get(w) < end){
                System.out.println(w+" "+map.get(w));
            }
        }
    
    }
    
    public void testCodon(){
          System.out.println("************************************************");
          FileResource fr =new FileResource() ;
          String test = fr.asString();
          System.out.println(test);
          buildCodonMap(test, 1);
          int tot = 0;
          for (String w : map.keySet()){
             
            if(map.get(w)<10){
                  //System.out.println(w);
                  System.out.println(w +" "+ map.get(w));
            }
  
            // System.out.println(w +" "+ map.get(w)+" "+ tot); 
             
          }
          
          System.out.println("----------------");
          System.out.println(map.size());
          /*
          String mostCodonTest = getMostCommonCodon();
          System.out.println("The most common codon : "+ mostCodonTest);
          System.out.println("-----------------");
          //printCodonCounts(1, 5);
          */
    }
    
    

}
