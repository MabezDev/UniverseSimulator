import java.awt.*;


public class Comet extends Space_Object
{   
    

    public Comet(int xPos, int yPos, int xVel, int yVel, int objectDiameter, Color objectColor, Universe theUniverse)
    {
       super(xPos,yPos,xVel,yVel,objectDiameter, objectColor, theUniverse); 
    }
    
    /**
    * A method that returns the type of comet. 
    */
    public String getType(){
        return null;
    }
}

