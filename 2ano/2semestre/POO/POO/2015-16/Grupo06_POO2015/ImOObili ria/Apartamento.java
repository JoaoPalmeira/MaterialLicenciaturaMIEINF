import java.lang.String;
import java.lang.Boolean;
/**
 * Escreva a descrição da classe Apartamento aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Apartamento extends Habitavel
{
    // variáveis de instância
    private String tipo;
    private double area;
    private int numQuartos;
    private int numWC;
    private int numPorta;
    private int andar;
    private boolean garagem;
    
    // construtores
    public Apartamento (){
        super();
        tipo = "";
        area = 0.0;
        numQuartos = 0;
        numWC = 0;
        numPorta = 0;
        andar = 0;
        garagem = true;       
    }    
    
    public Apartamento (String cod, String tipoI, String ru, String est, double precoP, double precoM, int visualiz, String ma, String tip, double ar, int nQuartos, int nWC, int nPorta, int and, boolean gara){
        super(cod, tipoI, ru, est, precoP, precoM, visualiz, ma);
        tipo = tip;
        area = ar;
        numQuartos = nQuartos;
        numWC = nWC;
        numPorta = nPorta;
        andar = and;
        garagem = gara;
    }
    
    public Apartamento (Apartamento a2){
        super(a2);
        tipo = a2.getTipo();
        area = a2.getArea();
        numQuartos = a2.getNumQuartos();
        numWC = a2.getNumWC();
        numPorta = a2.getNumPorta();
        andar = a2.getAndar();
        garagem = a2.getGaragem();
    }
    
    // métodos de instância
    public String getTipo(){
        return tipo;
    }
    
    public double getArea(){
        return area;
    }
    
    public int getNumQuartos(){
        return numQuartos;
    }
    
    public int getNumWC(){
        return numWC;
    }
    
    public int getNumPorta(){
        return numPorta;
    }
    
    public int getAndar(){
        return andar;
    }
    
    public boolean getGaragem(){
        return garagem;
    }
    
    public void setTipo(String tip){
        tipo = tip;
    }
    
    public void setArea(double ar){
        area = ar;
    }
    
    public void setNumQuartos(int nQuartos){
        numQuartos = nQuartos;
    }
    
    public void setNumWC(int nWC){
        numWC = nWC;
    }
    
    public void setNumPorta(int nPorta){
        numPorta = nPorta;
    }
    
    public void setAndar(int and){
        andar = and;
    }
    
    public void setGaragem(boolean gara){
        garagem = gara;
    }
    
     /**
     * Retorna uma cópia da instância
     */
    public Apartamento clone(){
        return new Apartamento(this);
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
        Apartamento o = (Apartamento) obj;
        return super.equals(o) && o.getTipo().equals(tipo) && o.getArea() == area &&
               o.getNumQuartos() == numQuartos && o.getNumWC() == numWC && 
               o.getNumPorta() == numPorta && o.getAndar() == andar && o.getGaragem() == garagem;
    }
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("------------ APARTAMENTO ------------\n");
        s.append(super.toString()).append("\n");
        s.append("Tipo: " + tipo + "\n");
        s.append("Área: " + area + "\n");
        s.append("Número de Quartos: " + numQuartos + "\n");
        s.append("Número de WCs: " + numWC + "\n");
        s.append("Número da Porta: " + numPorta + "\n");
        s.append("Andar: " + andar + "\n");
        s.append("Possui Garagem: " + garagem + "\n");
        return s.toString();
    }
}
