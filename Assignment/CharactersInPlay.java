
/**
 * Décrivez votre classe CharactersInPlay ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */

import java.util.*;
import edu.duke.*;
public class CharactersInPlay {
    
    private ArrayList<String> myNames ;
    private ArrayList<Integer> myCounts ;
    public CharactersInPlay(){
        myNames = new ArrayList<String>();
        myCounts = new ArrayList<Integer>() ;
    }

    public void update(String person){
        int indexOfName = myNames.indexOf(person);
        if(indexOfName == -1){
            myNames.add(person);
            myCounts.add(1) ;
        }
        else{
            int currCount = myCounts.get(indexOfName);
            myCounts.set(indexOfName, currCount+1);
        }
    }
    public void findAllCharacters(){
        String currName = "" ;
        myNames.clear();
        myCounts.clear();
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
        
           int indexPeriod = line.indexOf(".");
           if(indexPeriod != -1){
              currName = line.substring(0, indexPeriod); 
              update(currName);
           }
        
        }
    }
    public void charactersWithNumParts(int num1, int num2){
        for(int i=0; i < myNames.size(); i++){
             if(myCounts.get(i) > num1 && myCounts.get(i) < num2 ){
                System.out.println(myNames.get(i) +" "+ myCounts.get(i));
             }
        }
    }
    
    public void tester(){
        System.out.println("************************************");
        findAllCharacters();
        for(int i=0; i < myNames.size() ; i++){
            if(myCounts.get(i) > 90){
                 System.out.println(myNames.get(i) +" "+ myCounts.get(i));
            }
        }
        System.out.println("-------------------------");
        charactersWithNumParts(10, 100);
    }
}
