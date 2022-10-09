
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author Eyvaz Najafli
 * @version 10/08/2022
 */
public class MagnitudeFilter implements Filter{
    public String filterName; 
    private double minMagnitude;
    private double maxMagnitude;
    MagnitudeFilter(double min, double max, String name){
        minMagnitude = min;
        maxMagnitude = max;
        filterName = name; 
    }
    public boolean satisfies(QuakeEntry qe){
        double currMagnitude = qe.getMagnitude();
        return currMagnitude>=minMagnitude && currMagnitude<=maxMagnitude;
    }
    
    public String getName(){
        return filterName;
    }
}
