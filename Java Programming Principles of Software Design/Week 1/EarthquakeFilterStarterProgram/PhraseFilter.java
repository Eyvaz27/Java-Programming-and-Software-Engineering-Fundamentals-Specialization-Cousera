
/**
 * Write a description of PhraseFilter here.
 * 
 * @author Eyvaz Najafli
 * @version 10/08/2022
 */
public class PhraseFilter implements Filter {
    public String filterName;
    private String searchPosition;
    private String searchPhrase;
    PhraseFilter (String pos, String phrase, String name) {
        searchPosition = pos;
        searchPhrase = phrase;
        filterName = name;
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
    
    public String getName(){
        return filterName;
    }
}

