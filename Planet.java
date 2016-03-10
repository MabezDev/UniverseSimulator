
import java.awt.*;

public class Planet extends Space_Object
{
    private int degrees;
    
   /**
   * Constructor for objects of class Planet.
   *
   * @param xPos  the horizontal coordinate of the object
   * @param yPos  the vertical coordinate of the object
   * @param xVel  the horizontal speed of the object
   * @param yVel  the vertical speed of the object
   * @param objectDiameter  the diameter (in pixels) of the object
   * @param objectColor  the color of the object
   * @param theUniverse  the universe this object is in
   */
   public Planet(int xPos, int yPos, int xVel, int yVel, int objectDiameter, Color objectColor, Universe theUniverse)
   {
      super(xPos,yPos,xVel,yVel,objectDiameter, objectColor, theUniverse); 
      degrees = 1;
   }
    
   /**
   *  Add to the diameter of the planet.
   *  @param the amount to add to the diameter.
   */
   public void addToDiameter(int i){
        diameter +=i;
   }
   
   /**
   * Updates the position around the oribt in degrees (1- 360). 
   */
   public void updateDegree(){
       degrees +=2;
       if(degrees > 360){
           degrees = 1;
       }
   }
   
   /**
   * Returns the current progress round the orbit in degrees.
   */
   public int getDegrees(){
       return degrees;
   }
   
}


