
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
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> freqMap) {
         int maxFreq = 0;
         for (String key: freqMap.keySet()) {
             int currFreq = freqMap.get(key);
             if (currFreq>maxFreq)
                 maxFreq = currFreq;
         }
         return maxFreq;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> freqMap){
         ArrayList<String> freqIPs = new ArrayList<String>();
         int mostVisits = mostNumberVisitsByIP(freqMap);
         for (String key: freqMap.keySet()) {
             int currFreq = freqMap.get(key);
             if (currFreq == mostVisits)
                if(!freqIPs.contains(key))
                    freqIPs.add(key);
         }
         return freqIPs;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> iPs = new HashMap<String, ArrayList<String>>();
         for (LogEntry le: records) {
             String ipAddress = le.getIpAddress();
             String accessTime = le.getAccessTime().toString();
             String dayTime = accessTime.substring(4, 10);
             if (iPs.containsKey(dayTime))
             {
                ArrayList<String> temp = iPs.get(dayTime);
                temp.add(ipAddress);
                iPs.put(dayTime, temp);
             }
             else { 
                 ArrayList<String> temp = new ArrayList<String>();
                 temp.add(ipAddress);
                 iPs.put(dayTime, temp);
             }
         }
         return iPs;
     }
     
     public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> iPs) {
         int maxFreq = 0;
         String busyDay = "";
         for (String key: iPs.keySet()) {
             ArrayList<String> temp = iPs.get(key);
             if (maxFreq < temp.size()) {
                 maxFreq = temp.size();
                 busyDay = key;
             }
         }
         return busyDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dailyIPs, String someday) {
         ArrayList<String> dayAnanlysis = dailyIPs.get(someday);
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (String IP: dayAnanlysis) {
             if (!counts.containsKey(IP))
                counts.put(IP, 1);
             else
                counts.put(IP, counts.get(IP)+1);
         }
         return iPsMostVisits(counts);
     }
     
     public void printAllHigherThanNum (int num) {
         for(LogEntry le: records) {
             int statusCode = le.getStatusCode();
             if (statusCode > num)
                System.out.println(le);
         }
     }
     
     public int countUniqueIPsInRange (int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le: records) {
             String ipAddress = le.getIpAddress();
             int statusCode = le.getStatusCode();
             if (statusCode <= high && statusCode >= low)
                if (!uniqueIPs.contains(ipAddress))
                    uniqueIPs.add(ipAddress);
         }
         return uniqueIPs.size();
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay (String someday) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le: records) {
             String ipAddress = le.getIpAddress();
             String accessTime = le.getAccessTime().toString();
             if (accessTime.substring(4, 10).equals(someday))
                if (!uniqueIPs.contains(ipAddress))
                    uniqueIPs.add(ipAddress);
         }
         return uniqueIPs;
     }
}
