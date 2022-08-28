
/**
 * Write a description of measurement here.
 * 
 * @author Eyvaz Najafli
 * @version 07/14/2022
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class measurement {
    // Answer to first 4 questions
    public void testFolderAnalyzer() {
        folderAnalyzer("TemperatureF", "min");
        //folderAnalyzer("Humidity", "min");
    }
    public CSVRecord folderAnalyzer(String colname, 
                                    String maxmin) {
        System.out.println("Looking for overall " + maxmin + 
                           " value in " + colname + " Measure"); 
        DirectoryResource dr = new DirectoryResource();
        CSVRecord recordOfSearch = null;
        for (File f : dr.selectedFiles()) {
            System.out.println("Analyzing the file named " + 
                                f.getName() + " ##");
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord recordExtracted= extractRecord(parser, 
                                                     colname, 
                                                     maxmin);
            System.out.println(recordExtracted.get("DateUTC")  
                               + " -->> "
                               + recordExtracted.get(colname) 
                               + " " + colname);
            recordOfSearch = rowAnalyzer(recordOfSearch, 
                                         recordExtracted, 
                                         colname, maxmin);  
        }
        System.out.println("Result of Overall Search --> "+
                           recordOfSearch.get(colname) + " " +
                           colname + " Measure at the " +
                           recordOfSearch.get("DateUTC"));
        return recordOfSearch;
    }
    public CSVRecord extractRecord(CSVParser parser, 
                                    String colname, 
                                    String maxmin) {
        CSVRecord recordOfInterest = null;
        for ( CSVRecord recordCurrent: parser ) {
            recordOfInterest = rowAnalyzer(recordOfInterest, 
                                           recordCurrent, 
                                           colname, maxmin);
        }
        return recordOfInterest;
    }
    public CSVRecord rowAnalyzer (CSVRecord recordOfInterest, 
                                  CSVRecord recordCurrent, 
                                  String colname, String maxmin) 
    {
        if( recordOfInterest != null )
        {
            double valueCurrent = 0.0;
            double valueOfInterest = 0.0;
            try {
                valueCurrent = Double.parseDouble(recordCurrent.get(colname));
            }catch (Exception e) 
            {return recordOfInterest;}
            try {
                valueOfInterest = Double.parseDouble(recordOfInterest.get(colname));
            }catch (Exception e) 
            {return recordCurrent;}
                
            if(maxmin == "max") {
                if (valueCurrent > valueOfInterest)
                    return recordCurrent;
                else 
                    return recordOfInterest; 
            }
            else if (maxmin == "min") {
                if (valueCurrent < valueOfInterest)
                    return recordCurrent;
                else 
                    return recordOfInterest; 
                }
        }
        return recordCurrent;
    }
    // Answer to 5th and 6th questions
    public void testAverageFinder () {
        averageFinder("TemperatureF", 80.0);
    }
    public void averageFinder(String colname, 
                              double threshold) 
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageMeasure = averageCalculator(parser, 
                                                  colname, 
                                                  threshold);
        System.out.println("Average value for " + colname +
                           " measurement column --> " + 
                           Double.toString(averageMeasure));
    }
    public double averageCalculator(CSVParser parser, 
                                    String colname,
                                    double threshold)
    {
        int size = 0;
        double totalValue = 0.0;
        for ( CSVRecord recordCurrent: parser ) {
            double valueCurrent = 0.0;
            try {
                valueCurrent = Double.parseDouble(recordCurrent.get(colname));
                double humidityCurrent = Double.parseDouble(recordCurrent.get("Humidity"));
                if( humidityCurrent >= threshold) {
                    totalValue = totalValue + valueCurrent;
                    size = size + 1;
                }
            }catch (Exception e) {}
        }
        return totalValue/size;
    }
}
