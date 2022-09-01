
/**
 * Write a description of WordFrequencies here.
 * 
 * @author Eyvaz Najafli
 * @version 08/28/2022
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        
        for (String word: fr.words()) {
            // convert string to lower case
            word = word.toLowerCase();
            // remove extra punctuation characters
            word = word.replaceAll("\\p{Punct}","");
            int index = myWords.indexOf(word);
            if(index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
            
        }
    }
    public void findIndexOfMax () {
    }
    public void tester() {
        // relevant parameter initialization
        int max_value = 0;
        String mostFreqWord = "None";
        // find unique words
        findUnique();
        // print detailed information
        System.out.println("# Unique Words: " + myWords.size());
        for (int k = 0; k < myWords.size(); k++) {
            int currFreq = myFreqs.get(k);
            String currWord = myWords.get(k);
            System.out.println(currFreq + "\t" + currWord);
            // find word with highest frequency
            if(currFreq > max_value) {
                max_value = currFreq;
                mostFreqWord = currWord;
            }                   
        }
        System.out.println("The word that occurs most " + 
                           "often and its count are: " + 
                           mostFreqWord + " " + max_value);
    }
}
