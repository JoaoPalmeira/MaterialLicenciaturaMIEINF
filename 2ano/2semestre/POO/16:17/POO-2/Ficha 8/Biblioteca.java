import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.*;
public class Biblioteca
{
    private Map<String,Entrada> entradas;
    
    public void addEntrada(Entrada e)
    {
        this.entradas.put(e.getId(),e.clone());
    }
    
    public Entrada getEntrada(String idEntrada)
    {
        return this.entradas.get(idEntrada);
    }
    
    public Map<String,Entrada> getEntradas(String ator)
    {
        HashMap<String,Entrada> res = new HashMap<String,Entrada>();
        for(Map.Entry<String,Entrada> e : this.entradas.entrySet())
        {
            if(e instanceof Filme)
            {
                Filme f = new Filme((Filme)e);
                if(f.getAtores().contains(ator))
                {
                    res.put(f.getId(),f.clone());
                }
            }
        }
        return res;
    }
    
    public void gravaObj(String fich) throws IOException 
    {
       ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));
       oos.writeObject(this);    
       oos.flush();
       oos.close();
    }
   
    public static Biblioteca leObj(String fich) throws IOException, ClassNotFoundException
    {
       ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));    
       Biblioteca b = (Biblioteca) ois.readObject();
       ois.close();
       return b;
    }
}
