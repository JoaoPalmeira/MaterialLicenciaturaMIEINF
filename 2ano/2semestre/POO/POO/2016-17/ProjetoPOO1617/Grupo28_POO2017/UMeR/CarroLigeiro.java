
/**
 * Escreva a descrição da classe CarrosLigeiros aqui.
 * 
 * @sofia carvalho 
 * @version 1
 */
import java.util.*;
import java.lang.*;
import java.io.*;

public class CarroLigeiro extends Viatura implements ListaEspera, Serializable
{
    // variáveis de instância
    private int ocupacao;
    private int capacidade;
    private List<Viagem> viagensEmEspera;
    
    //construtor vazio
    public CarroLigeiro(){
        //inicializa variáveis de instância
        super();
        this.ocupacao=0;
        this.capacidade=0;
        this.viagensEmEspera = new ArrayList<>();
    }
    
    //construtor por cópia
    public CarroLigeiro(CarroLigeiro carro){
        super(carro);
        this.ocupacao = carro.getOcupacao();
        this.capacidade = carro.getCapacidade();
        this.viagensEmEspera = carro.getViagensEmEspera();
    }
    
    //construtor por parâmetros
    public CarroLigeiro(Map<Integer,Viagem> umasViagens, double velMedKm, 
                          double precoBaseKm, double fiabilidade, Espaco2D localizacao, String matricula, int ocupacao, int capacidade, List<Viagem> umasViagensEmEspera){
        super(umasViagens,velMedKm,precoBaseKm,fiabilidade,localizacao,matricula);
        this.ocupacao = ocupacao;
        this.capacidade = capacidade;
        this.viagensEmEspera = new ArrayList<>();
        for(Viagem v: umasViagensEmEspera)
            this.viagensEmEspera.add(v.clone());
    }

    //getters
    public int getOcupacao(){
        return this.ocupacao;
    }
    
    public int getCapacidade(){
        return this.capacidade;
    }
    
    public List<Viagem> getViagensEmEspera(){
        List<Viagem> viagens = new ArrayList<>();
        for (Viagem v : viagensEmEspera){
            viagens.add(v.clone());
        }
        return viagens;
    }
    
    //setters
    public void setOcupacao(int ocupacao){
        this.ocupacao = ocupacao;
    }
    
    public void setCapacidade(int capacidade){
        this.ocupacao = ocupacao;
    }
    
     public void setViagensEmEspera(List<Viagem> umasViagensEmEspera){
        this.viagensEmEspera = new ArrayList<>();
        for(Viagem v: umasViagensEmEspera)
            this.viagensEmEspera.add(v.clone());
    }
    
     public CarroLigeiro clone(){
       return new CarroLigeiro(this);
    }
    
    public void adicionarViagemEspera(Viagem v){
        viagensEmEspera.add(v);
    }
    
    public void removerViagemEspera(Viagem v){
        viagensEmEspera.remove(v);
    }
    
    public boolean equals(Object o) {
       if(o == this) {
            return true;
       }
       if(o == null || o.getClass() != this.getClass()) {
            return false;
       }
       if (!super.equals(o)) return false;
       else {
           CarroLigeiro v = (CarroLigeiro) o;
           return v.getOcupacao() == this.ocupacao && v.getCapacidade() == this.capacidade && 
                    v.getViagensEmEspera().equals(viagensEmEspera);
       }
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Capacidade do carro: ").append(capacidade).append("\n");
        sb.append("Ocupacao no momento: ").append(ocupacao).append("\n");
        sb.append("Clientes em Espera: ");
        for(Viagem v: viagensEmEspera)
           sb.append(v.toString()).append("\n");
        return sb.toString();
    }  
}