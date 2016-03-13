import java.io.*;
import java.util.*;
/**
 * A blank serializable class used to store universe instances.
 */
public class MySpaceObjects implements Serializable
{
    private ArrayList<Comet> comets;
    private ArrayList<Star> stars;
    private ArrayList<BlackHole> blackHoles;
    private ArrayList<Planet> planets;
    
    /**
     * Empty Constructor.
     */
    public MySpaceObjects()
    {
        
    }
    /**
     * Returns an ArrayList of Comets
     */
    public ArrayList<Comet> getComets(){
        return comets;
    }
    /**
     * Returns an ArrayList of Stars
     */
    public ArrayList<Star> getStars(){
        return stars;
    }
    /**
     * Returns an ArrayList of Planets
     */
    public ArrayList<Planet> getPlanets(){
        return planets;
    }
    /**
     * Returns an ArrayList of BlackHoles
     */
    public ArrayList<BlackHole> getBlackHoles(){
        return blackHoles;
    }
    /**
     * Stores an Array of Comets
     */
    public void setComets(ArrayList<Comet> c){
        comets = c;
    }
    /**
     * Stores an Array of Stars
     */
    public void setStars(ArrayList<Star> c){
        stars = c;
    }
    /**
     * Stores an Array of Planets
     */
    public void setPlanets(ArrayList<Planet> c){
        planets = c;
    }
    /**
     * Stores an Array of BlackHoles
     */
    public void setBlackHoles(ArrayList<BlackHole> c){
        blackHoles = c;
    }
}
