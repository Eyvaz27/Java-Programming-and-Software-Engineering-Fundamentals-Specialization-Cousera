
/**
 * Write a description of OOCeaserCipher here.
 * 
 * @author Eyvaz Najafli 
 * @version 08/23/2022
 */
import edu.duke.*;
import java.lang.*;

public class OOCeaserCipher {
    private int mainKey;
    private String alphabet;
    private String shiftedAlphabet;
    public OOCeaserCipher(int key){
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + 
                          alphabet.substring(0, key);
    }
    public String encrypt(String message) {
        StringBuilder input = new StringBuilder(message);
        for (int i=0; i<input.length(); i++) {
            char currChar = input.charAt(i);
            int currIndex = alphabet.indexOf(currChar);
            if(currIndex == -1){
                currIndex = alphabet.indexOf(Character.toUpperCase(currChar));
                if(currIndex != -1){
                    char newChar = shiftedAlphabet.charAt(currIndex);
                    input.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
            else{
                char newChar = shiftedAlphabet.charAt(currIndex);
                input.setCharAt(i, newChar);
            }
        }
        System.out.println(input.toString());
        return input.toString();
    }
    public String encryptTwoKeys(String message, int extraKey) {
        StringBuilder input = new StringBuilder(message);
        String extraAlphabet = alphabet.substring(extraKey) + 
                               alphabet.substring(0, extraKey);                         
        for (int i = 0; i < input.length(); i++){
            String tempAlphabet = "";
            if((i+1)%2 == 1)
                tempAlphabet = shiftedAlphabet;
            else
                tempAlphabet = extraAlphabet;
            char currChar = input.charAt(i);
            int currIdx = alphabet.indexOf(currChar);
            if(currIdx == -1){
                currIdx = alphabet.indexOf(Character.toUpperCase(currChar));
                if(currIdx != -1){
                    char newChar = tempAlphabet.charAt(currIdx);
                    input.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
            else{
                char newChar = tempAlphabet.charAt(currIdx);
                input.setCharAt(i, newChar);
            }
        }
        System.out.println(input.toString());
        return input.toString();
    }
    public String decrypt(String message) {
        String decryptionAlphabet = alphabet.substring(26-mainKey) + 
                                    alphabet.substring(0, 26-mainKey);
        StringBuilder input = new StringBuilder(message);
        for (int i=0; i<input.length(); i++) {
            char currChar = input.charAt(i);
            int currIndex = alphabet.indexOf(currChar);
            if(currIndex != -1){
                char newChar = decryptionAlphabet.charAt(currIndex);
                input.setCharAt(i, newChar);
            }
        }
        System.out.println(input.toString());
        return input.toString();
    }
    public String decryptTwoKeys(String message, int extraKey) {
        StringBuilder input = new StringBuilder(message);
        String decryptAlphabetTemp = "";
        String decryptAlphabetMain = alphabet.substring(26-mainKey) + 
                                     alphabet.substring(0, 26-mainKey);
        String decryptAlphabetExtra = alphabet.substring(26-extraKey) + 
                                      alphabet.substring(0, 26-extraKey);
        for (int i=0; i<input.length(); i++) {
            if((i+1)%2 == 0)
                decryptAlphabetTemp = decryptAlphabetExtra;
            else
                decryptAlphabetTemp = decryptAlphabetMain;
            char currChar = input.charAt(i);
            int currIndex = alphabet.indexOf(currChar);
            if(currIndex == -1){
                currIndex = alphabet.indexOf(Character.toUpperCase(currChar));
                if(currIndex != -1){
                    char newChar = decryptAlphabetTemp.charAt(currIndex);
                    input.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
            else{
                char newChar = decryptAlphabetTemp.charAt(currIndex);
                input.setCharAt(i, newChar);
            }
        }
        // System.out.println(input.toString());
        return input.toString();
    }
    public int charCounter(FileResource file){
        int maxValue = -1;
        int maxIndex = -1;
        int[] charCounts = new int [26]; 
        String message = file.asString();
        for (int i=0; i<message.length(); i++){
            char currChar = message.charAt(i);
            int currIndex = alphabet.indexOf(Character.toUpperCase(currChar));
            if(currIndex != -1)
                charCounts[currIndex] += 1;
        }
        for (int i=0; i<charCounts.length; i++){
            if(charCounts[i] > maxValue){
                maxValue = charCounts[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public void TestDecryptTwoKey(String message){
        // The name of the Java Mascot is Duke. Woeoeee! ## 7 | 19
        if(message == "None"){
            int counter = 0;
            message = "";
            FileResource fr = new FileResource();
            for (String word: fr.words()){
                counter = counter + 1;
                message = message + " " + word;
                if(counter > 20)
                    break;
            }
            int mostOccuringCharIndex = charCounter(fr);
            System.out.println("Most dominant char index in file is " + 
                               mostOccuringCharIndex + "\n");
        }
        for (int i=1; i<26; i++)
            for (int j=1; j<26; j++) {
                OOCeaserCipher tempObject = new OOCeaserCipher(i);
                String result = tempObject.decryptTwoKeys(message, j);
                System.out.println(result + " ## " + i + " | " + j);
            } 
    }
}
