import java.lang.String;
import java.lang.Boolean;

/**
 * Escreva a descrição da classe LojaHabitavel aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class LojaHabitavel extends Apartamento
{
    /**
     * variáveis de instância
     */ 
    private double area;
    private boolean WC;
    private String tipoNegocio;
    private int numPorta;
    
    /**
     * Construtor por omissão
     */
    public LojaHabitavel(){
        super();
        area = 0.0;
        WC = true;
        tipoNegocio = "";
        numPorta = 0;
    }
    
    /**
    * Construtor a partir das partes
    */
    public LojaHabitavel(String cod, String tipoI, String ru, String est, double precoP, double precoM, int visualiz, String ma, String tip, double ar, int nQuartos, int nWC, int nPorta, int and, boolean gara, double are, boolean wC, String tipoN, int numP){
        super(cod, tipoI, ru, est, precoP, precoM, visualiz, ma, tip, ar, nQuartos, nWC, nPorta, and, gara);
        area = are;
        WC = wC;
        tipoNegocio = tipoN;
        numPorta = numP;
    }
    
    /**
     * Construtor de cópia
     */
    public LojaHabitavel(LojaHabitavel lh){
        super(lh);
        area = lh.getArea();
        WC = lh.getWC();
        tipoNegocio = lh.getTipoNegocio();
        numPorta = lh.getNumPorta();
    }
    
    /**
     * Métodos de instância
     */
    public double getArea(){
        return area;
    }
    
    public boolean getWC(){
        return WC;
    }
    
    public String getTipoNegocio(){
        return tipoNegocio;
    }
    
    public int getNumPorta(){
        return numPorta;
    }
    
    public void setArea(double a){
        area = a;
    }
    
    public void setWC(boolean Wc){
        WC = Wc;
    }
    
    public void setTipoNegocio(String tNegocio){
        tipoNegocio = tNegocio;
    }
    
    public void setNumPorta(int nPorta){
        numPorta = nPorta;
    }
    
    /**
     * Retorna uma cópia da instância
     */
    public LojaHabitavel clone(){
        return new LojaHabitavel(this);
    }
    
    /**
     * Compara a igualdade com outro objeto
     */
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        LojaHabitavel o = (LojaHabitavel) obj;
        return super.equals(o) && o.getArea() == area && o.getWC() == WC && 
                   o.getTipoNegocio() == tipoNegocio && o.getNumPorta() == numPorta;                 
    }
    
    /**
     * Devolve sobre a forma de uma string toda a informação 
     * atual sobre as lojas habitáveis
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("---------------- LOJA HABITÁVEL ----------------\n");
        s.append(super.toString()).append("\n");
        s.append("Área: " + area + "\n");
        s.append("Possui WC: " + WC + "\n");
        s.append("Tipo de Negócio: " + tipoNegocio + "\n");
        s.append("Número de Porta: " + numPorta + "\n");
        return s.toString();
    }
}
