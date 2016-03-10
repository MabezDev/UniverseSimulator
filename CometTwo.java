import java.awt.*;
import java.util.Random;

public class CometTwo extends Comet
{   
    private int minSpeed = 1;
    private int maxSpeed = 8;
    private Random rn = new Random();
    
    /**
     * Create a comet of type six.
     *
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param xVel  the horizontal speed of the object
     * @param yVel  the vertical speed of the object
     * @param objectDiameter  the diameter (in pixels) of the object
     * @param objectColor  the color of the object
     * @param theUniverse  the universe this object is in
     */

    public CometTwo(int xPos, int yPos, int xVel, int yVel, int objectDiameter, Color objectColor, Universe theUniverse)
    {
       super(xPos,yPos,xVel,yVel,objectDiameter, objectColor, theUniverse); 
    }
    
    /**
    * Changes the size of the space_object randomly
    */
    public void grow(){
        if(Math.random() > 0.50){
            this.diameter += 2;
        } else {
            this.diameter -= 2;
        }
    }
    /**
    * Removes life from the lifeTime of the object randomly.
    */
    public void randomiseLifeTime(){
        if(Math.random() < 0.20){
            this.lifeTime -= 50;
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public String getType(){
        return "SIX";
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public void move()
    {
         universe.erase(this);
            
        // compute new position
       
        yPosition += ySpeed;
        xPosition += xSpeed;
        boolean touch = false;

        // check if it has hit the ground
        if(yPosition >= (groundPosition - diameter) && ySpeed > 0) {
            yPosition = groundPosition - diameter;
            ySpeed = -ySpeed;
            touch = true;
        }
        // check if top
        if(yPosition <= 0 && ySpeed < 0){
            ySpeed = -ySpeed;
            touch = true;
        }
        //check right
        if(xPosition >= (universe.getLength() - diameter) && xSpeed >0){
            xSpeed = -xSpeed;
            touch = true;
        }
        //check left
        if(xPosition <= 0 && xSpeed < 0){
            xSpeed = -xSpeed;
            touch = true;
        }
        
        if(touch){
            grow();
        }
        randomiseLifeTime();
        // draw again at new position
        universe.draw(this);
        
    }

}
