import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Escreva a descrição da classe Carrinhas aqui.
 * 
 * @sofia carvalho 
 * @version 1
 */
public class Carrinha extends Viatura implements ListaEspera, Serializable
{
    // variáveis de instância
    private int capacidade;
    private int ocupacao;
    private List<Viagem> viagensEmEspera;

    public Carrinha(){
        // inicializa variáveis de instância
        super();
        this.capacidade = 9;
        this.ocupacao = 0;
        this.viagensEmEspera = new ArrayList<>();
    }

    public Carrinha(Carrinha c){
        super(c);
        this.capacidade = c.getCapacidade();
        this.ocupacao = c.getOcupacao();
        this.viagensEmEspera = c.getViagensEmEspera();
    }
    
    public Carrinha(Map<Integer,Viagem> umasViagens, double velMedKm, 
                    double precoBaseKm, double fiabilidade, Espaco2D localizacao, String matricula, int capacidade, int ocupacao, List<Viagem> umasViagensEmEspera){
        super(umasViagens,velMedKm,precoBaseKm,fiabilidade,localizacao,matricula);
        this.capacidade = capacidade;
        this.ocupacao = ocupacao;
        this.viagensEmEspera = new ArrayList<>();
        for(Viagem v: umasViagensEmEspera)
            this.viagensEmEspera.add(v.clone());
    }
    
    public int getCapacidade(){
        return this.capacidade;
    }
    
    public int getOcupacao(){
        return this.ocupacao;
    }
    
    public List<Viagem> getViagensEmEspera(){
        List<Viagem> res = new ArrayList<>();
        for(Viagem v: this.viagensEmEspera)
            res.add(v.clone());
        return res;
    }
    
    public void setCapacidade(int capacidade){
        this.capacidade = capacidade;
    }
    
    public void setOcupacao(int ocupacao){
        this.ocupacao = ocupacao;
    }
    
    public void setViagensEmEspera(List<Viagem> umasViagensEmEspera){
        this.viagensEmEspera = new ArrayList<>();
        for(Viagem v: umasViagensEmEspera)
            this.viagensEmEspera.add(v.clone());
    }
    
    public void adicionarViagemEspera(Viagem v){
        viagensEmEspera.add(v);
    }
    
    public void removerViagemEspera(Viagem v){
        viagensEmEspera.remove(v);
    }
    
    public Carrinha clone(){
        return new Carrinha(this);
    }
    
    public boolean equals(Object o) {
       if(o == this) {
            return true;
       }
       if(o == null || o.getClass() != this.getClass()) {
            return false;
       }
       if(!super.equals(o)){
           return false;
       }
       else{
          Carrinha c = (Carrinha) o;
          return c.getCapacidade() == this.capacidade && c.getOcupacao() == this.ocupacao && 
                 c.getViagensEmEspera().equals(this.viagensEmEspera);
       }   
    }
   
    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append(super.toString());
       sb.append("Capacidade: ").append(capacidade).append("\n");
       sb.append("Ocupacao: ").append(ocupacao).append("\n");
       sb.append("Clientes em Espera: ");
       for(Viagem v: this.viagensEmEspera)
           sb.append(v.toString()).append("\n");
       return sb.toString();
    }        
}
