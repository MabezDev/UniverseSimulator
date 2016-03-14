import java.awt.Color;
import java.util.Random;


public class Comet extends Space_Object
{   
    private Random rand;
    
    /**
     * Initializes itself and a new Random() instance.
     */
    public Comet(int xPos, int yPos, int xVel, int yVel, int objectDiameter, Color objectColor, Universe theUniverse)
    {
       super(xPos,yPos,xVel,yVel,objectDiameter, objectColor, theUniverse);
       rand = new Random();
    }
    
    /**
    * A method that returns the type of comet. 
    */
    public String getType(){
        //used for debugging
        return Integer.toString(rand.nextInt(20));
    }
}

