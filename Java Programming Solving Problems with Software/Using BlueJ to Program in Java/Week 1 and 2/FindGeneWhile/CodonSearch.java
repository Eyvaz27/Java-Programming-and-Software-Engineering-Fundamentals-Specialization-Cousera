package FindGeneWhile;
import edu.duke.*;
import java.io.File;

/**
 * Write a description of AllCodons here.
 * 
 * @author Eyvaz Najafi
 * @version 07.05.2022
 */
public class CodonSearch {
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
    public StorageResource getAllGenes(String dna){
        String dna_processed = dna.toUpperCase();
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while(true){
            String temp_gene = findGene(dna_processed, startIndex);
            if(temp_gene.isEmpty()){
                break;
            }
            geneList.add(temp_gene);
            startIndex = dna_processed.indexOf(temp_gene,startIndex) 
                         + temp_gene.length();   
        }
        return geneList;
    }
    public float cgRatio (String dna) {
        int n = dna.length();
        int cg_count = 0;
        for (int index = 0; index< n; index++) {
            if(dna.charAt(index) == 'C' || 
               dna.charAt(index) == 'D') {
               cg_count = cg_count+1;
            }
        }
        return (float) cg_count/n;
    }
    public int countCTG (String dna) {
        int startIndex = dna.indexOf("CTG");
        int ctg_counter = 0;
        while (startIndex != -1) {
            ctg_counter = ctg_counter + 1;
            startIndex = dna.indexOf("CTG", startIndex+3);
        }
        return ctg_counter;
    }
    public void processGenes (StorageResource sr) {
        int max_length = 0;
        String longest_gene = "";
        StorageResource nine_character_list = new StorageResource();
        StorageResource higher_cg_list = new StorageResource();
        for (String gene: sr.data()) {
            if(gene.length() > max_length) {
                max_length = gene.length();
                longest_gene = gene;}
            if(gene.length() > 60) {
                nine_character_list.add(gene);}
            if(cgRatio(gene) > 0.35) {
                higher_cg_list.add(gene);}
        }
        System.out.println("There are "+sr.size()+" genes to process");
        System.out.println("There are " + 
                            nine_character_list.size() +
                            " genes with more than 60 chars:");
        for (String nine_gene: nine_character_list.data()) {
            System.out.println(nine_gene);}
        System.out.println("There are " + higher_cg_list.size() +
                            " genes with more than 0.35 C-G ratio:");
        for (String cg_gene: higher_cg_list.data()) {
            System.out.println(cg_gene);}
        System.out.println("The longest gene sequence has " + 
                            max_length + " characters:");  
        System.out.println(longest_gene);
    }
    public void testOn(String dna){
        System.out.println("Testing printAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String gene: genes.data()) {
            System.out.println(gene);
        }
    }
    public void test(){
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
    public void testPAWEEK2() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String dna = fr.asString();
            System.out.println("DNA Sequence --> " + dna);
            System.out.println("Codon CTG appears in DNA " + 
                                countCTG(dna) + " times");
            StorageResource geneList = getAllGenes(dna);
            processGenes(geneList);}
    }
}
