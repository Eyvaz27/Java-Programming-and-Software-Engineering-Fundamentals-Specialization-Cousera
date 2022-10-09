
/**
 * Write a description of DepthFilter here.
 * 
 * @author Eyvaz Najafli
 * @version 10/08/2022
 */
public class DepthFilter implements Filter {
    public String filterName;
    private double minDepth;
    private double maxDepth;
    DepthFilter(double minD, double maxD, String name){
        minDepth = minD;
        maxDepth = maxD;
        filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        double currDepth = qe.getDepth();
        return currDepth>=minDepth && currDepth<=maxDepth;
    }
    
    public String getName(){
        return filterName;
    }
}
