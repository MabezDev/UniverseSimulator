
import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * Handles all input and handles the simulations on a separate thread.
 */
public class Interface
{
    private Universe universe;
    private SimulationThread gt;
    private Scanner scanner;
    private boolean done = false;
    private Thread simulator;
    private Canvas canvas;
    private static Color[] validColors = {Color.RED,Color.CYAN,Color.BLUE,Color.GRAY,Color.PINK,Color.GREEN};
    private static final String SAVES_PATH = "saves/";
    private boolean cancelled = false;
    
    /**
     * Creates the CLI and initializes and controls the simulation thread.
     */
    public Interface()
    {
        scanner = new Scanner(System.in);
        gt = new SimulationThread();
        simulator = new Thread(gt);
        universe = gt.getUniverse();
        simulator.start();
        System.out.println("");
        System.out.println("Typing cancel at anytime will bring you back to the menu!");
        System.out.println("");
        while(!done){
            update();
        }
    }
    
    /**
     * Shuts down the simulator and CLI and exits the program cleanly.
     */
    private void finish(){
        universe.finish();
        done  = true;
        System.exit(0);
    }
    
    /**
     * Main Input Function
     */
    public void update(){
        printMenu();
        mainLoop:
        while(scanner.hasNextLine()){
            try{
                int choice = getValidInt(1,9);
                if(choice==5){
                    if(universe.isPaused()){
                        universe.resume();
                    } else {
                        universe.pause();
                    }
                    break;
                } else if(choice==6){
                    System.out.println("Enter the name of the file to save: ");    
                    String path = getString();
                    universe.save(SAVES_PATH+path);
                    break;
                }else if(choice==7){
                    //load a universe
                    String path2 = getValidSave();
                    universe.load(SAVES_PATH+path2);
                    universe.applyUniverse();
                    break; 
                } else if(choice==8){
                    universe.reset();
                    break;
                }else if(choice==9){
                    finish();
                } else if(choice==3) {
                        //list stars and choose one then attach the new plane to it
                        // do some maths to make sure they cannot be near the edge.
                        if(universe.getStars().size() > 0){
                            System.out.println("Enter a speed between 1 and 50: ");
                            int speed = getValidInt(1,50);
                            Color cPlanet = getValidColor();
                            System.out.println("Enter the diameter: ");
                            int diameterPlanet = getInt();
                            System.out.println("Choose a star to orbit: ");
                            for(int j = 0; j < universe.getStars().size(); j++){
                                System.out.println((j+1)+") Star at : ("+universe.getStars().get(j).getXPosition()+","+universe.getStars().get(j).getYPosition()+")");
                            }
                            int starChoice = getValidInt(1,universe.getStars().size()) - 1;
                            universe.addPlanet(universe.getStars().get(starChoice),new Planet(0,0,speed,0,diameterPlanet,cPlanet,universe));
                            break;
                        } else {
                            System.out.println("Cannot add Planet, no Star to orbit.");
                            break;
                        }
                } else {
                    System.out.println("Enter x: ");
                    int x = getValidInt(0,universe.getLength());
                    System.out.println("Enter y: ");
                    int y = getValidInt(0,universe.getGround());
                    System.out.println("Enter the diameter: ");
                    int diameter = getValidInt(0,1000);
                    if(choice==1){
                        System.out.println("Enter x velocity: ");
                        int xVel = getInt();
                        System.out.println("Enter y velocity: ");
                        int yVel = getInt();
                        Color c = getValidColor();
                        System.out.println("Which comet type, 1, 2 or A default(3): ");
                        int type = getValidInt(1,3);
                        if(type==1){
                            universe.addComet(new CometOne(x,y,xVel,yVel,diameter,c,universe));
                        } else if(type==2){
                            universe.addComet(new CometTwo(x,y,xVel,yVel,diameter,c,universe));
                        } else if(type==3){
                            universe.addComet(new Comet(x,y,xVel,yVel,diameter,c,universe));
                        }
                        break;
                    } else if(choice==2){
                        universe.addStar(new Star(x,y,0,0,diameter,Color.YELLOW,universe));
                        break;
                    } else if(choice==4){
                        universe.addBlackHole(new BlackHole(x,y,0,0,diameter,Color.BLACK,universe));
                        break;
                    }
                } 
            } catch (CancellationException c){
                break mainLoop;
            }
        }
    }
    
    /**
     * A Custom Exception class for breaking out of the input loop.
     */
    public class CancellationException extends Exception{ 
    }
    
    /**
     * Returns a valid Integer between a given min and max unless cancel is entered.
     */
    private int getValidInt(int min, int max) throws CancellationException{
        boolean got = false;
        int t = 0;
        while(!got){
            t = getInt();
            if(t >= min && t <= max){
                got = true;
            } else {
                System.out.println("Not a valid option!");
            }
        }
        return t;
    }
    
    /**
     * Returns a valid Color from the static array of valid colors unless cancel is entered.
     */
    private Color getValidColor() throws CancellationException{
        System.out.println("Enter a valid color from the list: ");
        System.out.println("1) Red.");
        System.out.println("2) Cyan.");
        System.out.println("3) Blue.");
        System.out.println("4) Gray.");
        System.out.println("5) Pink.");
        System.out.println("6) Green.");
        int colorInt = getValidInt(1,validColors.length) - 1;
        return validColors[colorInt];
    }
    
    
    /**
     * Returns a string unless cancel is entered.
     */
    private String getString() throws CancellationException{
        String temp = "DEFAULT.sav";
        while(scanner.hasNext()){
            temp = scanner.next();
            if(temp.equals("cancel")){
                throw new CancellationException();
            } else {
                break;
            }
        }
        return temp;
    }
    /**
     * Returns an int unless cancel is entered.
     */
    private int getInt() throws CancellationException{
        while(!scanner.hasNextInt()){
            String temp = scanner.next();
            if(temp.equals("cancel")){
                throw new CancellationException();
            }
            System.out.println("Enter an integer or type cancel!");
            System.out.print(">>> ");
        }
        return scanner.nextInt();
    }
    
    /**
     * Returns the name of a file entered, unless cancel is entered.
     */
    private String getValidSave() throws CancellationException{
        System.out.println("Choose a file to load: ");
        File folder = new File(SAVES_PATH);
        File[] files = folder.listFiles();
        for(int i=0; i < files.length; i++){
            System.out.println((i+1)+") "+files[i].getName());
        }
        int choice  = getValidInt(1,files.length) - 1;
        return files[choice].getName();
    }
    
    /**
     * Returns a Color[] of valid colors
     * @return A Color Array of valid colors
     */
    public static Color[] getValidColors(){
        return validColors;
    }
    
    /**
     * Prints the possible CLI Options.
     */
    private void printMenu(){
        System.out.println("1) Add new Comet.");
        System.out.println("2) Add new Star.");
        System.out.println("3) Add new Planet.");
        System.out.println("4) Add new BlackHole.");
        if(universe.isPaused()){
            System.out.println("5) Resume Simulation.");
        } else {
            System.out.println("5) Pause Simulation.");
        }
        
        System.out.println("6) Save current Universe.");
        System.out.println("7) Load a Universe.");
        System.out.println("8) Reset current universe.");
        System.out.println("9) Exit.");
        System.out.print(">>> ");
    }

}
