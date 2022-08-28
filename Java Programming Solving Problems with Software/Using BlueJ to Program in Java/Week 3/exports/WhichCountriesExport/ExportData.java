
/**
 * Write a description of ExportData here.
 * 
 * @author Eyvaz Najafli
 * @version 07/12/2022
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ExportData {
    public void listExporters(CSVParser parser, StorageResource exports) {
        int counter = 0;
        // for each row in the CSV File
        for (CSVRecord record: parser) {
            boolean check = true;
            String export = record.get("Exports");
            for (String exportOfInterest : exports.data()){
                if (!export.contains(exportOfInterest)) {
                    check = false;
                    break;}
            }
            if(check){
                String country = record.get("Country");
                System.out.println(country);
                counter = counter + 1;
            }
        }
        System.out.println("Result of Search --> " + counter + " rows");
    }
    public void compareValue(CSVParser parser, String threshold){
        int counter = 0;
        // for each row in the CSV File
        for (CSVRecord record: parser) {
            String country = record.get("Country");
            String valueDollar = record.get("Value (dollars)");
            if(valueDollar.length() > threshold.length()){
                System.out.println(country + " --> " + 
                                   valueDollar);
                counter = counter + 1;
            }
        }
        System.out.println("Result of Search --> " + counter + " rows");
    }
    public void ExportingCountries() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        StorageResource exports = new StorageResource();
        //exports.add("coffee");
        //exports.add("flowers");
        exports.add("cocoa");
        listExporters(parser, exports);
        parser = fr.getCSVParser();
        compareValue(parser, "$999,999,999,999");
    }
}
