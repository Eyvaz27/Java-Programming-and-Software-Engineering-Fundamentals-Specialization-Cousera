
/**
 * Write a description of class Tester here.
 * 
 * @author Eyvaz Najafli
 * @version 09/11/2022
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer test = new LogAnalyzer();
        test.readFile();
        int uniqueIPs = test.countUniqueIPs();
        HashMap<String, ArrayList<String>> dayAnalysis;
        dayAnalysis = test.iPsForDays();
        System.out.println("There are "+ uniqueIPs+" IPs");
        System.out.println("Most Busy Day - "+
                           test.dayWithMostIPVisits(dayAnalysis));
        ArrayList<String> mostVisitingIPs = test.iPsWithMostVisitsOnDay(dayAnalysis, "Sep 29");
        System.out.println("Most Active Users on Sep 29 -- "+mostVisitingIPs);
        HashMap<String, Integer> counts = test.countVisitsPerIP();
        System.out.println("Max Unique Visits = "+
                           test.mostNumberVisitsByIP(counts));
        System.out.println("Most Active Users :\n"+
                           test.iPsMostVisits(counts));
        System.out.println("Unique Users :\n"+counts);
        //test.printAll();
        System.out.println("Unique Visits on Sep 24 = "+
                           test.uniqueIPVisitsOnDay("Sep 24").size());
        System.out.println("Unique Visits on Sep 27 = "+
                           test.uniqueIPVisitsOnDay("Sep 27").size());
        System.out.println("Unique IPs in range 200-299 = "+
                           test.countUniqueIPsInRange(200, 299));
        System.out.println("Unique IPs in range 400-499 = "+
                           test.countUniqueIPsInRange(400, 499));      
        //int num = 0;
        //System.out.println("Printing Valus Higher than "+num);
        //test.printAllHigherThanNum(num);
    }
}
