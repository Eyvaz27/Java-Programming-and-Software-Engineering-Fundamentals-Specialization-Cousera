package FindGeneWhile;


/**
 * Write a description of AllCodons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllCodonsAnd {
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
        return -1;
    }
    public String findGene(String dna, int where){
        // find first occurance of "ATG",  startIndex
        int startIndex = dna.indexOf("ATG", where);
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
        taaIndex = taaIndex == -1 ? dna.length() : taaIndex;
        tagIndex = tagIndex == -1 ? dna.length() : tagIndex;
        tgaIndex = tgaIndex == -1 ? dna.length() : tgaIndex;
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
        if(dex != -1) System.out.println("error on -1");
        dex = findStopCodon(dna, 0, "TAG");
        if(dex != -1) System.out.println("error on -1");
        System.out.println("tests finished");
    }
    public void testFindGene(){
        // String dna = "ATGCCCGGGAAATAACCC";
        String dna = "AATGCTAACTAGCTGACTAAT";
        String gene = findGene(dna, 0);
        System.out.println(gene);
        //if(!gene.equals("ATGCCCGGGAAATAA")){
        //    System.out.println("error");
        //}
        //System.out.println("tests fininshed");
    } 
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String temp_gene = findGene(dna, startIndex);
            if(temp_gene.isEmpty()){
                break;
            }
            else{
                System.out.println(temp_gene);
                startIndex = dna.indexOf(temp_gene,startIndex) + 
                             temp_gene.length();                                        
            }
        }
    }
    public void testOn(String dna){
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    public void test(){
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}
