import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry entry: quakeData){
            if(entry.getMagnitude()>magMin)
                answer.add(entry);
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(int i=0; i<quakeData.size(); i++){
            QuakeEntry currQuake = quakeData.get(i);
            Location currLocation = currQuake.getLocation();
            //System.out.println(from.distanceTo(currLocation));
            if(from.distanceTo(currLocation)<distMax)
                answer.add(currQuake);
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(int i=0; i<quakeData.size(); i++){
            QuakeEntry currQuake = quakeData.get(i);
            double currDepth = currQuake.getDepth();
            if(currDepth<maxDepth &&  currDepth>minDepth)
                answer.add(currQuake);
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, String phrase) {
        int phraseLength = phrase.length();
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(int i=0; i<quakeData.size(); i++){
            QuakeEntry currQuake = quakeData.get(i);
            String currTitle = currQuake.getInfo();
            int titleLength = currTitle.length();
            if(where.contentEquals("start")){
                if(currTitle.startsWith(phrase))
                    answer.add(currQuake);
            }
            else if(where.contentEquals("end")){
                if(currTitle.endsWith(phrase))
                    answer.add(currQuake);
            }
            else if(where.contentEquals("any")){
                if(currTitle.indexOf(phrase)!=-1)
                    answer.add(currQuake);
            }
        }
        return answer;
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        System.out.println("Big Quakes in Dataset:");
        ArrayList<QuakeEntry> bigQuakes = filterByMagnitude(list, 5.0);
        for(QuakeEntry entry: bigQuakes)
            System.out.println(entry);
        System.out.println("Found " + bigQuakes.size() + 
                           " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        // distanceTo function compares the locations in meters
        ArrayList<QuakeEntry> closeQuakes = filterByDistanceFrom(list, 1000000, city);
        for(QuakeEntry entry: closeQuakes)
            System.out.println(entry.getLocation().distanceTo(city)/1000 + 
                               " " + entry.getInfo());
        System.out.println("Found " + closeQuakes.size() + 
                           " quakes that match that criteria");
    }
    
    public void quakesOfDepth (){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        // TODO
        ArrayList<QuakeEntry> deepQuakes = filterByDepth(list, -10000.0, -5000.0);
        for(QuakeEntry entry: deepQuakes)
            System.out.println(entry);
        System.out.println("Found " + deepQuakes.size() + 
                           " quakes that match that criteria");
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        // TODO
        ArrayList<QuakeEntry> phraseQuakes = filterByPhrase(list, "start", "Explosion");
        for(QuakeEntry entry: phraseQuakes)
            System.out.println(entry);
        System.out.println("Found " + phraseQuakes.size() + 
                           " quakes that match that criteria");
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
