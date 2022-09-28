/**
 * Write a description of VigenereBreaker here.
 * 
 * @author Eyvaz Najafli
 * @version 09/26/2022
 */


import edu.duke.*;
import java.util.*;
import java.io.*;
public class VigenereBreaker {
    
    public String capitalize(String sample){
        String firstChar = sample.substring(0, 1).toUpperCase();  
        String remainingChars = sample.substring(1);       
        return firstChar + remainingChars;   
    }
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        int idx = whichSlice;
        StringBuilder answer = new StringBuilder();
        while (idx<message.length()) {
            char currChar = message.charAt(idx);
            answer.append(currChar);
            idx+=totalSlices;
        }
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for (int i=0; i<klength; i++) {
            String tempSlice = sliceString(encrypted, i, klength);
            CaesarCracker tempCracker = new CaesarCracker(mostCommon);
            int estimatedKey = tempCracker.getKey(tempSlice);
            key[i] = estimatedKey;
        }
        return key;
    }
    public char mostCommonCharIn(HashSet<String> dictionary) {  
        int[] countLetters = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        CaesarCracker sample = new CaesarCracker();
        String totalDictionary = "";
        for (String word: dictionary) {
            int[] counterSample = sample.countLetters(word);
            for (int i=0; i<26; i++)
                countLetters[i] = countLetters[i] + counterSample[i];
        }
        int mostCharIndex = sample.maxIndex(countLetters);
        return alphabet.charAt(mostCharIndex);
    } 
    public HashSet<String> readDictionary(FileResource dictFile){
        HashSet<String> dictionary = new HashSet<String>();
        for(String word: dictFile.words())
            dictionary.add(word.toLowerCase());
        return dictionary;
    }
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxSimilarity = 0;
        String keyString = "";
        String returnString = "";
        char mostCommonChar = mostCommonCharIn(dictionary);
        CaesarCracker sampleCracker = new CaesarCracker();
        
        for (int i=1; i<100; i++) {
            int[] estimatedKeys = tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher sampleCipher = new VigenereCipher(estimatedKeys);
            String decryptedMessage = sampleCipher.decrypt(encrypted);
            int currOccuranceCount = 0;
            String[] sampleText = decryptedMessage.split(" ");
            for(String word: sampleText) {
                word = word.replaceAll("\\p{Punct}","");
                if(word.length()<=1)
                    continue;
                word = word.toLowerCase();
                if(dictionary.contains(word)) 
                    currOccuranceCount += 1;
            }
            // update maxSimilarity with dictionary
            if (maxSimilarity < currOccuranceCount) {
                maxSimilarity = currOccuranceCount;
                keyString = Arrays.toString(estimatedKeys);
                returnString = decryptedMessage.substring(0, decryptedMessage.indexOf("\n")); 
                returnString = keyString + " ### " + returnString;
            }
        }
        returnString = Integer.toString(maxSimilarity) + " ### " + returnString;
        return returnString;
    }
    
    public HashMap<String, String> breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        // solve the function to return <languageName, function(breakForLanguage)>
        HashMap<String, String> result = new HashMap<String, String>();
        for(String key: languages.keySet()){
            HashSet<String> currDictionary = languages.get(key);
            String currLanguageDecryption = breakForLanguage(encrypted, currDictionary);
            System.out.println(key+" ### "+currLanguageDecryption);
            result.put(key, currLanguageDecryption);
        }
        return result;
    }
    public void breakVigenere () {
        // Select Text File to analyze
        FileResource fr = new FileResource();
        String encryptedMessage = fr.asString();
        // Continue from here to make new directory resource going through
        // languages and create new dictionaries for each language as
        // HashMap<String, HashSet<String>>  ==>  <languageName, languageDictionary>
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        DirectoryResource dictionaries = new DirectoryResource();
        for(File f: dictionaries.selectedFiles()){ 
            String fileName = f.getName();
            fileName = fileName.substring(0, fileName.indexOf("."));
            FileResource tempDictionary = new FileResource(f);
            HashSet<String> currDictionary = readDictionary(tempDictionary);
            languages.put(fileName, currDictionary);
        }
        System.out.println("Language # MaxWordCount # Keys # First Line Decryption");
        HashMap<String, String> decryptionResult = breakForAllLangs(encryptedMessage, languages);
    }
    
}
