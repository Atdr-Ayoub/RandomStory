
/**
 * Write a description of WordsInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.InputStreamReader;
import edu.duke.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.FileReader;
public class WordsInFile {

    private HashMap<String,ArrayList> map = new HashMap<String,ArrayList>();
    
    public WordsInFile(){
         map = new HashMap();
    }
    
    private void addWordsFromFile(File f){
         String wordsInFile = "" ; 
         try{
            wordsInFile = new String(Files.readAllBytes(Paths.get(f.toString())));
         }catch(Exception e){
            e.printStackTrace();
         }
         //System.out.println(wordsInFile);
         for(String word : wordsInFile.split("\\s+|(?=\\p{Punct})|(?<=\\p{Punct})")){
            int length = word.length();
            StringBuilder new_word1 = new StringBuilder();
            for(int i=0; i<length; i++){
                char curr = word.charAt(i);
                //System.out.println(curr);
                if (!Character.isLetter(curr)) {
                   break;
                }
                new_word1.append(curr);
            }
            String new_word = new_word1.toString();
            
            if(new_word.isEmpty()){
               continue;
            }
            //System.out.println(new_word);
            
            if(!map.keySet().contains(new_word)){
                ArrayList<String> arrayWord = new ArrayList<String>();
                arrayWord.add(f.getName());
                map.put(new_word, arrayWord);
                if(new_word.equals("tree")){
                    System.out.println("sea occured in ==> "+ f.getName());
                }
            }
            else{
                ArrayList currArray = map.get(new_word);
                if(!currArray.contains(f.getName())){ 
                  currArray.add(f.getName());
                  map.put(new_word, currArray);
                }
                if(new_word.equals("tree")){
                    System.out.println("sea occured in ==> "+ f.getName());
                }
            }
         }
         
    }
    
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    } 
    
    public int maxNumber(){
        int max = 0;
        for(String word : map.keySet()){
            int numberOfFile = map.get(word).size();
            if(max < numberOfFile){
                max = numberOfFile ;
            }
            //System.out.println("The "+word+" is in "+max+" files");
        }
        return max ;
    }
    
    public ArrayList wordsInNumFiles(int number){
        ArrayList<String> wordExactNumFile = new ArrayList<String>() ;
        for(String word : map.keySet()){
            int numberOfFile = map.get(word).size();
            if(numberOfFile == number){
               if(!wordExactNumFile.contains(word)){
                   wordExactNumFile.add(word); 
               }
               //wordExactNumFile.add(word);
            }
        }
        return wordExactNumFile ;
    }
    
    public void printFilesIn(String word){
        System.out.println("The word "+word+" appears in :\n");
        ArrayList fileIn = map.get(word);
        if(fileIn.size() == -1){
            System.out.println("NONE");
        }
        for(int i=0; i < fileIn.size(); i++){
            System.out.println(fileIn.get(i));
        }
    }
   
    public void test1(){
        System.out.println("*************************************");
        buildWordFileMap();
        int maxOf = maxNumber();
        //System.out.println("The max number = "+ maxOf);
        
        int compteur = 0;
        for(String word : map.keySet()){
           //System.out.println(word+" "+map.get(word)+" "+map.get(word).size());
            
           if(map.get(word).size() == 4){
              //System.out.println(word+" "+map.get(word)+" "+map.get(word).size());
              // System.out.println(map.get(word));
               compteur += 1 ;
           }
           
           if(map.get(word).equals("tree")){
               // System.out.println(map.get(word));
            
           }
        }
        //System.out.println("appeaaaaaaaars ");
        //printFilesIn("tree");
        //System.out.println(wordsInNumFiles(4).size());
        //System.out.println(map.get("tree"));
        //printFilesIn("tree");
        System.out.println("** "+compteur+" **");
    }
    
    
    
    
    
    
    
    
    
    
}
