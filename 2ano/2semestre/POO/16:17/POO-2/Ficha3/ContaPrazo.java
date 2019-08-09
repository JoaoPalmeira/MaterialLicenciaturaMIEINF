
import java.util.Calendar;
import java.util.GregorianCalendar;
public class ContaPrazo
{
    private String codigo;
    private String titular;
    private GregorianCalendar inicio;
    private GregorianCalendar prazo;
    private double capital;
    private double taxa;
    
    public ContaPrazo(String codigo, String titular, GregorianCalendar inicio, GregorianCalendar prazo, double capital, double taxa) 
    {
        this.codigo = codigo;
        this.titular = titular;
        this.inicio = inicio;
        this.prazo = prazo;
        this.capital = capital;
        this.taxa = taxa;
    }
    
    public ContaPrazo() 
    {
        GregorianCalendar agora = new GregorianCalendar();
        GregorianCalendar prazo = new GregorianCalendar();
        prazo.add(Calendar.YEAR, 1);
        new ContaPrazo("", "", agora, prazo, 0, 5);
    }
    
    public ContaPrazo(ContaPrazo c2)
    {
        this.codigo = c2.getCodigo();
        this.titular = c2.getTitular();
        this.inicio = c2.getInicio();
        this.prazo = c2.getPrazo();
        this.capital = c2.getCapital();
        this.taxa = c2.getTaxa();
    }
    
    public String getCodigo() 
    {
        return this.codigo;
    }
    
    public String getTitular() 
    {
        return this.titular;
    }
    
    public GregorianCalendar getInicio() 
    {
        return this.inicio;
    }
        
    public GregorianCalendar getPrazo() 
    {
        return this.prazo;
    }
    
    public double getCapital() 
    {
        return this.capital;
    }
    
    public double getTaxa() 
    {
        return this.taxa;
    }
    
     public int diasPassados() 
     {
        GregorianCalendar agora = new GregorianCalendar();
        long difMilis = agora.getTimeInMillis() - this.inicio.getTimeInMillis();
        long difDias = difMilis / 1000 / 60 / 60 / 24;
        return (int)difDias;
    }
    
    public void alterarTitular(String nome) 
    {
        this.titular = nome;
    }
    
    public void alterarTaxa(double taxa) 
    {
        this.taxa = taxa;
    }
    
    public void actualizarJuros(GregorianCalendar novaData)
    {
         this.capital *= (1 + this.taxa);
         this.inicio = new GregorianCalendar();
          this.prazo = novaData;
    }
    
    public boolean verificarDiaJuros() 
    {
        GregorianCalendar agora = new GregorianCalendar();
        return (this.prazo.get(Calendar.DAY_OF_YEAR) ==agora.get(Calendar.DAY_OF_YEAR) && this.prazo.get(Calendar.YEAR) == agora.get(Calendar.YEAR));
    }
    
     public double fecharConta() 
     {
        double valorPagar = this.capital;
        this.capital = 0;
        return valorPagar;
    }
}
