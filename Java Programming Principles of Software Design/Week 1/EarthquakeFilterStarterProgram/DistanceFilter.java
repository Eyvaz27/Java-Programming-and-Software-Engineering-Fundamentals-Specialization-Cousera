
/**
 * Write a description of DepthFilter here.
 * 
 * @author Eyvaz Najafli
 * @version 10/08/2022
 */
public class DistanceFilter implements Filter {
    public String filterName;
    private Location location;
    private double maxDistance;
    DistanceFilter(Location loc, double distance, String name){
        location = loc;
        maxDistance = distance;
        filterName = name;
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location)<maxDistance;
    }
    public String getName(){
        return filterName;
    }
}
