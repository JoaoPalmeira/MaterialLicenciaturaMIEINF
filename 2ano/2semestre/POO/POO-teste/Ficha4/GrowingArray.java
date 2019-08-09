
/**
 * Write a description of class GrowingArray here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GrowingArray
{
    private int size;
    private int nveiculos;
    private Veiculo[] elementos;
    
    private static final int capacidade_inicial = 10;
    
    //construtores
    public GrowingArray(){
        this.size=capacidade_inicial;
        this.nveiculos=0;
        this.elementos = new Veiculo[capacidade_inicial];
    }
    public GrowingArray(int capacidade){
        this.elementos= new Veiculo[capacidade];
        this.size=0;
    }
    public Veiculo[] getElementos(){
        int i;
        Veiculo[] aux = new Veiculo[this.size];
        for(i=0;i<this.size;i++){
            aux[i]=this.elementos[i];
        }
        return aux;
    }
    
    //Getters & Setters
    public int getSize(){
        return this.size;
    }
    public int getNveiculos(){
        return this.nveiculos;
    }
    public void setSize(int a){
        this.size=a;
    }
    public void setNveiculos(int a){
        this.nveiculos=a;
    }
    
    //Funções
    public Veiculo get(int indice){
        if(indice>this.size){
            return this.elementos[indice];
        }else{
            return null;
        }
    }
    public void add(Veiculo v){
        aumentaCapacidade(this.nveiculos+1);
        this.elementos[nveiculos]=v.clone();
        nveiculos++;
    }
    public void aumentaCapacidade(int capacidade){
        if(capacidade > 0.5 * this.nveiculos){
            int novaCapacidade = (int)(this.nveiculos * 1.5);
            this.elementos = Arrays.copyOf(this.elementos,novaCapacidade);
        }
    }
    public void add(int indice, Veiculo v){
        int N, i;
        for(i=N-1;i>=0 && i>pos;i--){
            this.elementos[N+1]=this.elementos[N];
        }
        this.elementos[N+1]=v.clone();
        this.nveiculos++;
    }
    
    
    //Equals, toString & clone
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
    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append("size"+this.size+"\n");
        sb.append("Lista de elementos: ");
        for( int i=0;i<this.size;i++)
          sb.append(this.elementos[i].toString()+"\n");
      
        return sb.toString();
    }
    public GrowingArray clone() {
        return new Growing(this);
    }
}
