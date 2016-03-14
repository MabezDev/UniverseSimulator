import java.awt.*;
import java.util.ArrayList;

public class Star extends Space_Object
{
    
    private ArrayList<Planet> planets;
    
    /**
     * Create a star object.
     *
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param xVel  the horizontal speed of the object
     * @param yVel  the vertical speed of the object
     * @param objectDiameter  the diameter (in pixels) of the object
     * @param objectColor  the color of the object
     * @param theUniverse  the universe this object is in
     */
    public Star(int xPos, int yPos, int xVel, int yVel, int objectDiameter, Color objectColor, Universe theUniverse)
    {
       super(xPos,yPos,xVel,yVel,objectDiameter, objectColor, theUniverse);
       planets = new ArrayList<Planet>();
    }
    
    /**
     * Update all attatched planets positions.
     */
    public void updateSubObjects(){
        for(Planet p : planets){
            p.updateDegree();
            int[] t = getPointOnCircle(p.getDegrees(),p.getDiameter()*2,this.getXPosition(),this.getYPosition());//p.dimater *2 is distance from star going around
            p.move(t[0],t[1]);
        }
    }
   
   /**
   * Returns a point on an imaginary circle. Used for orbiting.
   * @param degrees - from 1 to 360, 360 being a full rotation.
   * @param radius - sets the size of the imaginary circle.
   * @param x - the centre point x of the imaginary circle.
   * @param y - the centre point y of the imaginary circle.
   * @return an array with the x and y value of a point on a circle.
   */
    public int[] getPointOnCircle(float degress, float radius,int x, int y) {
        double rads = Math.toRadians(degress - 90); // 0 becomes the top
        // Calculate the outter point of the line
        int xPosy = Math.round((float) (x + Math.cos(rads) * radius));
        int yPosy = Math.round((float) (y + Math.sin(rads) * radius));
        int[] temp = {xPosy,yPosy};
        return temp;
   }
   
   /**
   * Adds life to the star object.
   * @param life to add.
   */
   public void addLife(int i){
        lifeTime += i;
   }
   
   public int getLife(){
       return lifeTime;
   }
   
   /**
   *  Attatches a planet to this star from here the planet will orbit the star.
   *  @param the planet object to add.
   */
   public void addPlanet(Planet planet){
        planets.add(planet);
   }
    
}
