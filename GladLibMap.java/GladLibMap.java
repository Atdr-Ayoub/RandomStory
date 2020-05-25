import edu.duke.*;
import java.util.*;

public class GladLibMap{
        /*
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
        */
	private HashMap<String, ArrayList> myMap = new HashMap<String, ArrayList>();
	public GladLibMap(){
	    myMap = new HashMap();
	}
	
	private ArrayList<String> seenList ;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "datalong";
	
	public GladLibMap(String source){
	      initializeFromSource(source);
	      myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
	      ArrayList<String> myArr = new ArrayList<String>(Arrays.asList("adjective", "noun", "color", "country",
	                                                                  "name", "animal", "timeframe", "verb", "fruit")) ;
	                     
	      for(int i=0; i<myArr.size(); i++){
	          ArrayList<String>  newArr =  readIt(source+ "/" + myArr.get(i) +".txt");
	          myMap.put(myArr.get(i), newArr);

	      }
	        
	       
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		return randomFrom(myMap.get(label));
	}
	
	private int totalWordsInMap(){
	   int total = 0;
	   for(String word : myMap.keySet()){
	       int currTotal = myMap.get(word).size() ;
	       total = total + currTotal ;
	   }
	   return total;
	}
	
	private int totalWordsConsidered(){
	   int tot = 0;
	   for(int i=0; i<seenList.size(); i++){
	   
	       tot = tot + seenList.get(i).length();
	   
	   }
	   return tot ;
	}
	
	
	
	
	
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		int indexSub = 0 ;
		String sub = "" ;
		do{
		     sub = getSubstitute(w.substring(first+1,last));
		     indexSub = seenList.indexOf(sub);
		}while(indexSub != -1);
		seenList.add(sub);
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    seenList.clear();
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
	    System.out.println("\n\nThe number of words replaced = "+ seenList.size());
	}
	


}
