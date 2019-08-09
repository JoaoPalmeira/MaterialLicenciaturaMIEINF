import java.io.FileOutputSream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ClassNotFoundException;
/**
 * Escreva a descrição da classe Persistencia aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Persistencia
{
    public static void guardar(HoteisInc h) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream("hoteis.fo");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(h);
        oos.close();
        fos.close();
    }
    
    public static HoteisInc carregar(){
      HoteisInc h = new HoteisInc();
      try{
        FileInputStream fis = new FileInputStream("hoteis.fo");
        ObjectInputStream ois = new ObjectInputStream(fis);
        h = (HoteisInc) ois.readObject();
        fis.close();
        ois.close();
      }catch(FileNotFoundException e){
        if(HoteisInc.DEVELOPMENT){ // apenas útil durante o desenvolvimento
            e.printStackTrace(); // apenas útil durante o desenvolvimento
        }
      }catch(IOException e){
      
      }catch (ClassNotFoundException e){
      
      }
      return h;
    }
}
