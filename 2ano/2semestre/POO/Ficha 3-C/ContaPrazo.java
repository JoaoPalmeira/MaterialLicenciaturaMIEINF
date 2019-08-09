
/**
 * Write a description of class ContaPrazo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
public class ContaPrazo
{
    // instance variables
    private String codigo;
    private String titular;
    private GregorianCalendar inicio;
    private double capital;
    private GregorianCalendar prazo;
    private double taxa;
    
    // constructors
    public ContaPrazo() {
        GregorianCalendar now = new GregorianCalendar();
        GregorianCalendar prazo = new GregorianCalendar();
        prazo.add(Calendar.YEAR, 1);
        new ContaPrazo("a12345", "Nome", now, 0, prazo, 5);
    }
    
    public ContaPrazo(String codigo, String titular, GregorianCalendar inicio, double capital, GregorianCalendar prazo, double taxa) {
        this.codigo = codigo;
        this.titular = titular;
        this.inicio = inicio;
        this.capital = capital;
        this.prazo = prazo;
        this.taxa = taxa;
    }
    
    public ContaPrazo(ContaPrazo c2) {
        this.codigo = c2.getCodigo();
        this.titular = c2.getTitular();
        this.inicio = c2.getInicio();
        this.capital = c2.getCapital();
        this.prazo = c2.getPrazo();
        this.taxa = c2.getTaxa();
    }
    
    // getters & setters
    public String getCodigo() {
        return this.codigo;
    }
    
    public String getTitular() {
        return this.titular;
    }
    
    public GregorianCalendar getInicio() {
        return this.inicio;
    }
    
    public double getCapital() {
        return this.capital;
    }
    
    public GregorianCalendar getPrazo() {
        return this.prazo;
    }
    
    public double getTaxa() {
        return this.taxa;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public void setTitular(String titular) {
        this.titular = titular;
    }
    
    public void setInicio(GregorianCalendar inicio) {
        this.inicio = inicio;
    }
    
    public void setCapital(double capital) {
        this.capital = capital;
    }
    
    public void setPrazo(GregorianCalendar prazo) {
        this.prazo = prazo;
    }
    
    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
    
    // other methods
    public int diasPassados() {
        GregorianCalendar now = new GregorianCalendar();
        long difMilis = now.getTimeInMillis() - this.inicio.getTimeInMillis();
        long difDias = difMilis / 1000 / 60 / 60 / 24;
        return (int)difDias;
    }
    
    public void alterarTitular(String nome) {
        this.titular = nome;
    }
    
    public void alterarTaxa(double taxa) {
        this.taxa = taxa;
    }
    
    public void actualizarJuros(GregorianCalendar novaData) {
        this.capital *= (1 + this.taxa); // segundo os testes, a taxa nao é em percentagem
        this.inicio = new GregorianCalendar();
        this.prazo = novaData;
    }
    
    public boolean verificarDiaJuros() {
        GregorianCalendar now = new GregorianCalendar();
        return (this.prazo.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR) && this.prazo.get(Calendar.YEAR) == now.get(Calendar.YEAR));
    }
    
    public double fecharConta() {
        double pagar = this.capital;
        this.capital = 0;
        return pagar;
    }
}
