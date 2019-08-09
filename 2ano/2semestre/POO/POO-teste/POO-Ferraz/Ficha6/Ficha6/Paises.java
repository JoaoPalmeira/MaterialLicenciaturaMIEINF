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
 * Write a description of class Paises here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paises
{

  private Map<String,FichaCapital> fichas;
  
  public Paises(){
      this.fichas= new HashMap<>();
  }
  
  public Paises(Map<String,FichaCapital> l){
      this.fichas=new HashMap<>();
      for(String p: l.keySet()){
          this.fichas.put(p,l.get(p).clone());
       }
  }
  
  public Paises(Paises p){
      this.fichas= p.getFichas();
  }
  
  public Map<String,FichaCapital> getFichas(){
      Map<String,FichaCapital> fc = new TreeMap<>();
       for(String p: fichas.keySet()){
          fc.put(p,fichas.get(p).clone());
       }
       return fc;
    }
   
  public Paises clone(){
      return new Paises(this);
  }
  
  public boolean equals(Object o) {
    if(o==this) {
      return true;
    }
    if(o==null || o.getClass() != this.getClass()) {
      return false;
    }
    Paises p = (Paises) o;
    return p.getFichas().equals(this.fichas);
    }
    
   public String toString(){
        StringBuilder sb  = new StringBuilder();
        for(String p: this.fichas.keySet()){
            sb.append(p);
            sb.append(fichas.get(p));
        }
        return sb.toString();
    }
    
  
  
  
    
    
  /*
  public Map<String,FichaCapital> getFichas(){
      Map<String,FichaCapital> fc = new TreeMap<>();
      for(FichaCapital fl : this.fichas.values()){
          fc.put(,fl.clone());
       }
      return fc;
    }
   */
    public void setFichas(Map<String,FichaCapital> p){
      this.fichas.clear();
      for(String f : p.keySet()){
          this.fichas.put(f,p.get(f).clone());
      }
  }
  
  public int nrPaisesS(){
      return fichas.size();
    }
  public int nrPaises(){
      int r=0;
      Iterator<Map.Entry<String,FichaCapital>> it = this.fichas.entrySet().iterator();
      while(it.hasNext()){
          Map.Entry<String,FichaCapital> fc= it.next();
          r++;
        }
        return r;
    }
    
  public List<String> populacaover(int populacao){
      List<String> l = new ArrayList<String>();
      for(String f: fichas.keySet()){
        if(fichas.get(f).getPopulacao()>populacao){
            l.add(f);
        }
    }
        return l;
    }
    
  /*public List<Set> populacaoverF(int populacao){
      return this.fichas.values().stream().filter(f->f.getPopulacao()>populacao)..collect(Collectors.toList());
    }
    */
  
   public FichaCapital fichacapital(String nome){
      return fichas.get(nome);
  
   }
   
  public void alterarpopulacaocapital(String nome, int p){
       fichas.get(nome).setPopulacao(p);
  }
    
  public void inseririnformacao(String nome,FichaCapital l){
        this.fichas.put(nome,l.clone());
    }
    
  public List<String> capitaisregistadas(){
        List<String> l= new ArrayList<String>();
        for(String f: fichas.keySet()){
            l.add(fichas.get(f).getNomec());
        }
        return l;
  }
    
  public int somatoriopopulacaoes(){
        int res=0;
        for(String f: fichas.keySet()){
            res+=(fichas.get(f).getPopulacao());
        }
        return res;
  }
  
  public void mapmap(Map<String,FichaCapital> p){
      for(String s : p.keySet()){
          if(fichas.containsKey(s)){
              fichas.put(s,p.get(s));
              //fichas.replace(s,p.get(s));
            }
        }
    }
      
  public void remove(List<String> paises){
     for(String s: paises){
          fichas.remove(s);
        }
    }
  
  
    
    
    
    
    
    
}
    
        
        
    
    
    
    
    