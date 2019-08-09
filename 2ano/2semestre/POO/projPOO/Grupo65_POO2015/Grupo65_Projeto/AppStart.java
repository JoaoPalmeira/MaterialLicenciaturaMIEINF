/** 
 * Classe que contém o módulo main para iniciar a aplicação.
 */
import java.lang.*;
import java.util.*;
import java.io.*;

public class AppStart
{   
    
    
    public static void main() {
        Imoobiliaria inicial = new Imoobiliaria();
        
        //mainTest();
        
        try {
            inicial = load();           
        } catch (FileNotFoundException e) {
            System.out.println("Erro. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erro. " + e.getMessage());
        }
        inicial.initApp();
        try {
            save(inicial);
        } catch (FileNotFoundException e) {
            System.out.println("Erro1. " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro2. " + e.getMessage());
        }
        
        
    }
    /**
     * Salvaguarda do estado da aplicação.
     */
    public static void save(Imoobiliaria imo) throws FileNotFoundException, IOException {
        FileOutputStream imoWriter = new FileOutputStream("Saves/Imoob");
        ObjectOutputStream imoOOS = new ObjectOutputStream(imoWriter);
      
        imoOOS.writeObject(imo);
        imoOOS.close();
    }
    /**
     * Carrega o ficheiro de imobiliária com a informação contida previamente.
     */
    public static Imoobiliaria load() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream imoPrinter = new FileInputStream("Saves/Imoob");
        ObjectInputStream imoOOS = new ObjectInputStream(imoPrinter);
        
        Imoobiliaria aux = (Imoobiliaria) imoOOS.readObject();
        
        imoOOS.close();
        
        return aux; 
    }
    public void fechaSessao() {
        
    }
}
