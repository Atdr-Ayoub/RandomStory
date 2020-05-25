
/**
 * Décrivez votre classe WordFrequencies ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */

import edu.duke.* ;
import java.util.* ;
public class WordFrequencies {
    
    private ArrayList<String> myWords ;
    private ArrayList<Integer> myFreqs ;
    
    public WordFrequencies(){
       myWords = new ArrayList<String>() ;
       myFreqs = new ArrayList<Integer>() ;
    }
    
    public void findUnique(){
       myWords.clear() ;
       myFreqs.clear() ;
       
       FileResource fr = new FileResource() ;
       for(String word : fr.words()){
          word = word.toLowerCase() ;
          int indexOfWord = myWords.indexOf(word);
          if(indexOfWord == -1 ){
             myWords.add(word) ;
             myFreqs.add(1) ;
          }
          else{
             int currFreq = myFreqs.get(indexOfWord) ;
             myFreqs.set(indexOfWord, currFreq+1) ;
          }
          
       }
    
    }
    public void tester(){
       findUnique();
       System.out.println("**************************");
       System.out.println("The number of unique Words : "+ myWords.size());
       for(String word : myWords){
          int index = myWords.indexOf(word);
          //System.out.println(word +" ==> "+ myFreqs.get(index));
       }
       System.out.println("index of max : " + findIndexOfMax()+" with word :"+myWords.get(findIndexOfMax())+" with freqs ="+myFreqs.get(findIndexOfMax()));
    }
    
    public int findIndexOfMax(){
       int indexMax = 0 ;
       for(int i=0 ; i < myFreqs.size() ; i++){
          if(myFreqs.get(indexMax) < myFreqs.get(i) ){
              indexMax = i ;  
          } 
       }
       return indexMax ;
    }
    
    
    
    
    
    
    
    
    
    
    
    

}
