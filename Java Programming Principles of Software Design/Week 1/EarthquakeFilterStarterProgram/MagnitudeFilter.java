
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author Eyvaz Najafli
 * @version 10/08/2022
 */
public class MagnitudeFilter implements Filter{
    private double minMagnitude;
    private double maxMagnitude;
    MagnitudeFilter(double min, double max){
        minMagnitude = min;
        maxMagnitude = max;
    }
    public boolean satisfies(QuakeEntry qe){
        double currMagnitude = qe.getMagnitude();
        return currMagnitude>=minMagnitude && currMagnitude<=maxMagnitude;
    }
}
