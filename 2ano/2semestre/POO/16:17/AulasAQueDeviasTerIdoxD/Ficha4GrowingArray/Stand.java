import java.util.*;
import java.util.Arrays;

/**
 * Classe que representa um Stand de automóveis
 * 
 * @author anr
 * @version 2014.03.16  
 * @version 2017.03.03
 */

public class Stand {
  //variáveis de instância
  private String nomeStand;
  private GrowingArray carros;
  
  //construtores
  public Stand() {
    this.nomeStand = new String();
    this.carros = new Veiculo[capacidade_inicial];
    this.capacidade = capacidade_inicial;
    this.nveiculos = 0;
  }
  
  public Stand(String nome, int capacidade) {
    this.nomeStand = nome;
    this.carros = new Veiculo[capacidade];
    this.capacidade = capacidade;
    this.nveiculos = 0;
  }
  
  public Stand(Stand umStand) {
    this.nomeStand  = umStand.getNomeStand();
    this.carros     = umStand.getCarros();
    this.capacidade = umStand.getCapacidade();
    this.nveiculos  = umStand.getNVeiculos();
  }
  
  //métodos de instância
  
  //gets e sets: fazer!
  
  public Veiculo[] getCarros(){
      Veiculo[] res = new Veiculo[this.capacidade];
      
      for(int i=0; i<this.nveiculos; i++)
             res[i] = (this.carros[i]).clone();
      
      return res;
  }
  
  public String getNomeStand(){
      return this.nomeStand;    
  }
  
  public int getCapacidade(){
      return this.capacidade;    
  }
  
  public int getNVeiculos(){
      return this.nveiculos;    
  }
  
  public void setCarros(Veiculo[] cars){
      for(int j=0; cars[j]!=null; j++)
      this.nveiculos = j;
      for(int i=0; i<this.nveiculos; i++)
             this.carros[i] = cars[i].clone();
  }
  
  public void setNomeStand(String nStand){
      this.nomeStand = nStand;    
  }
  
  public void setCapacidade(int cap){
      this.capacidade = cap;    
  }
  
  public void setNVeiculos(int num){
      this.nveiculos = num;
  }
  
  //outros métodos
  
  /**
   * Método que insere um veículo no stand
   * 
   */
   public void insereVeiculo(Veiculo v) {
      this.carros.add(v.clone());
   }
   
   public Veiculo getVeiculo(int posicao) {
      return this.carros.get(posicao).clone();
   }
   
   /**
    * Método que verifica se um determinado veículo está no
    * stand.
    */
    public boolean existeVeiculo(Veiculo v) {
        boolean r = false; 
        for(int i=0; i<nveiculos; i++){
           if(this.carros[i].equals(v)) r=true;
        }
        return r;
    }
    
    
    /**
     * Método que verifica se um veículo, cuja matrícula é conhecida, 
     * está no stand.
     */
    public boolean existeVeiculoPorMatricula(String matricula) {
       boolean r = false;
       for(int i=0; i<nveiculos; i++){
          if(this.carros[i].getMatricula().equals(matricula)) r= true;
       }
       return r;    
    }
    
    
    /** 
     * Método que devolve o veículo com mais kms.
     * 
     */
    public Veiculo veiculoComMaisKms() {
        Veiculo v = new Veiculo();
        v = this.carros[0].clone();
        for(int i=0; i<nveiculos; i++){
            if(this.carros[i].getKmsTotal()>v.getKmsTotal()) v = this.carros[i].clone();
        }
        return v;
    }
    
    /**
     * Método que devolve o veículo mais gastador (em termos de combustível).
     *
     */
    public Veiculo veiculoMaisGastador() {
        Veiculo v = new Veiculo();
        v = this.carros[0].clone();
        for(int i=0; i<nveiculos; i++){
            if(this.carros[i].getConsumoMedio()>v.getConsumoMedio()) v = this.carros[i].clone();
        }
        return v;
    }
    
    /**
     * Método que determina o número de kms de todos os veículos
     * da garagem.
     * 
     */
    public double totalKmsTodosVeiculos() {
        double total = 0;
        for(int i=0; i<nveiculos; i++){
           total = total + this.carros[i].getKmsTotal();
        }
        return total;
    }
    
    
    /**
     * equals
     * 
     */
    public boolean equals(Object o) {
        if(this == o){return true;}
        if(o == null || o.getClass() != this.getClass()){return false;}
        Stand s = (Stand) o;
        return s.getNomeStand().equals(nomeStand) && Arrays.equals(s.getCarros(), carros) &&
        s.getNVeiculos() == nveiculos && s.getCapacidade() == capacidade;
    }
    
    /**
     * toString
     */

    public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("Nome do Stand: ");
      sb.append(nomeStand+"\n");
      sb.append("Carros: ");
      for (int i=0; i<nveiculos; i++){
          sb.append(carros[i].toString());
      }
      sb.append("Número de veículos no Stand: ");
      sb.append(nveiculos+"\n");
      sb.append("Capacidade do Stand: ");
      sb.append(capacidade+"\n");
      return sb.toString();
    }
    
    /**,
     * clone
     */
    public Stand clone() {
       return new Stand(this);
    }
    
}
