import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
/**
 * Write a description of class Parque here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Parque
{
   private String nome;
   private Map<String,Lugar> lugares;
   
   
   public Parque(){
       this.nome= new String();
       this.lugares= new HashMap<>();
   }
   
   public Parque(String nome, Map<String,Lugar> l){
        this.nome=nome;
        this.lugares= new HashMap<>();
        for(Lugar ll: l.values()){
            this.lugares.put(ll.getMatricula(),ll.clone());
        }
   }
   
   public Parque(Parque p){
       this.nome=p.getNome();
       this.lugares=p.getLugares();
   }
   
   public String getNome(){
       return this.nome;
    }
      public boolean equals(Object o) {
        if(o==this) {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) {
            return false;
        }
        Parque p = (Parque) o;
        return p.getNome().equals(this.nome) && p.getLugares().equals(this.lugares);
    }
    
     public String toString(){
         StringBuilder sb  = new StringBuilder();  
        for(Lugar l: this.lugares.values()){
         sb.append(l.getNome());
         }
         return sb.toString();
        }
    
    
   public Parque clone(){
       return new Parque(this);
   }
    
   public Map<String,Lugar> getLugares(){
       Map<String,Lugar> gl = new TreeMap<>();
       Iterator <Map.Entry<String,Lugar>> it = this.lugares.entrySet().iterator();
       while(it.hasNext()){
           Map.Entry<String,Lugar> l = it.next();
           gl.put(l.getKey(),l.getValue().clone());
        }
        return gl;
   }      
   public Set<String> getMatriculas(){
       return this.lugares.keySet();
   } 
   public void registaLugar(Lugar l){
        this.lugares.put(l.getMatricula(),l.clone());  
   }
   public void removeLugar(Lugar l){
        this.lugares.remove(l.getMatricula());
   } 
   public void alteraLugar(int t, String matricula){
        this.lugares.get(matricula).setMinutos(t);
   }
   public int totalmin(){
        int res=0;
        for(Lugar l : this.lugares.values()){
            res+=l.getMinutos();
        }
        return res;
   }
   public int totalminF(){
        return this.lugares.values().stream().mapToInt(Lugar::getMinutos).sum();
   }
    
    public boolean verificaLugarOtimizado(String matricula){
        return this.lugares.containsKey(matricula);
    }
    
    public boolean verificaLugar(String matricula){
        boolean res= false;
        for(Lugar l : this.lugares.values()){
            if(l.getMatricula().equals(matricula))
            res= true;
        }
        return res;
    }
    
    public List<String> matriculasMinutosF(int minutos){
      return this.lugares.values().stream().filter(l->l.getMinutos()>minutos).map(Lugar::getMatricula).collect(Collectors.toList());
    }
    
    public List<String> matriculasMinutos (int minutos){
        List<String> mm= new ArrayList<String>();
        for(Lugar l: this.lugares.values()){
            if(l.getMinutos()>minutos){
                mm.add(l.getMatricula());
            }
        }
        return mm;
    }
    
    public String InfoLugarIT(String matricula){
        String res= new String();
        Iterator <Map.Entry<String,Lugar>> it = this.lugares.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,Lugar> f = it.next();
            if(f.getKey().equals(matricula))
                res=f.toString();
            }
            return res;
        }
    
    
    
    public String InfoLugar(String matricula){
        String res= new String();
        for(Lugar l: this.lugares.values()){
            if(l.getMatricula().equals(matricula))
             res= l.toString();
            } 
        return res;
    }
        
        
     /**
     * Outros métodos (não previstos no enunciado).
     */
    
    /*
     * Dar uma listagem dos lugares por ordem de saída, ordenada crescentemente por minutos.
     * A ordenação deverá ser por minutos de estacionamento e, em caso de igualdade, pelo nome 
     * do proprietário.
     * 
     */   
        
     public TreeSet<Lugar> ordemMin(){
         TreeSet<Lugar> tl = new TreeSet<>(new ComparatorLugarTempo());
         for(Lugar l: this.lugares.values()){
             tl.add(l.clone());
            }
         return tl;
     }
     
       /*
     * Versão com iteradores internos do método anterior.
     */
    
    public TreeSet<Lugar> ordenacaoMinutosF() {
        return this.lugares.values().stream().map(Lugar::clone).
             collect(Collectors.toCollection(() -> new TreeSet<Lugar>(new ComparatorLugarTempo()))); 
    }
    
    
    
    /*
     * Indicar qual é a matrícula do primeiro lugar do parque a ficar vago.
     */
    
    public String matriculaPrimeiroLugarVago() {
        
        return (this.ordenacaoMinutosF()).first().getMatricula();
    }
}
     
     
    
    
    
    
    
    
    
  
     
