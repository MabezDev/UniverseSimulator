import java.io.*;


public class SaveAndLoad
{
    
    private static ObjectOutputStream saveStream;
    private static ObjectInputStream loadStream;
    
    private SaveAndLoad()
    {
        
    }
    
    public static void saveUniverse(Universe uni,String path){
        try{
        saveStream = new ObjectOutputStream(new FileOutputStream(path));
        saveStream.writeObject(uni);
        saveStream.close();
       } catch(IOException e){
           e.printStackTrace();
       }
    }
    
    public static Universe loadUniverse(String path){
        Universe uni = null;
        try{
            loadStream = new ObjectInputStream(new FileInputStream(path));
            uni = (Universe) loadStream.readObject();
            loadStream.close();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e2){
            
        }
        return uni;
    }
    
}
