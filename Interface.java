
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
    public void update(){
        printMenu();
        while(scanner.hasNextLine()){
            int choice = getValidInt(1,10);
            if(choice==5){
                universe.resume();
                break;
            } else if(choice==6){
                universe.pause();
                break;
            } else if(choice==7){
               //save current universe
                String path = scanner.next();
                universe.save(SAVES_PATH+path);
                break;
            }else if(choice==8){
                //load a universe
                String path2 = getValidSave();
                universe.load(SAVES_PATH+path2);
                universe.applyUniverse();
                break; 
            } else if(choice==9){
                universe.reset();
            }else if(choice==10){
                finish();
            } else if(choice==3) {
                    //list stars and choose one then attach the new plane to it
                    // do some maths to make sure they cannot be near the edge.
                    if(universe.getStars().size() > 0){
                        Color cPlanet = getValidColor();
                        System.out.println("Enter the diameter: ");
                        int diameterPlanet = getInt();
                        for(int j = 0; j < universe.getStars().size(); j++){
                            System.out.println((j+1)+") Star at : ("+universe.getStars().get(j).getXPosition()+","+universe.getStars().get(j).getYPosition()+")");
                        }
                        int starChoice = getValidInt(1,universe.getStars().size()) - 1;
                        universe.addPlanet(universe.getStars().get(starChoice),new Planet(0,0,0,0,diameterPlanet,cPlanet,universe));
                        break;
                    } else {
                        System.out.println("Cannot add planet without a star to orbit.");
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
                    System.out.println("Which comet type, 1 or 2: ");
                    int type = getValidInt(1,2);
                    Color c = getValidColor();
                    if(type==1){
                        universe.addComet(new CometOne(x,y,xVel,yVel,diameter,c,universe));
                    } else {
                        universe.addComet(new CometTwo(x,y,xVel,yVel,diameter,c,universe));
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
        }
    }
    
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
    
    private Color getValidColor(){
        System.out.println("Enter a valid color from the list: ");
        for(int i = 0; i < validColors.length;i++){
            System.out.println((i+1)+") "+ validColors[i].toString());
        }
        int colorInt = getValidInt(1,validColors.length) - 1;
        return validColors[colorInt];
    }
    
    private int getInt(){
        while(!scanner.hasNextInt()){
            scanner.next();
            System.out.println("Enter an integer!");
        }
        return scanner.nextInt();
    }
    
    private String getValidSave(){
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
        System.out.println("5) Resume Simulation.");
        System.out.println("6) Pause Simulation.");
        System.out.println("7) Save current Universe.");
        System.out.println("8) Load a Universe.");
        System.out.println("9) Reset current universe.");
        System.out.println("10) Exit.");
    }

}
