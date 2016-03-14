
/**
 * Thread handler for the Universe class. 
 */
public class SimulationThread implements Runnable
{
    private Universe universe;
    
    /**
     * Create a single Universe instance on a new Thread.
     */
    public SimulationThread()
    {
        universe = new Universe();
    }
    
    @Override
    public void run(){
        universe.main();
    }
    
    /**
     * @return Returns the current Universe instance.
     */
    public Universe getUniverse(){
        return universe;
    }
    
    

    
}
