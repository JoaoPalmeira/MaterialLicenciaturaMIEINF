import java.util.GregorianCalendar;
public class Comunicacao 
{
    private String remetente;
    private String destinatario;
    private GregorianCalendar data;
    
    public String getRemetente()
    {
        return this.remetente;
    }
    
    public GregorianCalendar getData()
    {
        return this.data;
    }
}