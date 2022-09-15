
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
     
     public int countUniqueIPs() {
         // uniqueIPs starts as an empty list
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         // for each element LEnog Entry in records
         for (LogEntry le: records) {
             // ipAddr is le's ipAddress 
             String ipAddr = le. getIpAddress();
             // if ipAddr is not in uniqueIPs
             if(!uniqueIPs.contains(ipAddr)){
                 // add ipAddr to uniqueIPs
                 uniqueIPs.add(ipAddr);
             }
         }
         // return the size of uniqueIPs
         return uniqueIPs.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         // Make an empty HashMap <String, Integer> counts
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         // For each Log Entry in records
         for (LogEntry le: records) {
             String ip = le.getIpAddress();
             if (!counts.containsKey(ip))
                counts.put(ip, 1);
             else
                counts.put(ip, counts.get(ip)+1);
         }
         return counts;
     }
}
