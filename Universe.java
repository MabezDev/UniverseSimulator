import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Class Universe - a universe which contains 3 objects  
 *
 * @author Robert Topp
 * @version 2016.1.22
 */

public class Universe
{
    private Canvas universe;
    private int leftEdge = 0;     // coordinates of the edges of the universe
    private int topEdge = 0;
    private int rightEdge;
    private int bottomEdge;
    private static int Area = 10;
    
    private ArrayList<Comet> comets;
    private ArrayList<Star> stars;
    private ArrayList<BlackHole> blackHoles;
    private ArrayList<Planet> planets;
    private boolean paused = true;
    private boolean done = false;
    /**
     * Create a universe with default name and size. Creates a fresh canvas to display the universe
     */
    public Universe()
    {
        universe = new Canvas("Universe Simulation", 600, 500);
        rightEdge = 600;
        bottomEdge = 500;
        comets = new ArrayList<Comet>();
        stars = new ArrayList<Star>();
        blackHoles = new ArrayList<BlackHole>();
        planets = new ArrayList<Planet>();
       
        
        Star s1 = new Star(80,20,0,0,25,Color.YELLOW,this);
        stars.add(s1);
        Planet p1 = new Planet(0,0,0,0,25,Color.GREEN,this);
        planets.add(p1);
        s1.addPlanet(p1);
        
        blackHoles.add(new BlackHole(300,250,0,0,25,Color.BLACK,this));
        comets.add(new CometOne(100,300,2,2,25,Color.RED,this));
        comets.add(new CometTwo(50,400,2,2,25,Color.RED,this));
        
        
    }
    /**
     *  Create a universe with given name and size
     *  @param name The name to give the universe
     *  @param rightEdge The maximum x coordinate
     *  @param bottomEdge The maximum y coordinate
     */
     public Universe(String name, int rightEdge, int bottomEdge)
    {
        universe = new Canvas(name, rightEdge, bottomEdge);
        this.rightEdge = rightEdge;
        this.bottomEdge = bottomEdge;
    }
    
   
    /**
    *Main Loop, handles all collision detection and object updates.  
    */
    public void main(){
        while(done == false){
            if(!paused){
            universe.wait(50);
               
            ArrayList removeC = new ArrayList();
            ArrayList removeP = new ArrayList();
            ArrayList removeB = new ArrayList();
            ArrayList removeS = new ArrayList();
            
            /*
             * ATTENTION - >>>> NEED TO PUT THE OTHER OBJECT BACK IN THERE OWN LOOPS BECAUSE WHEN ALL TH ECOMETS DIE THE SIMULATION STOPS
             */
            for(Comet c : comets){
                c.move();
                c.updateLife();
                //only comets can collide so well do the processing here
                for(Star star: stars){
                    if((c.getXPosition() + star.getDiameter()/2 + c.getDiameter()/2 > star.getXPosition()) && (c.getXPosition() < star.getXPosition()+star.getDiameter()/2 + c.getDiameter()/2) && 
                    (c.getYPosition() +star.getDiameter()/2 + c.getDiameter()/2 > star.getYPosition()) && (c.getYPosition() < star.getYPosition()+star.getDiameter()/2 + c.getDiameter()/2)){
                        star.addLife((int) Math.PI * c.getDiameter()/2);
                        c.destroy();
                    }
                    this.draw(star);
                    star.updateLife();
                    star.updateSubObjects();//updates it surrounding planets
                    if(star.isDestroyed()){
                        removeS.add(star);
                    }
                }
                
                for(Planet planet: planets){
                     if((c.getXPosition() + planet.getDiameter()/2 + c.getDiameter()/2 > planet.getXPosition()) && (c.getXPosition() < planet.getXPosition()+planet.getDiameter()/2 + c.getDiameter()/2) && 
                     (c.getYPosition() +planet.getDiameter()/2 + c.getDiameter()/2 > planet.getYPosition()) && (c.getYPosition() < planet.getYPosition()+planet.getDiameter()/2 + c.getDiameter()/2)){
                        planet.addToDiameter(c.getDiameter()/2);
                        c.destroy();
                    }
                    planet.updateLife();
                    if(planet.isDestroyed()){
                        removeP.add(planet);
                    }
                }
                
                for(BlackHole blackHole: blackHoles){
                    if((c.getXPosition() > blackHole.getXPosition()-blackHole.getDiameter() - Area) && (c.getXPosition() < blackHole.getXPosition()+blackHole.getDiameter() + Area) && 
                    (c.getYPosition() > blackHole.getYPosition()-blackHole.getDiameter() - Area) && (c.getYPosition() < blackHole.getYPosition()+blackHole.getDiameter() + Area)){
                        //move the comet closer
                        c.setXSpeed((blackHole.getXPosition() - c.getXPosition())/4);
                        c.setYSpeed((blackHole.getYPosition() - c.getYPosition())/4);
                        //check if its been completely sucked in
                        if((c.getXPosition() > blackHole.getXPosition() - 5) && (c.getXPosition() < blackHole.getXPosition()+5) && 
                        (c.getYPosition() > blackHole.getYPosition() - 5) && (c.getYPosition() < blackHole.getYPosition() + 5)){
                            removeC.add(c);
                        }
                    }
                    this.draw(blackHole);
                    blackHole.updateLife();
                    if(blackHole.isDestroyed()){
                        removeB.add(blackHole);
                    }
                }
                
                for(Comet c2 : comets){
                    if((c.getXPosition() > c2.getXPosition()-c2.getDiameter()/2) && (c.getXPosition() < c2.getXPosition()+c2.getDiameter()/2) && 
                    (c.getYPosition() > c2.getYPosition()-c2.getDiameter()/2) && (c.getYPosition() < c2.getYPosition()+c2.getDiameter()/2)){
                        if(!c.getType().equals(c2.getType())){
                            if(c.getDiameter() > c2.getDiameter()){
                                removeC.add(c2);
                            } else if(c.getDiameter() < c2.getDiameter()){
                                removeC.add(c);
                            } else {
                                if((c.getXSpeed() > c2.getXSpeed()) && (c.getYSpeed() > c2.getYSpeed())){
                                    removeC.add(c2);
                                } else if((c.getXSpeed() < c2.getXSpeed()) && (c.getYSpeed() < c2.getYSpeed())) {
                                    removeC.add(c);
                                }
                            }
                        } else {
                            //bounce of each other
                        }
                    }
                }
                
                if(c.isDestroyed()){
                    removeC.add(c);
                }
           }
           
           
            this.eraseAll(removeC);
            comets.removeAll(removeC);
            
            this.eraseAll(removeS);
            stars.removeAll(removeS);
            
            this.eraseAll(removeP);
            planets.removeAll(removeP);
            
            this.eraseAll(removeB);
            blackHoles.removeAll(removeB);
            
            
        } else {
            try{
                Thread.sleep(10);
            } catch (Exception e){
                
            }
        }
    }
            
    }
    
