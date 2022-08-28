
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;
 
public class BabyBirths {
    String search_path = "E:/Company Folders/Development/JAVA/" + 
                         "Java Programming and Software Engineering " + 
                         "Fundamentals Specialization/Java Programming" + 
                         " Solving Problems with Software/" + 
                         "Using BlueJ to Program in Java/Week 4/" + 
                         "us_babynames/us_babynames_by_year";
    public void printNames() {
        FileResource fr = new FileResource();
        int numBorn = 0;
        for (CSVRecord rec: fr.getCSVParser(false)) {
            numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <= 100)
                System.out.println("Name " + rec.get(0) + 
                                   " Gender " + rec.get(1) +
                                   " Num Born " + rec.get(2));
        }
    }
    public void totalBirths (FileResource fr) {
        int numBorn = 0;
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int numberBoys = 0;
        int numberGirls = 0;
        for (CSVRecord rec: fr.getCSVParser(false)) {
            numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M"))
            {
                totalBoys += numBorn;
                numberBoys = numberBoys + 1;}
            else if(rec.get(1).equals("F")){
                totalGirls += numBorn;
                numberGirls = numberGirls + 1;}
        }
        System.out.println("Total Births = " + totalBirths);
        System.out.println("Total Boy Births = " + totalBoys);
        System.out.println("Total Girl Births = " + totalGirls);
        System.out.println("Total Number Boy = " + numberBoys);
        System.out.println("Total Number Girl = " + numberGirls);
    }
    public void testTotalBirths () {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public int getRank (int year, String name, String gender) {
        String file_name = "/yob" + String.valueOf(year) + ".csv";
        String directory = search_path + file_name;
        int ranking = 0;
        boolean flag = false;
        int male_index = 0;
        boolean male_found = false;
        FileResource fr = new FileResource(directory);
        for (CSVRecord rec: fr.getCSVParser(false)) {
            if(!male_found && rec.get(1).contentEquals("M")){
                    male_found = true;
                    male_index = ranking;
            }
            ranking = ranking + 1;
            if(rec.get(0).contentEquals(name) && 
               rec.get(1).contentEquals(gender))
            {
                flag = true;
                break;
            }
        }
        if(!flag)
            return -1;
        else
            if(gender.contentEquals("M"))
                return ranking - male_index;
            else
                return ranking;
    }
    public void testgetRank(){
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        int result = getRank(year, name, gender);
        System.out.println("Name " + name + 
                                   " Gender " + gender +
                                   " Ranking " + result);
    }
    public String getName(int year, int rank, String gender){
        String file_name = "/yob" + String.valueOf(year) + ".csv";
        String directory = search_path + file_name;
        int index = 0;
        int male_index = 0;
        boolean male_found = false;
        boolean flag = false;
        String person_name = "";
        FileResource fr = new FileResource(directory);
        for (CSVRecord rec: fr.getCSVParser(false)) {
            if(gender.contentEquals("M") && !male_found)
                if(rec.get(1).contentEquals("M")){
                    male_found = true;
                    male_index = index;
                }
            index = index + 1;
            if(rec.get(1).contentEquals(gender))
                if(gender.contentEquals("F")){
                    if(index == rank) {
                        person_name = rec.get(0);
                        flag = true;
                        break;
                    }
                }
                else if(index - male_index == rank){
                    person_name = rec.get(0);
                    flag = true;
                    break;
                }
            
        }
        if(!flag)
            return "NO NAME";
        else
            return person_name;
    }
    public void testgetName(){
        int year = 1880;
        int rank = 3;
        String gender = "M";
        String result = getName(year, rank, gender);
        System.out.println("Name " + result + 
                                   " Gender " + gender +
                                   " Year " + year);
    }
    public void whatIsNameInYear(String name, int year,
                                 int newYear, String gender){
        int rank = getRank (year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + 
                           " would be " + newName + " if "+
                           "born in " + newYear);
    }
    public void yearOfHighestRank(String name, String gender) {
        int yearOfHR = 0;
        int best_ranking = 1000000000;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            String file_name = f.getName();
            int year = Integer.parseInt(file_name.substring(3, 7));
            int ranking = getRank (year, name, gender);
            if(ranking < best_ranking && ranking != -1){
                best_ranking = ranking;
                yearOfHR = year;
            }
        }
        if(best_ranking != 1000000000){
            System.out.println("Name " + name + 
                               " Gender " + gender +
                               " Most Popular in " + 
                               yearOfHR + " ## ranking = " +
                               best_ranking);
        }
        else
            System.out.println("Not Found");
    }
    public void getAverageRank(String name, String gender) {
        int average_ranking = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            String file_name = f.getName();
            int year = Integer.parseInt(file_name.substring(3, 7));
            int ranking = getRank (year, name, gender);
            if(ranking > -1)
                average_ranking = average_ranking + ranking;
        }
        double average = average_ranking / 3.0;
        System.out.println("Name " + name + " Gender " + gender +
                           " Average Ranking " + average);
        
    }
    public void getTotalBirthsRankedHigher(int year, String name, String gender){
        String file_name = "/yob" + String.valueOf(year) + ".csv";
        String directory = search_path + file_name;
        int ranking = 0;
        int total_births = 0;
        boolean flag = false;
        int male_index = 0;
        boolean male_found = false;
        FileResource fr = new FileResource(directory);
        for (CSVRecord rec: fr.getCSVParser(false)) {
            if(!male_found && rec.get(1).contentEquals("M")){
                    male_found = true;
                    male_index = ranking;
            }
            ranking = ranking + 1;
            if(rec.get(1).contentEquals(gender)){
                    if(rec.get(0).contentEquals(name))
                        break;
                    else
                        total_births = total_births + Integer.parseInt(rec.get(2));
            }
        }
        System.out.println("Name " + name + "\nGender " + gender +
                           "\nYear " + year + 
                           "\nTotal Births Ranked Higher " + total_births);
    }
}


