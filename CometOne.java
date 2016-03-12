import java.awt.*;
import java.util.Random;


public class CometOne extends Comet
{
    private int minSpeed = 1;
    private int maxSpeed = 8;
    private Random rn = new Random();
    
    
    /**
     * Create a comet of type seven.
     *
     * @param xPos  the horizontal coordinate of the object
     * @param yPos  the vertical coordinate of the object
     * @param xVel  the horizontal speed of the object
     * @param yVel  the vertical speed of the object
     * @param objectDiameter  the diameter (in pixels) of the object
     * @param objectColor  the color of the object
     * @param theUniverse  the universe this object is in
     */
    public CometOne(int xPos, int yPos, int xVel, int yVel, int objectDiameter, Color objectColor, Universe theUniverse)
    {
       super(xPos,yPos,xVel,yVel,objectDiameter, objectColor, theUniverse); 
    }
    
    /**
    * A method to change the speed of the comet between 1 and 8;
    */
    public void randomiseSpeed(){
        int nextSpeed = rn.nextInt((maxSpeed - minSpeed) + 1) + minSpeed;
        if(xSpeed > 0){
            this.xSpeed = nextSpeed;
        }else {
            this.xSpeed = nextSpeed * -1;
        }
        
        if(ySpeed > 0){
            this.ySpeed = nextSpeed;
        }else {
            this.ySpeed = nextSpeed * -1;
        }
        
    }
    
    /**
    * Changes the color of the comet from a list of valid colors
    */
    public void randomiseColor(){
        if(Math.random() < 0.2){
          int colorInt = rn.nextInt(Interface.getValidColors().length - 1);
          this.color = Interface.getValidColors()[colorInt];
        }
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public String getType(){
        return "SEVEN";
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
            randomiseSpeed();
        }
        randomiseColor();
        // draw again at new position
        universe.draw(this);
        
    }
}
