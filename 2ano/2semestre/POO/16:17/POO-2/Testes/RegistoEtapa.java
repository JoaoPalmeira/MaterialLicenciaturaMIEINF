import java.util.GregorianCalendar;
import java.util.Calendar;
public class RegistoEtapa
{
    private GregorianCalendar inicio;
    private GregorianCalendar fim;
    private String nome;
    private double milhas;
    
    public double getInicio()
    {
        return this.inicio.get(Calendar.MINUTE);
    }
    
    public double getFim()
    {
        return this.fim.get(Calendar.MINUTE);
    }
}
