import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;
public class ComunicacoesMoveis
{
    private HashMap<String,ArrayList<Comunicacao>> comms;
    
    public void adComunicacao(Comunicacao com) throws ComunicacaoExiste
    {
        if(this.comms.containsKey(com.getRemetente()) && this.comms.get(com.getRemetente()).contains(com))
            throw new ComunicacaoExiste("Comunicação já existe");
        else if(this.comms.containsKey(com.getRemetente()))
            this.comms.get(com.getRemetente()).add(com/*.clone()*/);
        else 
        {
            this.comms.put(com.getRemetente(),new ArrayList<Comunicacao>());
            this.comms.get(com.getRemetente()).add(com/*.clone()*/);
        }
    }
    
    public int factura(String numeroOriginador, GregorianCalendar inicio, GregorianCalendar fim) throws ClienteNãoExiste, DatasErradas
    {
        if(!this.comms.containsKey(numeroOriginador))
             throw new ClienteNãoExiste("Cliente nao existe");
        if(inicio.after(fim) || fim.before(inicio))
            throw new DatasErradas();
        int res=0;
        for(Map.Entry<String,ArrayList<Comunicacao>> l : this.comms.entrySet())
        {
            for(Comunicacao c : l.getValue())
            {
                if(c.getData().before(fim) && c.getData().after(inicio))
                {
                    if(c instanceof EMail) res+=((Mensagem)c).getTexto().length()/100;
                    if(c instanceof Telefonema) res+=((Telefonema)c).getDuração()*7;
                    if(c instanceof SMS) res+=((SMS)c).getTotalParts()*7;
                }
            }
        }
        return res;
    }
}