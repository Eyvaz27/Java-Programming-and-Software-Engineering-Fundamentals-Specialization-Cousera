import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }
    public void quakePrint(ArrayList<QuakeEntry> quakeData){
        for (QuakeEntry qe: quakeData) { 
            System.out.println(qe);
        } 
    }
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);         
        System.out.println("read data for "+quakeData.size()+" quakes");

        // MagnitudeFilter magFilter = new MagnitudeFilter(4.0, 5.0);
        // DepthFilter depthFilter = new DepthFilter(-35000.0, -12000.0);
        // ArrayList<QuakeEntry> highMagQuakes  = filter(quakeData, magFilter); 
        // ArrayList<QuakeEntry> deepQuakes = filter(highMagQuakes, depthFilter);
        Location Tokyo = new Location(35.42, 139.43);
        DistanceFilter distFilter = new DistanceFilter(Tokyo, 10000000);
        PhraseFilter phraseJapan = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> tokyoQuakes  = filter(quakeData, distFilter); 
        ArrayList<QuakeEntry> japanQuakes  = filter(tokyoQuakes, phraseJapan); 
        quakePrint(japanQuakes);
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);         
        System.out.println("read data for "+quakeData.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0));
        maf.addFilter(new PhraseFilter("any", "a"));
        ArrayList<QuakeEntry> filteredQuakes  = filter(quakeData, maf); 
        quakePrint(filteredQuakes);
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
