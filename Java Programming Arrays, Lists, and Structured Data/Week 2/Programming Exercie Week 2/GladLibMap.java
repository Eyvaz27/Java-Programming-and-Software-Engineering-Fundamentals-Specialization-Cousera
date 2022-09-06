
/**
 * Write a description of GladLibMap here.
 * 
 * @author Eyvaz Najafli
 * @version 09/04/2022
 */
import edu.duke.*;
import java.util.*;
import java.io.File;

public class GladLibMap { 
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> wordsConsidered;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "E:/Company Folders/Development/JAVA/Java Programming and Software Engineering Fundamentals Specialization/Java Programming Arrays, Lists, and Structured Data/Week 2/GladLib/data/";
    private static String templateSourceDirectory = "E:/Company Folders/Development/JAVA/Java Programming and Software Engineering Fundamentals Specialization/Java Programming Arrays, Lists, and Structured Data/Week 2/GladLib/template/";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        wordsConsidered = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        wordsConsidered = new ArrayList<String>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        myMap.clear();
        File dir = new File(source);
        File[] directoryListing = dir.listFiles();
        // add hashmap code here
        for(File child : directoryListing) {
            String name = child.getName();
            name = name.substring(0, name.indexOf("."));
            // System.out.println("Uploading content of file "+name);
            String pathFile = child.getPath();
            ArrayList<String> content = readIt(pathFile);
            myMap.put(name, content);
        }
        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if(!wordsConsidered.contains(label))
            wordsConsidered.add(label);
        if (myMap.containsKey(label)) {
            return randomFrom(myMap.get(label));
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
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
        System.out.println("\n");
        String story = fromTemplate(templateSourceDirectory+
                                    "madtemplate2.txt");
        printOut(story, 60);
        totalWordsInMap();
        totalWordsConsidered();
    }
    
    public void totalWordsInMap() {
        int totalWords = 0;
        for(String key: myMap.keySet()) {
            int arraySize = myMap.get(key).size();
            totalWords = totalWords + arraySize;
        }
        System.out.println("\n\nIn total "+totalWords+" uploaded");
    }
    
    public void totalWordsConsidered() {
        int totalConsideredWords = 0;
        for (int i=0; i<wordsConsidered.size(); i++) {
            if(wordsConsidered.get(i).contains("number"))
                // Skipping randomly generated "number" field
                continue;
            int arraySize = myMap.get(wordsConsidered.get(i)).size();
            totalConsideredWords = totalConsideredWords + arraySize;
        }
        System.out.println("In total "+totalConsideredWords+" used");
    }

}
