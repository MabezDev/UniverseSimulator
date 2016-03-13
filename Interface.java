
import java.util.*;
import java.io.IOException;
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
    
    
    /**
     * Creates the CLI and initializes and controls the simulaation thread.
     */
    public Interface()
    {
        scanner = new Scanner(System.in);
        gt = new SimulationThread();
        simulator = new Thread(gt);
        universe = gt.getUniverse();
        simulator.start();
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
    // NEED TO VALIDATE ALL INPUTS! TODO:
    public void update(){
        printMenu();
        while(scanner.hasNextLine()){
            int choice = getValidInt(1,9);
            if(choice > 0 && choice <= 9){
                if(choice==5){
                    universe.resume();
                    break;
                } else if(choice==6){
                    universe.pause();
                    break;
                } else if(choice==7){
                    //save current universe
                    System.out.println("Enter the save path including the file extension: ");
                    String path = scanner.next();
                    universe.save(SAVES_PATH+path);
                    break;
                }else if(choice==8){
                    //load a universe
                    System.out.println("Enter the name of the file to load including the file extension: ");
                    String path2 = scanner.next();
                    universe.load(SAVES_PATH+path2);
                    universe.applyUniverse();
                    break;
                    
                } else if(choice== 9){
                    finish();
                } else {
                    System.out.println("Enter x: ");
                    int x = getInt();
                    System.out.println("Enter y: ");
                    int y = getInt();
                    System.out.println("Enter the diameter: ");
                    int diameter = getInt();
                    System.out.println("Enter a valid color from the list: ");
                    for(int i = 0; i < validColors.length;i++){
                        System.out.println((i+1)+") "+ validColors[i].toString());
                    }
                    int colorInt = getValidInt(1,validColors.length) - 1;
                    if(choice==1){
                        //comet - need vel
                        System.out.println("Enter x velocity: ");
                        int xVel = getInt();
                        System.out.println("Enter y velocity: ");
                        int yVel = getInt();
                        System.out.println("Which comet type, 1 or 2: ");
                        int type = getValidInt(1,2);
                        if(type==1){
                            universe.addComet(new CometOne(x,y,xVel,yVel,diameter,validColors[colorInt],universe));
                        } else {
                            universe.addComet(new CometTwo(x,y,xVel,yVel,diameter,validColors[colorInt],universe));
                        }
                        break;
                    }
                    /*
                     * ADD OTHER OBJECTS!
                     */
                } 
          } 
        }
    }
    
    
    // finish these
    private int getValidInt(int min, int max){
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
    
    private int getInt(){
        while(!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Enter an integer!");
        }
        return scanner.nextInt();
    }
    
    private String getValidPath(String directoryToLookIn){
        return null;
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
        System.out.println("5) Start/resume Simulation.");
        System.out.println("6) Pause Simulation.");
        System.out.println("7) Save current Universe.");
        System.out.println("8) Load a Universe.");
        System.out.println("9) Exit.");
    }

}
