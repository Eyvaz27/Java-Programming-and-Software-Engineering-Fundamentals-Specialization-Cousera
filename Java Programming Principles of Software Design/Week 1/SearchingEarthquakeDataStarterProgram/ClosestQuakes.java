
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    // need to continue from here and you should update the 
    // "addQuake" function to work smoothly
    public ArrayList<QuakeEntry> addQuake(ArrayList<QuakeEntry> quakeData, QuakeEntry newQuake, Location reference, int howMany){
        int insertionIndex = 0;
        int elementCount = 0;
        // Initial Element count check 
        if(quakeData.size()==0){
            quakeData.add(newQuake);
            return quakeData;
        }
        Location newLocation = newQuake.getLocation();
        for(int index=0; index<quakeData.size(); index++){
            QuakeEntry currentQuake = quakeData.get(index); 
            Location currentLoc = currentQuake.getLocation();
            if(currentLoc.distanceTo(reference)>newLocation.distanceTo(reference)){
                    insertionIndex = index;
                    break;
            }  
        }
        if(quakeData.size()<howMany){
            elementCount = quakeData.size();
            quakeData.add(quakeData.get(elementCount-1));
        }
        for(int index=elementCount-1; index>insertionIndex; index--){
            QuakeEntry previousQuake = quakeData.get(index-1);
            quakeData.set(index, previousQuake);
        }
        quakeData.set(insertionIndex, newQuake);
        return quakeData;
    }
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location reference, int howMany) {
        ArrayList<QuakeEntry> quakesClosest = new ArrayList<QuakeEntry>();
        // TO DO
        // you need to continue from here 
        // add elements to the list as in the case of
        // insertion sort in order to maintain consistency
        int elementCount = howMany;
        if(quakeData.size()<howMany){
            elementCount = quakeData.size();
        }
        for(int k=1; k<quakeData.size(); k++){
            QuakeEntry currentQuake = quakeData.get(k); 
            quakesClosest = addQuake(quakesClosest, currentQuake, 
                                     reference, elementCount);
        }
        return quakesClosest;
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
