
/**
 * Write a description of interface Filter here.
 * 
 * @author Eyvaz Najafli
 * @version 10/08/2022
 */
public interface Filter
{
    public String getName();
    public  boolean satisfies(QuakeEntry qe); 
}
