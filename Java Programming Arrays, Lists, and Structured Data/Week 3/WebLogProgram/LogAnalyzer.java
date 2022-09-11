
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author Eyvaz Najafli
 * @version 09/11/2022
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private WebLogParser parser;
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         parser = new WebLogParser();
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile() {
         // complete method
         FileResource searchFile = new FileResource();
         for(String line: searchFile.lines()){
             LogEntry temp = parser.parseEntry(line);
             records.add(temp);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
