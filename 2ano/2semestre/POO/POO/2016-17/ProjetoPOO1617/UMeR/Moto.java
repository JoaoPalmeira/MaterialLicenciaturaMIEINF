import java.util.*;

/**
 * Escreva a descrição da classe Moto aqui.
 * 
 * @sofia carvalho 
 * @version 1
 */
public class Moto extends Viatura
{
    // variáveis de instância
    private int capacidade;
    private int ocupacao;

    /**
     * Construtor para objetos da classe Motos
     */
    
    //construtor vazio
    public Moto(){
        super();
        capacidade = 0;
        ocupacao = 0;
    }
    
    //construtor por parâmetros
    public Moto(Map<Integer,Viagem> viagens, double velMedKm, double precoBaseKm, double fiabilidade, Espaco2D localizacao, String matricula, int capacidade, int ocupacao){
        super(viagens, velMedKm, precoBaseKm, fiabilidade, localizacao, matricula);
        this.capacidade = capacidade;
        this.ocupacao = ocupacao;
    }
    
    //construtor por cópia
    public Moto(Moto m){
        super(m.getViagens(), m.getVelMedKm(), m.getPrecoBaseKm(), m.getFiabilidade(), m.getLocalizacao(), m.getMatricula());
        capacidade = m.getCapacidade();
        ocupacao = m.getOcupacao();
    }
    
    //getters e setters
    public int getCapacidade(){
        return capacidade;
    }
    
    public void setCapacidade(int capacidade){
        this.capacidade = capacidade;
    }
    
    public int getOcupacao(){
        return ocupacao;
    }
    
    public void setOcupacao(int ocupacao){
        this.ocupacao = ocupacao;
    }
    
    public Moto clone() {
        return new Moto(this);
    }
    
    public boolean equals(Object o){
        if (o == this) return true;
        if (o.getClass()!=this.getClass() || (o == null)) return false;
        if (!super.equals(o)) return false;
        else {
           Moto m = (Moto) o;
           return capacidade == m.getCapacidade() && ocupacao == m.getOcupacao();
        }
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\n");
        sb.append("Capacidade da Moto: " + capacidade + "\n");
        sb.append("Ocupação atual da Moto: " + ocupacao + "\n");
        return sb.toString();
    }
}
