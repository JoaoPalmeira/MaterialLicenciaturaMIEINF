import java.lang.String;
import java.lang.Boolean;
/**
 * Escreva a descrição da classe Moradia aqui.
 * 
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class Moradia extends Habitavel
{
    /**
     * variáveis de instância
     */ 
    private String tipo;
    private double areaImplantacao;
    private double areaTotal;
    private double areaEnvolvente;
    private int numQuartos;
    private int numWC;
    private int numPorta;
    
    /**
     * Construtor por omissão
     */ 
    public Moradia(){
        super();
        tipo = "";
        areaImplantacao = 0.0;
        areaTotal = 0.0;
        areaEnvolvente = 0.0;
        numQuartos = 0;
        numWC = 0;
        numPorta = 0;
    }
    
    /** 
     * Construtor a partir das partes
     */
    public Moradia(String cod, String tipoI, String ru, String est, double precoP, double precoM, int visualiz, String ma, String t, double areaImpl, double areaTot, double areaEnv, int nQuartos, int nWC, int nPorta){
        super(cod, tipoI, ru, est, precoP, precoM, visualiz, ma);
        tipo = t;
        areaImplantacao = areaImpl;
        areaTotal = areaTot;
        areaEnvolvente = areaEnv;
        numQuartos = nQuartos;
        numWC = nWC;
        numPorta = nPorta;
    }
    
    /**
     * Construtor de cópia
     */
    public Moradia(Moradia m){
        super(m);
        tipo = m.getTipo();
        areaImplantacao = m.getAreaImplantacao();
        areaTotal = m.getAreaTotal();
        areaEnvolvente = m.getAreaEnvolvente();
        numQuartos = m.getNumQuartos();
        numWC = m.getNumWC();
        numPorta = m.getNumPorta();
    }
    
    /**
     * Métodos de instância
     */
    public String getTipo(){
        return tipo;
    }
    
    public double getAreaImplantacao(){
        return areaImplantacao;
    }
    
    public double getAreaTotal(){
        return areaTotal;
    }
    
    public double getAreaEnvolvente(){
        return areaEnvolvente;
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
    
    public void setTipo(String t){
        tipo = t;
    }
    
    public void setAreaImplantacao(double areaI){
        areaImplantacao = areaI;
    }
    
    public void setAreaTotal(double areaT){
        areaTotal = areaT;
    }
    
    public void setAreaEnvolvente(double areaE){
        areaEnvolvente = areaE;
    }
    
    public void setNumQuartos(int nQ){
        numQuartos = nQ;
    }
    
    public void setNumWC(int nWC){
        numWC = nWC;
    }
    
    public void setNumPorta(int nP){
        numPorta = nP;
    }
    
    /**
     * Retorna uma cópia da instância
     */
    public Moradia clone(){
        return new Moradia(this);
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
        Moradia o = (Moradia) obj;
        return super.equals(o) && o.getTipo().equals(tipo) && o.getAreaImplantacao() == areaImplantacao &&
              o.getAreaTotal() == areaTotal && o.getAreaEnvolvente() == areaEnvolvente && 
              o.getNumQuartos() == numQuartos && o.getNumWC() == numWC && o.getNumPorta() == numPorta;
    }
    
    /**
     * Devolve sobre a forma de uma string toda a informação 
     * atual sobre as moradias
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("------------ MORADIA ------------\n");
        s.append(super.toString()).append("\n");
        s.append("Tipo: " + tipo + "\n");
        s.append("Área de Implantação: " + areaImplantacao + "\n");
        s.append("Área Total: " + areaTotal + "\n");
        s.append("Área Envolvente: " + areaEnvolvente + "\n");
        s.append("Número de Quartos: " + numQuartos + "\n");
        s.append("Número de WCs: " + numWC + "\n");
        s.append("Número de Porta: " + numPorta + "\n");
        return s.toString();
    }
}



