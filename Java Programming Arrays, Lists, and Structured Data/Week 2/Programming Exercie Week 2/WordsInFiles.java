
/**
 * Write a description of WordsInFiles here.
 * 
 * @author Eyvaz Najafli
 * @version 09/04/2022
 */
import edu.duke.*;
import java.util.*;
import java.io.File;
public class WordsInFiles {
    // private ArrayList<String> folderFiles;
    private HashMap<String, ArrayList<String>> fileWords;
    public WordsInFiles() {
        fileWords = new HashMap<String, ArrayList<String>>();
    }
    private void addWordsFromFile(File f) {
        String fileName = f.getName();
        FileResource currFile = new FileResource(f);
        for (String word: currFile.words()) {
            if(fileWords.containsKey(word)) {
                ArrayList<String> files = fileWords.get(word);
                files.add(fileName);
                fileWords.replace(word, files);
            }
            else {
                ArrayList<String> files = new ArrayList<String>();
                files.add(fileName);
                fileWords.put(word, files);
            }
        }
    }
    private void buildWordFileMap() {
        fileWords.clear();
        DirectoryResource folder = new DirectoryResource();
        for (File f: folder.selectedFiles()) {
            addWordsFromFile(f);
        }
    }    
    public int maxNumber() {
        int max_files = 0;
        for(String key: fileWords.keySet()) {
            int currFreq = fileWords.get(key).size();
            if(max_files < currFreq)
                max_files = currFreq;                
        }
        return max_files;
    }
    public ArrayList<String> wordsInNumFiles(int freq) {
        ArrayList<String> words = new ArrayList<String>();
        for(String key: fileWords.keySet()) {
            int currFreq = fileWords.get(key).size();
            if(currFreq == freq)
                words.add(key);
        }
        return words;
    }
    public void printFilesIn(String word) {
        ArrayList<String> files;
        if(fileWords.containsKey(word)) {
            files = fileWords.get(word);
            for(String file: files)
                System.out.println(file);
        }
        else {
            System.out.println(word + " is not found in files");
        }
        System.out.println("\n");
    }
    public void tester() {
        buildWordFileMap();
        int maxWord = maxNumber();
        System.out.println("Max word frequency = " + maxWord);
        ArrayList<String> words = wordsInNumFiles(maxWord);
        for (String word: words){
            System.out.println("Analyzing word --> " + word);
            printFilesIn(word);
        }
        System.out.println("\n### Private HashMap Summary ###");
        for(String key: fileWords.keySet()) {
            System.out.println("Word --> " + key);
            for (String file: fileWords.get(key)){
                System.out.println(file);
            }
        }
    }
}
