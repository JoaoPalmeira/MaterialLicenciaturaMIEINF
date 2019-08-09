import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
public class CronometroDS
{
    private GregorianCalendar inicio;
    private double primeiraParagem;
    private double segundaParagem;
    
    public CronometroDS() 
    {
        this.inicio = new GregorianCalendar();
        this.primeiraParagem = -1;
        this.segundaParagem = -1;
    }
    
    public void parar() 
    {
        if (this.primeiraParagem == -1) this.primeiraParagem = (new GregorianCalendar()).getTimeInMillis() - this.inicio.getTimeInMillis();
        else if (this.segundaParagem == -1) this.segundaParagem = (new GregorianCalendar()).getTimeInMillis() - this.inicio.getTimeInMillis();
    }
    
    private String toFormat(double time) 
    {
        double minutos = (double)TimeUnit.MILLISECONDS.toMinutes((long)time);
        double segundos = (double)TimeUnit.MILLISECONDS.toSeconds((long)time) - (double)TimeUnit.MINUTES.toSeconds((long)minutos);
        double millis = time - (double)TimeUnit.MINUTES.toMillis((long)minutos) - (double)TimeUnit.SECONDS.toMillis((long)segundos);
        return (minutos + ":" + segundos + "'" + millis);
    }
    
    public String primeiraParagem() 
    {
        if (this.primeiraParagem != -1) return (toFormat(this.primeiraParagem));
        else return "Primeira paragem inexistente";
    }
    
    public String segundaParagem() 
    {
        if (this.segundaParagem != -1) return (toFormat(this.segundaParagem));
        else return "Segunda paragem inexistente";
    }
    
    public String tempoSplit() 
    {
        if (this.primeiraParagem != -1 && this.segundaParagem != -1) return (toFormat(this.segundaParagem - this.primeiraParagem));
        else return "Primeira ou segunda paragem inexistente";
    }
    
    private String toFormat2(long time) {
        long horas = TimeUnit.MILLISECONDS.toHours(time);
        long minutos = TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(horas);
        long segundos = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.HOURS.toSeconds(horas) - TimeUnit.MINUTES.toSeconds(minutos);
        long millis = time - TimeUnit.HOURS.toMillis(horas) - TimeUnit.MINUTES.toMillis(minutos) - TimeUnit.SECONDS.toMillis(segundos);
        return (horas + " Horas, " + minutos + " Minutos, " + segundos + " Segundos, " + millis + " Milissegundos.");
    }
    
    public String tempoAbsoluto() 
    {
        String res = "Inicio: " + toFormat2(this.inicio.getTimeInMillis());
        if (this.primeiraParagem != -1) res += "\nPrimeira paragem: " + toFormat2((long)(this.primeiraParagem + this.inicio.getTimeInMillis()));
        if (this.segundaParagem != -1) res += "\nSegunda paragem: " + toFormat2((long)(this.segundaParagem + this.inicio.getTimeInMillis()));
        System.out.println(res);
        return res;
    }
}
