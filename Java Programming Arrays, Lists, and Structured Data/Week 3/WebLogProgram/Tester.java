
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
        System.out.println("There are "+ uniqueIPs+" IPs");
        HashMap<String, Integer> counts = test.countVisitsPerIP();
        System.out.println("Unique Users :\n"+counts);
        test.printAll();
    }
}
