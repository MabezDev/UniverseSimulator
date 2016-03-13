import java.io.*;

/**
 * A static class used for reading a writing Objects into and from files.
 */
public class SaveAndLoad
{
    
    private static ObjectOutputStream saveStream;
    private static ObjectInputStream loadStream;
    
    private SaveAndLoad()
    {
        
    }
    /**
     * Static Function for writing the MySpaceObject.class to a file.
     * @param SpaceObject to write to file
     * @param String file name.
     */
    public static void saveFile(MySpaceObjects obj,String path){
        try{
        saveStream = new ObjectOutputStream(new FileOutputStream(path));
        saveStream.writeObject(obj);
        saveStream.close();
       } catch(IOException e){
           e.printStackTrace();
       }
    }
    
    /**
     * Loads an Object in from a file
     * @param String filename
     * @return Object from file.
     */
    public static MySpaceObjects loadSpaceObjects(String path){
        MySpaceObjects uni = null;
        try{
            loadStream = new ObjectInputStream(new FileInputStream(path));
            uni = (MySpaceObjects) loadStream.readObject();
            loadStream.close();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e2){
            
        }
        return uni;
    }
    
}
