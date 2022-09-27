/**
 * Write a description of VigenereBreaker here.
 * 
 * @author Eyvaz Najafli
 * @version 09/26/2022
 */


import edu.duke.*;
import java.util.*;

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
    public HashSet<String> readDictionary(FileResource dictFile){
        HashSet<String> dictionary = new HashSet<String>();
        for(String word: dictFile.words())
            dictionary.add(word.toLowerCase());
        return dictionary;
    }
    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommonChar) {
        int maxSimilarity = 0;
        String keyString = "";
        String returnString = "";
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
    public HashMap<String, String> breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        
    }
    public void breakVigenere () {
        // Select Text File to analyze
        FileResource fr = new FileResource();
        // Select Dictionary File as reference
        FileResource dictionaryFile = new FileResource();
        String encryptedMessage = fr.asString();
        // Continue from here to make new directory resource going through
        // languages and create new dictionaries for each language as
        // HashMap<String, HashSet<String>>  ==>  <languageName, languageDictionary>
        DirectoryResource dictionaries = new DirectoryResource();
        HashSet<String> dictionary = readDictionary(dictionaryFile);
        char mostCommonChar = mostCommonCharIn(dictionary);
        String decryptedMessage = breakForLanguage(encryptedMessage, dictionary, mostCommonChar);
        System.out.println("Decrypted Message : "+"MaxWordCount # Keys # First Line Decryption");
        System.out.println(decryptedMessage);
    }
    
}
