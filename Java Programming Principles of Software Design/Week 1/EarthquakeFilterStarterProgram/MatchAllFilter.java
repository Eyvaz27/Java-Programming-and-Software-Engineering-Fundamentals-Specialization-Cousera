
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author Eyvaz Najafli 
 * @version 10/09/2022
 */
import edu.duke.*;
import java.util.*;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> quakeFilters;
    MatchAllFilter(){
        quakeFilters = new ArrayList<Filter>();
    }
    public void addFilter(Filter newFilter){
        quakeFilters.add(newFilter);
    }
    public boolean satisfies(QuakeEntry sampleQuake){
        for(Filter aFilter: quakeFilters){
            if(!aFilter.satisfies((sampleQuake))){
                return false;
            }
        }
        return true;
    }
}
