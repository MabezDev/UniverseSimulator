
import java.util.*;
import java.io.IOException;
import java.awt.*;

public class Interface
{
    private Universe universe;
    private GameThread gt;
    private Scanner scanner;
    private boolean done = false;
    private Thread simulator;
    private static Color[] validColors = {Color.RED,Color.CYAN,Color.BLUE,Color.GRAY,Color.PINK,Color.GREEN};
    /**
     * Constructor for objects of class Interface
     */
    public Interface()
    {
        scanner = new Scanner(System.in);
        gt = new GameThread();
        simulator = new Thread(gt);
        universe = gt.getUniverse();
        while(!done){
            update();
        }
    }
    
    private void finish(){
        done = true;
        //universe.finish();
    }
    
    public void update(){
        printMenu();
        while(scanner.hasNextInt()){
            int choice = scanner.nextInt();
            if(choice > 0 && choice <= 7){
                if(choice==5){
                    simulator.start();
                    break;
                }
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
    
    public boolean isFinished(){
        return done;
    }
    
    public static Color[] getValidColors(){
        return validColors;
    }
    
    private void printMenu(){
        System.out.println("1) add new Comet.");
        System.out.println("2) add new Star.");
        System.out.println("3) add new Planet.");
        System.out.println("4) add new BlackHole.");
        System.out.println("5) start Simulation.");
        System.out.println("6) pause Simulation.");
        System.out.println("7) exit.");
    }

}
