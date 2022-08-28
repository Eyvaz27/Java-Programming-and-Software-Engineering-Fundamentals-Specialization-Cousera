package ShapePerimeterCalculator;
import edu.duke.*;

public class PerimeterRunner {
    public double getPerimeter(Shape s){
        // start with totalPerim = 0
        double totalPerim = 0;
        // Start with prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape
        for(Point currPt: s.getPoints()){
              // Find the distance from prevPt to currPt
              double currDistance = prevPt.distance(currPt);
              // Update totalPerim to be totalPerim + distance
              totalPerim = totalPerim + currDistance;
              // Update prevPt to be currPt
              prevPt = currPt;}
        // totalPerim is returned
        return totalPerim;
    }
    public void testPerimeter(){
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("Perimeter = " + length);
    }
    public static void main (String[] args){
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}




    
