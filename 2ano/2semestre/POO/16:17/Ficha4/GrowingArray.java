import java.util.Arrays;
import java.util.*;
/**
 * Escreva a descrição da classe GrowingArray aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class GrowingArray
{
    private int size;
    private int nveiculos;
    private Veiculo[] elementos;
    
    private static final int capacidade_inicial = 10;
    
    
    public GrowingArray(){
       this.size = capacidade_inicial;
       this.nveiculos = 0;
       this.elementos = new Veiculo[capacidade_inicial];
    }
    public GrowingArray(int capacidade){
        this.elementos= new Veiculo[capacidade];
        this.size=0;
    }
    public Veiculo[] getElementos(){
        int i;
        Veiculo[] aux= new Veiculo[this.size];
        for(i=0;i<this.size;i++)
            aux[i]=(this.elementos[i]); 
        return aux;
    }
    public int getSize(){
        return this.size;
    }
    public int getNveiculos(){
      return this.nveiculos;
    }
    public void setSize(int tam){
      this.size = tam;    
    }
  
    public void setNveiculos(int numV){
      this.nveiculos = numV;
    }
    public Veiculo get(int indice){
        
        if(indice<=this.size)
            return this.elementos[indice];
        else 
            return null;
    }
    public Veiculo[] getVeiculos(){
      Veiculo[] res = new Veiculo[this.size];
      for(int i=0; i<this.nveiculos; i++)
             res[i] = (this.elementos[i]).clone();
      return res;
    }
    public void aumentaCapacidade(int capacidade){
        if(capacidade > 0.5 * this.nveiculos){
            int novaCapacidade = (int)(this.nveiculos * 1.5);
            this.elementos = Arrays.copyOf(this.elementos,novaCapacidade);
        }
    }
    public void add(Veiculo v) {
      aumentaCapacidade(this.nveiculos + 1);
      this.elementos[nveiculos] = v.clone();
      nveiculos++;
    }
    public void set(int indice, Veiculo v){
      aumentaCapacidade(this.nveiculos + 1);
      System.arraycopy(elementos,indice,elementos,indice+1,nveiculos-indice);
      this.elementos[indice] = v.clone();
      nveiculos++;
    }
    
    
    
    
         /**
     * equals
     * 
     */
    public boolean equals(GrowingArray o) {
        if (this==o)
            return true;
        if((o==null||(this.getClass()!=o.getClass())))
            return false;
        else{
            GrowingArray  s = (GrowingArray) o;
            return(this.size==getSize() && Arrays.equals(this.elementos,s.getElementos()));
        
        }
    }

    /**
     * toString
     */

    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append("size"+this.size+"\n");
        sb.append("Lista de elementos: ");
        for( int i=0;i<this.size;i++)
          sb.append(this.elementos[i].toString()+"\n");
      
         
        return sb.toString();
    }

    /**
     * clone
     */
    
    public GrowingArray clone() {
        return new GrowingArray();
    }
}