    /**
     * Erase an object from the view of the universe
     * 
     * @param spaceObj The object to be erased
     */
    public void erase(Space_Object spaceObj)
    {
        universe.eraseCircle(spaceObj.getXPosition(), spaceObj.getYPosition(), spaceObj.getDiameter());
    }
    
    public void addStar(Star star){
        stars.add(star);
    }
    
    public void addPlanet(Star star,Planet planet){
        planets.add(planet);
        star.addPlanet(planet);
    }
    
    public void addBlackHole(BlackHole bh){
        blackHoles.add(bh);
    }
    
    public void addComet(Comet c){
        comets.add(c);
    }
    
    public void pause(){
        paused = true;
    }
    
    public void resume(){
        paused = false;
    }
    
    public boolean isPaused(){
        return paused;
    }
    
    public void finish(){
        done = true;
    }
    
    
    
    /**
    * Erases all objects from an array from the canvas.
    * @param ArrayList of Space_Objects to remove.
    */
    public void eraseAll(ArrayList<Space_Object> toRemove){
        for(Space_Object spaceObj : toRemove){
            universe.eraseCircle(spaceObj.getXPosition(), spaceObj.getYPosition(), spaceObj.getDiameter());
        }
    }
    
    
     /**
     * Draw an object at its current position onto the view of the universe.
     * 
     * @param spaceObj The object to be drawn
     */
    public void draw(Space_Object spaceObj)
    {
        universe.setForegroundColor(spaceObj.getColor());
        universe.fillCircle(spaceObj.getXPosition(), spaceObj.getYPosition(), spaceObj.getDiameter());
    }
    
    /**
     * Return the y cordinate of the bottom of the universe
     */
    public int getGround()
    {
        return bottomEdge;
    }
    
    /**
    * Returns the right edge of the universe aka the height.  
    */
    public int getLength(){
        return rightEdge;
    }
}
