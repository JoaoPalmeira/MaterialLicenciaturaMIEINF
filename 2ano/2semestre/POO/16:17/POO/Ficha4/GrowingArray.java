import java.util.Arrays;
/**
 * Write a description of class GrowingArray here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GrowingArray
{
    private int size;
    private Veiculo[] elementos;
    
    private static final int capacidade_inicial = 10;
    
    
    public GrowingArray(){
        this(capacidade_inicial);
    }
    
    public GrowingArray(int capacidade){
        this.elementos= new Veiculo[capacidade];
        this.size=0;
    }
    
    public int getSize(){
        return this.size;
    }
    public Veiculo[] getElementos(){
        int i;
        Veiculo[] aux= new Veiculo[this.size];
        for(i=0;i<this.size;i++)
            aux[i]=(this.elementos[i]).clone(); 
        return aux;
    }
    
    public Veiculo get(int indice){
        
        if(indice<=this.size)
            return this.elementos[indice];
        else 
            return null;
        }
        
    public void add(Veiculo v){
        int i;
        for(i=0;i<=this.size+1;i++);
        elementos[i]=v;
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
    
    

