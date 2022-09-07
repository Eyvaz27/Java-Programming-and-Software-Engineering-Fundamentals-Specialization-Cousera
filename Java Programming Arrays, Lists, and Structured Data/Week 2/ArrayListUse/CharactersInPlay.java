
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author Eyvaz Najafli
 * @version 08/30/2022
 */
import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> Characters;
    private ArrayList<Integer> Frequency;
    public CharactersInPlay() {
        Characters = new ArrayList<String>();
        Frequency = new ArrayList<Integer>();
    }
    private void update(String person) {
        int personIndex = Characters.indexOf(person);
        if(personIndex == -1){
            Characters.add(person);
            Frequency.add(1);
        }
        else {
            int value = Frequency.get(personIndex);
            Frequency.set(personIndex, value + 1);
        }
    }
    public void findAllCharacters(FileResource fr){
        for (String line: fr.lines()) {
            int placeDot = line.indexOf(".");
            if(placeDot != -1){
                String name = line.substring(0, placeDot);
                if(name == name.toUpperCase()){
                    update(name);
                    //System.out.println(line);
                }
            }
        }
    }
    public void charactersWithNumParts(int num1, int num2) {
        if(num1 > num2)
            System.out.println("### Invalid Input ###");
        else {
            int maxValue = 0;
            String mostFreqCharacter = "None";
            for (int k=0; k<Characters.size(); k++){
                int currFreq = Frequency.get(k);
                String currName = Characters.get(k);
                if (currFreq>num1 && currFreq<num2)
                    System.out.println(currFreq + "\t" + currName);
                if(currFreq > maxValue){
                    maxValue = currFreq;
                    mostFreqCharacter = currName;
                }
            }
            System.out.println("\nMost talking Character is: " + 
                                mostFreqCharacter + " " + maxValue);
        }
    }
    public void tester() {
        FileResource fr = new FileResource();
        findAllCharacters(fr);
        charactersWithNumParts(9, 16);
    }
}
