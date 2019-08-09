import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
public class MsgMap 
{
    private TreeMap<String, ArrayList<Mensagem>> msgBox;
    
    public MsgMap(MsgMap m2)
    {
        this.msgBox=m2.getMsgBox();
    }
    
    public TreeMap<String, ArrayList<Mensagem>> getMsgBox()
    {
        TreeMap<String, ArrayList<Mensagem>> res = new  TreeMap<String, ArrayList<Mensagem>>();
        for(Map.Entry<String, ArrayList<Mensagem>> l : this.msgBox.entrySet())
            res.put(l.getKey(),l.getValue());
        return res;
    }
    
    public int tamanho()
    {
        int res=0;
        for(Map.Entry<String, ArrayList<Mensagem>> l : this.msgBox.entrySet())
            for(Mensagem m : l.getValue())
                res++;
        return res;
    }
    
    public int quantosDe(String remetente)
    {
        int res=0;
        for(Map.Entry<String, ArrayList<Mensagem>> l : this.msgBox.entrySet())
            for(Mensagem m : l.getValue())
                if(m.getRemetente().compareTo(remetente)==0) res++;
        return res;
    }
    
    public void antiSpam(String palavra)
    {
        for(Map.Entry<String, ArrayList<Mensagem>> l : this.msgBox.entrySet())
            for(Mensagem m : l.getValue())
                if(m.getAssunto().contains(palavra)) 
                    this.msgBox.get(l.getKey()).remove(m);
    }
    
    public Map<String,List<Mensagem>> msgDeRemetente()
    {
        Map<String, List<Mensagem>> res = null;
        for(Map.Entry<String, ArrayList<Mensagem>> l : this.msgBox.entrySet())
            for(Mensagem m : l.getValue())
                if(!res.containsKey(m.getRemetente()))
                    res.put(m.getRemetente(),new ArrayList<Mensagem>());
        for(Map.Entry<String, ArrayList<Mensagem>> l : this.msgBox.entrySet())
            for(Mensagem m : l.getValue())
                if(res.containsKey(m.getRemetente()))
                    res.get(m.getRemetente()).add(m/*.clone()*/);
        return res;
    }
}