
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author Eyvaz Najafli
 * @version 10/08/2022
 */
public class MinMagFilter implements Filter
{
    public String filterName;
    private double magMin; 
    
    public MinMagFilter(double min, String name) { 
        magMin = min;
        filterName = name;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 
    
    public String getName(){
        return filterName;
    }
}
