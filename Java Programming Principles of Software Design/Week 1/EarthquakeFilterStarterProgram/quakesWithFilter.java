
/**
 * Write a description of quakesWithFilter here.
 * 
 * @author Eyvaz Najafli 
 * @version 10/08/2022
 */
public class quakesWithFilter implements Filter {
    private MagnitudeFilter fMag;
    private DepthFilter fDepth;
    // write a default constructor
    quakesWithFilter(){
        fMag = new MagnitudeFilter(4.0, 5.0);
        fDepth = new DepthFilter(-35000.0, -12000.0);
    }
    public boolean satisfies(QuakeEntry qe){
        return fMag.satisfies(qe) && fDepth.satisfies(qe);
    }
}
