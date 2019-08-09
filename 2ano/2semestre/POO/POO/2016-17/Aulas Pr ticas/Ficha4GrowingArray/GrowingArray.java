
/**
 * Escreva a descrição da classe GrowingArray aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class GrowingArray
{
    private int cap;
    private int nveiculos;
    private Veiculo[] veiculos;
    
    public static final int capacidade_inicial = 10;
    
    public GrowingArray(){
       this.cap = capacidade_inicial;
       this.nveiculos = 0;
       this.veiculos = new Veiculo[capacidade_inicial];
    }
    
    public GrowingArray(int capacidade, int nveics){
       this.cap = capacidade;
       this.nveiculos = nveics; 
       this.veiculos = new Veiculo[capacidade];
    }
    
    public GrowingArray(GrowingArray gArray){
       this.cap = gArray.getCap();
       this.nveiculos = gArray.getNveiculos(); 
       this.veiculos = gArray.getVeiculos();
    }
    
    public int getCap(){
      return this.cap;    
    }
    
    public int getNveiculos(){
      return this.nveiculos;
    }
    
    public Veiculo[] getVeiculos(){
      Veiculo[] res = new Veiculo[this.cap];
      
      for(int i=0; i<this.nveiculos; i++)
             res[i] = (this.veiculos[i]).clone();
      
      return res;
    }
    
      public void setCap(int capaci){
      this.cap = capaci;    
    }
  
    public void setNveiculos(int numV){
      this.nveiculos = numV;
    }
    
    public void setVeiculos(Veiculo[] veics){
      for(int j=0; veics[j]!=null; j++)
      this.nveiculos = j;
      aumentaCapacidade(nveiculos);
      for(int i=0; i<this.nveiculos; i++)
             this.veiculos[i] = veics[i].clone();
    }
    
    public GrowingArray(int capacidade){
       this.cap = capacidade;
       this.nveiculos = 0; 
       this.veiculos = new Veiculo[capacidade];
    }
    
    public Veiculo get(int indice){
      if(indice <= nveiculos)
         return this.veiculos[indice];
      else
         return null;
    }
    
    public void aumentaCapacidade(int capacidade){
        if(capacidade > 0.5 * this.nveiculos){
            int novaCapacidade = (int)(this.nveiculos * 1.5);
            this.veiculos = Arrays.copyOf(this.veiculos,novaCapacidade);
        }
    }
    
    public void add(Veiculo v) {
      aumentaCapacidade(this.nveiculos + 1);
      this.veiculos[nveiculos] = v.clone();
      nveiculos++;
    }
    
    public void add(int indice, Veiculo v){
      aumentaCapacidade(this.nveiculos + 1);
      System.arraycopy(veiculos,indice,veiculos,indice+1,nveiculos-indice);
      this.veiculos[indice] = v.clone();
      nveiculos++;
    }
    
    public void add(int indice, Veiculo v){
      this.veiculos[indice] = v.clone();
    }
    
    
} 

