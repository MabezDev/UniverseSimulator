import java.io.*;


public class SaveAndLoad
{
    
    private static ObjectOutputStream saveStream;
    private static ObjectInputStream loadStream;
    
    private SaveAndLoad()
    {
        
    }
    
    public static void saveFile(MySpaceObjects obj,String path){
        try{
        saveStream = new ObjectOutputStream(new FileOutputStream(path));
        saveStream.writeObject(obj);
        saveStream.close();
       } catch(IOException e){
           e.printStackTrace();
       }
    }
    
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
