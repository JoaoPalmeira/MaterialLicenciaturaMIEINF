
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
  private Veiculo[] carros;
  private int nveiculos;   //número veículos no stand
  
  //capacidade do stand (em número de veículos)
  private int capacidade;
  
  //capacidade inicial do Stand: valor por omissão
  public static final int capacidade_inicial = 10;
  
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
  public String getNomeStand(){
        return this.nomeStand;
    }
    
  public Veiculo[] getCarros(){
      return this.carros;
  }
  
  public int getNVeiculos(){
        return this.nveiculos;
    }
  
  public int getCapacidade(){
        return this.capacidade;
    }  
  
  public void setNomeStand(String s){
        this.nomeStand = s;
    }
    
  public void setCarros(Veiculo[] c){
        this.carros = c;
    }
    
  public void setNVeiculos(int n){
        this.nveiculos = n;
    }  
  
  public void setCapacidade(int cp){
        this.capacidade = cp;
    }
    
  //outros métodos
  
  /**
   * Método que insere um veículo no stand
   * 
   */
   public void insereVeiculo(Veiculo v) {
       if (nveiculos == capacidade) {}
       else {carros[nveiculos++]=v.clone();}
    }
   
   
   /**
    * Método que verifica se um determinado veículo está no
    * stand.
    */
    public boolean existeVeiculo(Veiculo v) {
        int i; boolean encontrar= false;
        for (i=0;i<nveiculos && !encontrar;i++){
        if (carros[i].equals(v)) encontrar=true;}
        return encontrar;
    }
    
    
    /**
     * Método que verifica se um veículo, cuja matrícula é conhecida, 
     * está no stand.
     */
    public boolean existeVeiculoPorMatricula(String matricula) {
        int i; boolean encontrar = false;
        for(i=0;i<nveiculos && !encontrar; i++) {
            Veiculo v = carros[i];
            if(v.getMat()==matricula) encontrar=true;
         }
         return encontrar;
    }
    
    
    /** 
     * Método que devolve o veículo com mais kms.
     * 
     */
    public Veiculo veiculoComMaisKms() {
        int i; double kms=0; int maior=0;
        for (i=0; i<nveiculos; i++){
            Veiculo veiculo = carros[i];
            if(veiculo.getKmsTotal()>kms){
            kms= veiculo.getKmsTotal();
            maior=i;
            }
        }
        Veiculo v = carros[maior];
        return v;
    }
    
    /**
     * Método que devolve o veículo mais gastador (em termos de
     * combustível).
     */
    public Veiculo veiculoMaisGastador() {
        int i; double consumo=0; int maior=0;
        for (i=0; i<nveiculos; i++){
        Veiculo veiculo = carros[i];
            if(veiculo.getConsumoMedio()>consumo){
                consumo =veiculo.getConsumoMedio();
                maior=i;
            }
        }
        Veiculo v= carros[maior];
        return v;
    }
    
    /**
     * Método que determina o número de kms de todos os veículos
     * da garagem.
     * 
     */
    public double totalKmsTodosVeiculos() {
        int i; double total=0;
        for (i=0; i<nveiculos; i++){
        Veiculo v= carros[i];
        total += v.getKmsTotal();
        }
        return total;
    }
    
    
    /**
     * equals
     * 
     */
    public boolean equals(Object o) {
        if (o==this) return true;
        if ((o==null) || (o.getClass() != this.getClass())) 
            return false;
        else { Stand v = (Stand) o;
            return this.nomeStand.equals(v.getNomeStand());
        }
    }
    
    /**
     * toString
     */

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append ("Stand");
        s.append (this.nomeStand);
        return s.toString();
    }
    
    /**
     * clone
     */
    public Stand clone() {
        return new Stand (this);
    }
    
}
