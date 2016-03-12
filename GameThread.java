
/**
 * Write a description of class GameThread here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameThread implements Runnable
{
    private Universe universe;
    
    public GameThread()
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
