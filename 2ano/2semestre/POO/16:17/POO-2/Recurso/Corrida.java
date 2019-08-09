import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
public class Corrida
{
    private ArrayList<TreeMap<String,Tempo>> temposPorVolta;
    
    public List<String> classificacaoNaVolta(int volta) throws CorridasException
    {
        if(this.temposPorVolta.isEmpty()) throw new CorridasException("Não houve corrida");
        ArrayList<String> res = new ArrayList<String>();
        for(int l = this.temposPorVolta.size();l>0;l--)
            for(Map.Entry<String,Tempo> t : this.temposPorVolta.get(l).entrySet())
                res.add(t.getKey());
        return res;
    }
    
    public Tempo tempoRecorde()
    {
        int tempo = this.temposPorVolta.get(0).get(this.temposPorVolta.get(0).firstKey()).getTempo();
        Tempo res = new Tempo();
        for(TreeMap<String,Tempo> m : this.temposPorVolta)
            for(Map.Entry<String,Tempo> t : m.entrySet())
                if(t.getValue().getTempo()<tempo) res=t.getValue()/*.clone()*/;
        return res;
    }
}