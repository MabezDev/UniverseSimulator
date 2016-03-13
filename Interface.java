
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
            int choice = scanner.nextInt();
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
                    System.out.println("enter x: ");
                    int x = scanner.nextInt();
                    System.out.println("enter y: ");
                    int y = scanner.nextInt();
                    System.out.println("Enter the diameter: ");
                    int diameter = scanner.nextInt();
                    System.out.println("Enter a valid color from the list: ");
                    for(int i = 0; i < validColors.length;i++){
                        System.out.println((i+1)+") "+ validColors[i].toString());
                    }
                    int colorInt = scanner.nextInt() - 1;
                    if(choice==1){
                        //comet - need vel
                        System.out.println("Enter x velocity: ");
                        int xVel = scanner.nextInt();
                        System.out.println("Enter y velocity: ");
                        int yVel = scanner.nextInt();
                        System.out.println("Which comet type, 1 or 2: ");
                        int type = scanner.nextInt();
                        if(type==1){
                            universe.addComet(new CometOne(x,y,xVel,yVel,diameter,validColors[colorInt],universe));
                        } else {
                            universe.addComet(new CometTwo(x,y,xVel,yVel,diameter,validColors[colorInt],universe));
                        }
                        break;
                    }
                } 
          }
        }
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
        System.out.println("7) Exit.");
    }

}
