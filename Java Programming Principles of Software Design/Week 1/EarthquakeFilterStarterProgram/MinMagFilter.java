
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author Eyvaz Najafli
 * @version 10/08/2022
 */
public class MinMagFilter implements Filter
{
    private double magMin; 
    
    public MinMagFilter(double min) { 
        magMin = min;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 

}
