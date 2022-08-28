
/**
 * Write a description of OOTestCaesarCipher here.
 * 
 * @author Eyvaz Najafli
 * @version 08/24/2022
 */
import edu.duke.*;
public class OOTestCaesarCipher {
    public void wordLength() {
        int maxValue = -1;
        int maxIndex = -1;
        int[] wordLength = new int [20]; 
        FileResource txtFile = new FileResource();
        for(String word: txtFile.words()){
            word = word.replaceAll("\\p{Punct}","");
            int currLength = word.length();
            if(currLength == -1 || currLength >= wordLength.length){
                System.out.println("Anomaly occured in word --" + word);
                continue;
            }
            else
                wordLength[currLength] += 1;
        }
        for (int i=0; i<wordLength.length; i++){
            if(wordLength[i] > maxValue){
                maxValue = wordLength[i];
                maxIndex = i;
            }
        }
        System.out.println("Max Word Length is equal to " + maxIndex);
    }
}
