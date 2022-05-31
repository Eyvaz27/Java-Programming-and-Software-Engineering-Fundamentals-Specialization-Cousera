package perimeter_quiz;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int num_points = 0;
        for (Point currPt : s.getPoints()) {
            num_points = num_points+1;
        }
        return num_points;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int num_points = 0;
        double average_length = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            average_length = average_length + currDist;
            num_points = num_points+1;
            prevPt = currPt;
        }
        return average_length/(double)num_points;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largest_side = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if(currDist>largest_side){
                largest_side = currDist;
            }
            prevPt = currPt;
        }
        return largest_side;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largest_x = 0.0;
        for (Point currPt : s.getPoints()) {
            double curr_x = currPt.getX(); 
            if(curr_x>largest_x){
                largest_x = curr_x;}
        }
        return largest_x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largest_perimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource temp_fr = new FileResource(f);
            Shape temp_s = new Shape(temp_fr);
            double temp_perimeter = getPerimeter(temp_s);
            if(temp_perimeter>largest_perimeter){
                largest_perimeter = temp_perimeter;
            }
        }
        return largest_perimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double largest_perimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource temp_fr = new FileResource(f);
            Shape temp_s = new Shape(temp_fr);
            double temp_perimeter = getPerimeter(temp_s);
            if(temp_perimeter>largest_perimeter){
                largest_perimeter = temp_perimeter;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }
    
    public void testAverageLength () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double average_length = getAverageLength(s);
        System.out.println("Average Shape Length = " + average_length);
    }
    
    public void testLargestSide(){
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double largest_length = getLargestSide(s);
        System.out.println("Largest Side Length = " + largest_length);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largest_perim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Shape Perimeter = " + largest_perim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largest_perim_file = getFileWithLargestPerimeter();
        System.out.println("Largest Perimeter File --> " + largest_perim_file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testAverageLength();
        pr.testLargestSide();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
