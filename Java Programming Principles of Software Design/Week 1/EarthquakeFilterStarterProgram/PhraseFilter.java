
/**
 * Write a description of PhraseFilter here.
 * 
 * @author Eyvaz Najafli
 * @version 10/08/2022
 */
public class PhraseFilter implements Filter {
    private String searchPosition;
    private String searchPhrase;
    PhraseFilter (String pos, String phrase) {
        searchPosition = pos;
        searchPhrase = phrase;
    }

public boolean satisfies (QuakeEntry qe){
        String currTitle = qe.getInfo();
        if(searchPosition.contentEquals("start") && 
           currTitle.startsWith(searchPhrase)){
               return true;
        }
        else if(searchPosition.contentEquals("end") &&
                currTitle.endsWith(searchPhrase)){
                return true;
        }
        else if(searchPosition.contentEquals("any") &&
                currTitle.indexOf(searchPhrase)!=-1){
                return true;
        }
        return false;
    }
}
