package FindGeneWhile;


/**
 * Write a description of FindGeneWhile here.
 * 
 * @author Eyvaz Najafli
 * @version Version: 0.1  Date:06/15/2022 
 */
public class FindGeneWhile {
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        int currIndex = dna.indexOf("TAA", startIndex+3);
        String answer = "";
        while (currIndex != -1){
            if( (currIndex - startIndex) % 3 == 0){
                answer = dna.substring(startIndex, currIndex+3);
                currIndex = -1;
            }
            else{
                currIndex = dna.indexOf("TAA", currIndex+1);
            }
        }
        return answer;
    }
    public void testFindGeneSimple(){
        //
        String dna = "AATGCGTAATTAATCG";
        System.out.println("DNA strand is " + dna);
        String gene = findGene(dna);
        System.out.println("Gene is " + gene);
        //
        dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna);
        System.out.println("Gene is " + gene);
        //
        dna = "CGATGGTTGATAAGCCTAAGCTAAA";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna);
        System.out.println("Gene is " + gene);
        
    }
}
