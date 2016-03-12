import java.awt.*;
import java.util.ArrayList;
import java.io.*;


/**
 * Class Space_Object - an object that exists in a finite Universe. The object bounces off
 * the bottom edge of the universe.
 * 
 * Movement can be initiated by repeated calls to the "move" method.
 * 
 * N.B This class is incomplete and still under development. It will require updating to
 * complete the INTPROG coursework assignment.
 * 
 * @author Robert Topp
 *
 * @version 2016.01.22
 */

public class Space_Object implements Serializable
{
    protected Color color;
    protected int diameter;
    protected int xPosition;
    protected int yPosition;
    protected final int groundPosition;     // y position of the bottom of the Universe
    protected Universe universe;
    protected int xSpeed;                 // current horizontal speed   (+  moving left to right, - right to left
    protected int ySpeed;                // current vertical speed ( + moving down, - moving up)
    protected int lifeTime;
    protected boolean destroyed;

    /**
     * Constructor for objects of class Space_Object
     *
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param xVel  the horizontal speed of the object
     * @param yVel  the vertical speed of the object
     * @param objectDiameter  the diameter (in pixels) of the object
     * @param objectColor  the color of the object
     * @param theUniverse  the universe this object is in
     */
    public Space_Object(int xPos, int yPos, int xVel, int yVel, int objectDiameter, Color objectColor, Universe theUniverse)
    {
        xPosition = xPos;
        yPosition = yPos;
        xSpeed = xVel;
        ySpeed = yVel;
        color = objectColor;
        diameter = objectDiameter;
        universe = theUniverse;
        groundPosition = universe.getGround();
        lifeTime = 1000000;
        destroyed = false;
        //degrees = 1;
        
    }
    
    public Space_Object (){
        groundPosition = 0;
    }

   
    /**
     * Move this object according to its position and speed and redraw.
     **/
    public void move()
    {
         universe.erase(this);
            
        // compute new position
       
        yPosition += ySpeed;
        xPosition += xSpeed;

        // check if it has hit the ground
        if(yPosition >= (groundPosition - diameter) && ySpeed > 0) {
            yPosition = groundPosition - diameter;
            ySpeed = -ySpeed; 
        }
        // check if top
        if(yPosition <= 0 && ySpeed < 0){
            ySpeed = -ySpeed; 
        }
        //check right
        if(xPosition >= (universe.getLength() - diameter) && xSpeed >0){
            xSpeed = -xSpeed;
        }
        //check left
        if(xPosition <= 0 && xSpeed < 0){
            xSpeed = -xSpeed;
        }
        
        // draw again at new position
        universe.draw(this);
    }
    
    
    /**
    * Set the position of a Space_Object to
    * a certain (x,y) coordinate.
    * @param x position to move to 
    * @param y position to move to.
    */
    public void move(int x, int y){
        universe.erase(this);
        this.yPosition = y;
        this.xPosition = x;
        universe.draw(this);
    }
    /**
    * Removes life from a Space_Object and checks if it needs to be destroyed.
    */
    public void updateLife(){
        lifeTime--;
        if(lifeTime <= 0){
            destroyed = true;
        }
    }
    
    /**
    * Sets the destroyed boolean to true.
    */
    public void destroy(){
        destroyed = true;
    }
    
    /**
    * Returns if the object is destroyed.
    * @return true if the object is destroyed, else false.
    */
    public boolean isDestroyed(){
        return destroyed;
    }

    /**
     * return the horizontal position of this object.
     */
    public int getXPosition()
    {
        return xPosition;
    }
    /**
    * Returns current X Speed.
    */
    public int getXSpeed(){
        return xSpeed;
    }
    
    /**
    * Returns current Y Speed.
    */
    public int getYSpeed(){
        return ySpeed;
    }
    
    /**
    * Sets the current XSpeed.
    * @param Speed to change.
    */
    public void setXSpeed(int speed){
        xSpeed = speed;
    }
    /**
    * Sets the current YSpeed.
    * @param Speed to change.
    */
    public void setYSpeed(int speed){
        ySpeed = speed;
    }

    /**
     * return the vertical position of this object
     */
    public int getYPosition()
    {
        return yPosition;
    }
    
    /**
     * return the colour of this object
    */
    public Color getColor()
    {   
        return color;
    }
    
    /**
     * return the diameter of this object
     */
    public int getDiameter()
    {
        return diameter;
    }
}
