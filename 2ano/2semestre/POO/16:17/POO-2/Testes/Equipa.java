import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
public class Equipa 
{
    private String nome;
    private Map<String,Barco> barcos;
    
    public Map<String,Barco> getBarcos()
    {
       HashMap<String,Barco> aux = new HashMap<String,Barco>();
       for(Barco b: this.barcos.values())
           aux.put(b.getId(),b.clone());
       return aux;
    }
    
    public double totalEmProva(String idBarco)
    {
        double res=0.0;
        for(RegistoEtapa r: this.barcos.get(idBarco).getRegistos())
        {
            res+=r.getFim()-r.getInicio();
        }
        return res;
    }
    
    public double registoMaisLongo()
    {
        double res=0.0;
        for(Map.Entry<String,Barco> b : this.barcos.entrySet())
        {
            for(RegistoEtapa r: b.getValue().getRegistos())
            {
                if(r.getFim()-r.getInicio()>res) res=r.getFim()-r.getInicio();
            }
        }
        return res;
    }
    
    public TreeSet<Barco> getBarcosBySeguro()
    {
        TreeSet<Barco> barcosS = new TreeSet<Barco>(new SeguroComparator());
        for(Barco b: barcos.values())
        {
            if(b instanceof Seguro)
            {
                barcosS.add(b.clone());
            }
        }
        return barcosS;
    }
}