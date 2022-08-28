
/**
 * Write a description of CeaserCipher here.
 * 
 * @author Eyvaz Najafli
 * @version 08/20/2022
 */
import edu.duke.*;
import java.lang.*;
public class CeaserCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + 
                                 alphabet.substring(0, key);
        for (int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int currIdx = alphabet.indexOf(currChar);
            if(currIdx == -1){
                currIdx = alphabet.indexOf(Character.toUpperCase(currChar));
                if(currIdx != -1){
                    char newChar = shiftedAlphabet.charAt(currIdx);
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
            else{
                char newChar = shiftedAlphabet.charAt(currIdx);
                encrypted.setCharAt(i, newChar);
            }
        }
        System.out.println(encrypted.toString());
        return encrypted.toString();
    }
    public String encryptTwoKeys (String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String AlphabetKey1 = alphabet.substring(key1) + 
                                 alphabet.substring(0, key1);
        String AlphabetKey2 = alphabet.substring(key2) + 
                                 alphabet.substring(0, key2);                         
        for (int i = 0; i < encrypted.length(); i++){
            String tempAlphabet = "";
            if((i+1)%2 == 1)
                tempAlphabet = AlphabetKey1;
            else
                tempAlphabet = AlphabetKey2;
            char currChar = encrypted.charAt(i);
            int currIdx = alphabet.indexOf(currChar);
            if(currIdx == -1){
                currIdx = alphabet.indexOf(Character.toUpperCase(currChar));
                if(currIdx != -1){
                    char newChar = tempAlphabet.charAt(currIdx);
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
            else{
                char newChar = tempAlphabet.charAt(currIdx);
                encrypted.setCharAt(i, newChar);
            }
        }
        System.out.println(encrypted.toString());
        return encrypted.toString();
    }
    public void testCeaserCipher(int key){
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
}
