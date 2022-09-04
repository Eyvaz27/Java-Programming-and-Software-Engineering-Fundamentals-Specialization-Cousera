
/**
 * Write a description of CodonCount here.
 * 
 * @author Eyvaz Najafli 
 * @version 09/02/2022
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {
    private String textDNA;
    private HashMap<String, Integer> codonMap;
    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
        FileResource fr = new FileResource();
        textDNA = fr.asString();
    }
    public void buildCodonMap(int start, String dna) {
        if(!codonMap.isEmpty())
            codonMap.clear();
        if(dna.contains("None"))
            dna = textDNA;
        dna = dna.toUpperCase();
        for (int index=start; index<dna.length()-3; index+=3) {
            String currentCodon = dna.substring(index, index+3);
            if(codonMap.containsKey(currentCodon))
                codonMap.put(currentCodon, 
                             codonMap.get(currentCodon) + 1);
            else
            codonMap.put(currentCodon, 1);
        }
    }
    public void getMostCommonCodon() {
        int mostCommonFreq = -1;
        String mostCommonCodon = "";
        for(String key: codonMap.keySet()) {
            if(codonMap.get(key) > mostCommonFreq) {
                mostCommonFreq = codonMap.get(key);
                mostCommonCodon = key;
            }
        }
        System.out.println("Most Common Codon is : " + 
                           mostCommonCodon + " with " + 
                           mostCommonFreq + " occurances\n");
    }
    public void printCodonCounts(int lowest, int highest) {
        for(String key: codonMap.keySet()) {
            if(codonMap.get(key) >= lowest && 
               codonMap.get(key) <= highest) 
               System.out.println(key + " \t " + 
                                  codonMap.get(key) + 
                                  " occurances\n");
        }
    }
    public void testCodonCount() {
        // CodonCount test = new CodonCount();
        buildCodonMap(0, "None");
        getMostCommonCodon();
        printCodonCounts(1, 5);
    }
}
