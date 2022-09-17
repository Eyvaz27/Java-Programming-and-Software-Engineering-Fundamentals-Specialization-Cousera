import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
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
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
    }
    
}
