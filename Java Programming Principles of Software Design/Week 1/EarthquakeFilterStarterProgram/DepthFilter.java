
/**
 * Write a description of DepthFilter here.
 * 
 * @author Eyvaz Najafli
 * @version 10/08/2022
 */
public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;
    DepthFilter(double minD, double maxD){
        minDepth = minD;
        maxDepth = maxD;
    }
    public boolean satisfies(QuakeEntry qe) {
        double currDepth = qe.getDepth();
        return currDepth>=minDepth && currDepth<=maxDepth;
    }
}
