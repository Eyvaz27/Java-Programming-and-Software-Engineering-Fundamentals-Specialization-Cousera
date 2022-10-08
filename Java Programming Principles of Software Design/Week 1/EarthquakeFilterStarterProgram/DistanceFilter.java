
/**
 * Write a description of DepthFilter here.
 * 
 * @author Eyvaz Najafli
 * @version 10/08/2022
 */
public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;
    DistanceFilter(Location loc, double distance){
        location = loc;
        maxDistance = distance;
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location)<maxDistance;
    }
}
