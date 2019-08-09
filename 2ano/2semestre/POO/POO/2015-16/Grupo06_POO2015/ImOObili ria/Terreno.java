import java.lang.String;
import java.lang.Boolean;
/**
 * Escreva a descrição da classe Terreno aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Terreno extends Imovel
{
    // variáveis de instância
    private String tipoConstrucao;
    private double diametroCanalizacoes;
    private double kWh;
    private boolean esgotos;
    
    // construtores
    public Terreno(){
        super();
        tipoConstrucao = "";
        diametroCanalizacoes = 0.0;
        kWh = 0.0;
        esgotos = true;
    }
    
    public Terreno (String cod, String tipoI, String ru, String est, double precoP, double precoM, int visualiz, String ma, String tipoC, double diametroCan, double kW, boolean esgot){
        super(cod, tipoI, ru, est, precoP, precoM, visualiz, ma);
        tipoConstrucao = tipoC;
        diametroCanalizacoes = diametroCan;
        kWh = kW;
        esgotos = esgot;
    }
    
    public Terreno (Terreno t2){
        super(t2);
        tipoConstrucao = t2.getTipoConstrucao();
        diametroCanalizacoes = t2.getDiametroCanalizacoes();
        kWh = t2.getKWh();
        esgotos = t2.getEsgotos();
    }
    
    // métodos de instância
    public String getTipoConstrucao(){
        return tipoConstrucao;
    }
    
    public double getDiametroCanalizacoes(){
        return diametroCanalizacoes;
    }
    
    public double getKWh(){
        return kWh;
    }
    
    public boolean getEsgotos(){
        return esgotos;
    }
    
    public void setTipoConstrucao(String tipoC){
        tipoConstrucao = tipoC;
    }
    
    public void setDiametroCanalizacoes(double diametroC){
        diametroCanalizacoes = diametroC;
    }
    
    public void setKWh(double kW){
        kWh = kW;
    }
    
    public void setEsgotos(boolean esgot){
        esgotos = esgot;
    }
    
     /**
     * Retorna uma cópia da instância
     */
    public Terreno clone(){
        return new Terreno(this);
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
        Terreno o = (Terreno) obj;
        return super.equals(o) && o.getTipoConstrucao().equals(tipoConstrucao) && 
               o.getDiametroCanalizacoes() == diametroCanalizacoes &&
               o.getKWh() == kWh && o.getEsgotos() == esgotos;
    }
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("------------ TERRENO ------------\n");
        s.append(super.toString()).append("\n");
        s.append("Tipo de Construção: " + tipoConstrucao + "\n");
        s.append("Diâmetro de Canalizações: " + diametroCanalizacoes + "\n");
        s.append("kWh Máximos Suportados: " + kWh + "\n");
        s.append("Possui Acesso aos Esgotos: " + esgotos + "\n");
        return s.toString();
    }
}
