
/**
 * Write a description of class ContaPrazo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 import java.util.GregorianCalendar;
public class ContaPrazo
{
    private String titular, codigo;
    private GregorianCalendar data, prazo;
    private double capital, taxa;
    private int n;
    
    /** Construtores usuais*/
    public ContaPrazo(String codigo, String titular, GregorianCalendar data, double capital, GregorianCalendar prazo, double taxa) { 
        this.codigo = codigo;
        this.titular = titular;
        this.data = data;
        this.capital = capital;
        this.prazo = prazo;
        this.taxa = taxa;
    }
    public ContaPrazo(ContaPrazo c) { 
        this.codigo = c.getCodigo();
        this.titular = c.getTitular();
        this.data = c.getData();
        this.capital = c.getCapital();
        this.prazo = c.getPrazo();
        this.taxa = c.getTaxa();
    }
    
        /** Métodos da instância */
    // GET
    public String getCodigo(){
        return this.codigo;
    }
    public String getTitular(){
        return this.titular;
    }
    public GregorianCalendar getData(){
        return this.data;
    }
    public double getCapital(){
        return this.capital;
    }
    public GregorianCalendar getPrazo(){
        return this.prazo;
    }
    public double getTaxa(){
        return this.taxa;
    }
     
    //SET
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setTitular(String titular){
        this.titular = titular;
    }
    public void setData(GregorianCalendar data){
        this.data = data;
    }
    public void setCapital(double capital){
        this.capital = capital;
    }
    public void setPrazo(GregorianCalendar prazo){
        this.prazo = prazo;
    }
    public void setTaxa(double taxa){
        this.taxa = taxa;
    }
     
    public String toString() {
        return new String("Código: " + this.codigo + "\nTitular: "+ this.titular + "\nData de inicio de contagem de juros: " + this.data + "\nCapital depositado: " + this.capital + "\nPrazo para cálculo de juros: " + this.prazo + "Taxa de juros: " + this.taxa);
    }
     
    public boolean equals(ContaPrazo c) {
        if (c != null)
            return ((this.codigo == c.getCodigo()) && (this.titular == c.getTitular()) && (this.data == c.getData())) && (this.capital == c.getCapital()) && (this.prazo == c.getPrazo()) && (this.taxa == c.getTaxa());
        else
            return false;
    }
     
    public ContaPrazo clone() {
        return new ContaPrazo(this);
    }
     
    /**
     * Calcula o número de dias passados desde a abertura da conta
     */
    public int diasPassados() {
        double msA = this.prazo.getTimeInMillis();
        double msD = this.data.getTimeInMillis();
        double ms = msA - msD;
        int dias = (int) (ms/1000/60/60/24);
        return dias;
    }
     
    /**
     * Altera o titular da conta
     */
    public void alterarTitular (String titular) {
        this.titular = titular;
    }
     
    /**
     * Altera a taxa de juros
     */
    public void alterarTaxa(double taxa) {
        this.taxa = taxa;
    }
     
    /**
     * Atingido o prazo para juros, calcula tais juros, junta-os ao capital, e regista a nova data de cálculo de juros
     */
    public void actualizarJuros(GregorianCalendar novaData) {
        capital += capital * taxa;
        n++;
        this.data = novaData;
    }
     
    /**
     * Verifica se hoje é o dia de calcular os juros
     */
    public boolean verificarDiaJuros() {
        return(data.getTimeInMillis() == data.getTimeInMillis());
    }
     
    /**
     * Fechar a conta calculando o valor total a pagar ao titular (capital inicial + juros)
     */
    public double fecharConta() {
            capital += capital * taxa;
        return capital;
    } 
}