/**
 * Write a description of VigenereBreaker here.
 * 
 * @author Eyvaz Najafli
 * @version 09/20/2022
 */

import java.util.*;
import edu.duke.*;
 
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

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        int maxSimilarity = 0;
        String keyString = "";
        String TargetDecryptedMessage = "";
        // Select Text File to analyze
        FileResource fr = new FileResource();
        // Select Dictionary File as reference
        FileResource dictionaryFile = new FileResource();
        String encryptedMessage = fr.asString();
        ArrayList<String> dictionary = new ArrayList<String>();
        for(String word: dictionaryFile.words())
            dictionary.add(word);
        CaesarCracker sampleCracker = new CaesarCracker();
        
        for (int i=1; i<15; i++) {
            int[] estimatedKeys = tryKeyLength(encryptedMessage, i, 'e');
            VigenereCipher sampleCipher = new VigenereCipher(estimatedKeys);
            String decryptedMessage = sampleCipher.decrypt(encryptedMessage);
            int currOccuranceCount = 0;
            String[] sampleText = decryptedMessage.split(" ");
            for(String word: sampleText) {
                word = word.replaceAll("\\p{Punct}","");
                if(word.length()<=1)
                    continue;
                word = capitalize(word);
                if(dictionary.contains(word)) 
                    currOccuranceCount += 1;
            }
            // update maxSimilarity with dictionary
            if (maxSimilarity < currOccuranceCount) {
                maxSimilarity = currOccuranceCount;
                keyString = Arrays.toString(estimatedKeys);
                TargetDecryptedMessage = decryptedMessage; 
            }
        }
        
        System.out.println("Decryption Keys : "+keyString);
        System.out.println("Decrypted Message : "+TargetDecryptedMessage);
    }
    
}
