
/**
 * Thread handler for the Universe class. 
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
    
    /**
     * @return Returns the current Universe instance.
     */
    public Universe getUniverse(){
        return universe;
    }
    
    

    
}
