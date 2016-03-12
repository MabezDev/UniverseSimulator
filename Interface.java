
import java.util.*;
import java.io.IOException;

public class Interface
{
    private Universe universe;
    private Scanner scanner;
    private boolean done = false;
    /**
     * Constructor for objects of class Interface
     */
    public Interface(Universe uni)
    {
        this.universe = uni;
        scanner = new Scanner(System.in);
    }
    
    private void finish(){
        done = true;
        //universe.finish();
    }
    
    public void update(){
        printMenu();
        while(scanner.hasNextInt()){
            int choice = scanner.nextInt();
            if(choice > 0 && choice <= 5){
                if(choice==5){
                    this.finish();
                    break;
                }
                System.out.println("enter x: ");
                int x = scanner.nextInt();
                System.out.println("enter y: ");
                int y = scanner.nextInt();
                System.out.println("Enter the diameter: ");
                int diameter = scanner.nextInt();
                System.out.println("Enter a valid color from the list: ");
                for(int i = 0; i < universe.getValidColors().length;i++){
                    System.out.println((i+1)+") "+ universe.getValidColors()[i].toString());
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
                        universe.addComet(new CometOne(x,y,xVel,yVel,diameter,universe.getValidColors()[colorInt],universe));
                    } else {
                        universe.addComet(new CometTwo(x,y,xVel,yVel,diameter,universe.getValidColors()[colorInt],universe));
                    }
                    break;
                }
            } 
        }
    }
    
    public boolean isFinished(){
        return done;
    }
    
    private void printMenu(){
        System.out.println("1) add new Comet.");
        System.out.println("2) add new Star.");
        System.out.println("3) add new Planet.");
        System.out.println("4) add new BlackHole.");
        System.out.println("5) start Simulation.");
        //System.out.println("6) exit.");
    }

}
