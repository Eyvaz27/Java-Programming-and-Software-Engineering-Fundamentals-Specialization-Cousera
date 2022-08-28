package FindGeneWhile;


/**
 * Write a description of AllCodons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllCodons {
    public int findStopCodon(String dnaStr, 
                             int startIndex, 
                             String stopCodon){
        // find stopCodon starting from (startIndex + 3), 
        // currIndex as long as currIndex is not equal to -1
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while(currIndex!=-1){
            if((currIndex-startIndex)%3==0){
                return currIndex;
            }
            else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return dnaStr.length();
    }
    public String findGene(String dna){
        // find first occurance of "ATG",  startIndex
        int startIndex = dna.indexOf("ATG");
        // if startIndex is -1, return ""
        if(startIndex == -1){
            return "";}
        // use taaIndex to store findStopCodon(dna, startIndex)
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        // use tagIndex to store findStopCodon(dna, startIndex)
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        // use tgaIndex to store findStopCodon(dna, startIndex)
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        // store in minIndex the smallest of these three values 
        int minIndex = Math.min(Math.min(taaIndex, tagIndex), 
                                tgaIndex);
        // if minIndex is dna.length() -- nothing found, return ""
        if(minIndex == dna.length()){
            return "";
        }
        else{
            return dna.substring(startIndex, minIndex+3);
        }
    }
    public void testFindStop() {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if(dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if(dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna, 21, "TAA");
        if(dex != 26) System.out.println("error on 26");
        dex = findStopCodon(dna, 0, "TAG");
        if(dex != 26) System.out.println("error on 26");
        System.out.println("tests finished");
    }
}
