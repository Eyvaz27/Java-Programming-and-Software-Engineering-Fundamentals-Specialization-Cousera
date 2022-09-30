
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<Integer> closestIndexes = new ArrayList<Integer>();
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        // you need to continue from here 
        // add elements to the list as in the case of
        // insertion sort in order to maintain consistency
        for (int loop=0; loop<howMany; loop++){
            int minIndex = 0; 
            for(int k=1; k<quakeData.size(); k++){
                QuakeEntry currentQuake = quakeData.get(k); 
                Location currentLoc = currentQuake.getLocation();
                Location minLocation = quakeData.get(minIndex).getLocation();
                if(currentLoc.distanceTo(current)<minLocation.distanceTo(current)){
                    if(!closestIndexes.contains(k))                    
                        minIndex = k;
                }   
            }
            ret.add(quakeData.get(minIndex));
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,10);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
