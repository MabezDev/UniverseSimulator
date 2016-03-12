import java.io.*;
import java.util.*;

public class MySpaceObjects implements Serializable
{
    private ArrayList<Comet> comets;
    private ArrayList<Star> stars;
    private ArrayList<BlackHole> blackHoles;
    private ArrayList<Planet> planets;
    private boolean paused = true;
    private boolean done = false;
    
    public MySpaceObjects()
    {
        
    }
    
    public ArrayList<Comet> getComets(){
        return comets;
    }
    public ArrayList<Star> getStars(){
        return stars;
    }
    public ArrayList<Planet> getPlanets(){
        return planets;
    }
    public ArrayList<BlackHole> getBlackHoles(){
        return blackHoles;
    }
    
    public void setComets(ArrayList<Comet> c){
        comets = c;
    }
    public void setStars(ArrayList<Star> c){
        stars = c;
    }
    public void setPlanets(ArrayList<Planet> c){
        planets = c;
    }
    public void setBlackHoles(ArrayList<BlackHole> c){
        blackHoles = c;
    }

    
}
