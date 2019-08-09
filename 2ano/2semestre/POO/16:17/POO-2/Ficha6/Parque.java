import java.util.*;
public class Parque
{
    private String nome;
    private Map<String,Lugar> lugares;
    
    public Parque()
    {
        this.lugares = new TreeMap<String,Lugar>();
    }
    
    public Parque (Map<String,Lugar> p)
    {
        this();
        for (Map.Entry<String,Lugar> l : p.entrySet())
        this.lugares.put(l.getKey(),l.getValue().clone());
    }
}
