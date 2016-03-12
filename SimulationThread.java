
/**
 * Write a description of class GameThread here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SimulationThread implements Runnable
{
    private Universe universe;
    
    public SimulationThread()
    {
        universe = new Universe();
    }
    
    
    @Override
    public void run(){
        universe.main();
    }
    
    public Universe getUniverse(){
        return universe;
    }
    
    

    
}
