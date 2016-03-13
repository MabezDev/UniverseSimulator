import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;
/**
 * Class Universe - handles all simulation and saving and loading of universes.
 *
 * @author Robert Topp
 * @author UP745497
 * @version 2016.1.22
 */

public class Universe
{
    private transient Canvas universe;//stops the canvas being saved
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
        rightEdge = 600;
        bottomEdge = 500;
        universe = new Canvas("Universe Simulator",rightEdge,bottomEdge);
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
                }
                
                for(Planet planet: planets){
                     if((c.getXPosition() + planet.getDiameter()/2 + c.getDiameter()/2 > planet.getXPosition()) && (c.getXPosition() < planet.getXPosition()+planet.getDiameter()/2 + c.getDiameter()/2) && 
                     (c.getYPosition() +planet.getDiameter()/2 + c.getDiameter()/2 > planet.getYPosition()) && (c.getYPosition() < planet.getYPosition()+planet.getDiameter()/2 + c.getDiameter()/2)){
                        planet.addToDiameter(c.getDiameter()/2);
                        c.destroy();
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
                            c.destroy();
                        }
                    }
                }
                
                for(Comet c2 : comets){
                    if((c.getXPosition() > c2.getXPosition()-c2.getDiameter()/2) && (c.getXPosition() < c2.getXPosition()+c2.getDiameter()/2) && 
                    (c.getYPosition() > c2.getYPosition()-c2.getDiameter()/2) && (c.getYPosition() < c2.getYPosition()+c2.getDiameter()/2)){
                        if(!c.getType().equals(c2.getType())){
                            if(c.getDiameter() > c2.getDiameter()){
                                c2.destroy();
                            } else if(c.getDiameter() < c2.getDiameter()){
                                c.destroy();
                            } else {
                                if((c.getXSpeed() > c2.getXSpeed()) && (c.getYSpeed() > c2.getYSpeed())){
                                    c2.destroy();
                                } else if((c.getXSpeed() < c2.getXSpeed()) && (c.getYSpeed() < c2.getYSpeed())) {
                                    c.destroy();
                                }
                            }
                        } else { // need testing
                            c.setXSpeed(-1*c.getXSpeed());
                            c.setYSpeed(-1*c.getYSpeed());
                            c2.setXSpeed(-1*c2.getXSpeed());
                            c2.setYSpeed(-1*c2.getYSpeed());
                        }
                    }
                }
                
                if(c.isDestroyed()){
                    removeC.add(c);
                }
           }
           
           for(Star star: stars){
               this.draw(star);
               star.updateLife();
               star.updateSubObjects();//updates it surrounding planets
               if(star.isDestroyed()){
                        removeS.add(star);
               }
           }
           
           for(BlackHole blackHole: blackHoles){
               this.draw(blackHole);
               blackHole.updateLife();
               if(blackHole.isDestroyed()){
                        removeB.add(blackHole);
               }
           }
           
           for(Planet planet: planets){
               planet.updateLife();
               if(planet.isDestroyed()){
                   removeP.add(planet);
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
     * Used when loading in new Space Objects to give them a universe to exist in.
     */
    public void applyUniverse(){
       for(Star star: stars){
           star.addUniverse(this);
       }
           
       for(BlackHole blackHole: blackHoles){
           blackHole.addUniverse(this);
       }
       
       for(Planet planet: planets){
           planet.addUniverse(this);
       }
       for(Comet comet : comets){
           comet.addUniverse(this);
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
    
    /**
     * Adds a star to the the stars Array
     * @param Star object to add
     */
    public void addStar(Star star){
        stars.add(star);
    }
     /**
     * Adds a planet to the the planet Array
     * @param PLanet object to add
     */
    public void addPlanet(Star star,Planet planet){
        planets.add(planet);
        star.addPlanet(planet);
    }
     /**
     * Adds a blackHole to the the blackHole Array
     * @param Star object to add
     */
    public void addBlackHole(BlackHole bh){
        blackHoles.add(bh);
    }
     /**
     * Adds a comet to the the comet Array
     * @param Comet object to add
     */
    public void addComet(Comet c){
        comets.add(c);
    }
    
    /**
     * Pauses the simulation
     */
    public void pause(){
        paused = true;
    }
    
    /**
     * Resumes the simulation
     */
    public void resume(){
        paused = false;
    }
    
    /**
     * Returns if the simulation is paused.
     * @return boolean paused.
     */
    public boolean isPaused(){
        return paused;
    }
    
    /**
     * Stops the simulation.
     */
    public void finish(){
        done = true;
    }
    
    
    /**
     * Saves the Space objects into a serializable class called MySpaceObjects.
     * @param String file name of the save.
     */
    public void save(String filename){
        // make a copy of all the space objects
        MySpaceObjects save = new MySpaceObjects();
        save.setComets(comets);
        save.setPlanets(planets);
        save.setStars(stars);
        save.setBlackHoles(blackHoles);
        //write object to file
        SaveAndLoad.saveFile(save,filename);
    }
    
    /**
     * Loads a serialized file back into an object, then loads the data
     * into the current universe.
     * @param String filename of the file being loaded.
     */
    public void load(String filename){
       //before we load we clear all objects off the screen
       this.eraseAll(comets);
       this.eraseAll(stars);
       this.eraseAll(planets);
       this.eraseAll(blackHoles);
       //then load in the new ones and continue
       MySpaceObjects save = SaveAndLoad.loadSpaceObjects(filename);
       if(save==null){
           System.out.println("Failed to load universe from '"+filename+"'");
           return;
       }
       comets = save.getComets();
       planets = save.getPlanets();
       stars = save.getStars();
       blackHoles = save.getBlackHoles();
    }
    
    
    
    /**
    * Erases all objects from an array from the canvas.
    * @param ArrayList of Space_Objects to remove.
    */
    public void eraseAll(ArrayList toRemove){
        for(Object spaceObj1 : toRemove){
            Space_Object spaceObj = (Space_Object) spaceObj1;
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
