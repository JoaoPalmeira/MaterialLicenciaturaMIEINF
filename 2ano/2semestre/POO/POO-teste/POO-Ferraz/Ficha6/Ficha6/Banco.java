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
 import java.util.GregorianCalendar;
/**
 * Write a description of class Banco here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Banco
{
  private Map<String,ContaPrazo> contas;
  
  public Banco(){
      this.contas= new HashMap<>();
    }
    
  public Banco(HashMap<String,ContaPrazo> c){
      this.contas= new HashMap<>();
      for(ContaPrazo cp : c.values()){
          this.contas.put(cp.getCodigo(),cp.clone());
        }
    }
 
  public Banco(Banco b){
      this.contas=b.getContas();
  }
  
  public Map<String,ContaPrazo> getContas(){
      Map<String,ContaPrazo> gc = new TreeMap<>();
      for(ContaPrazo cp: this.contas.values()){
          gc.put(cp.getCodigo(),cp.clone());
        }
        return gc;
    }
  
 
  public boolean equals(Object o) {
        if(o==this) {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) {
            return false;
        }
        Banco p = (Banco) o;
        return p.getContas().equals(this.contas);
  }
    
  public String toString(){
      StringBuilder sb  = new StringBuilder();  
      for(ContaPrazo cp: this.contas.values()){
          sb.append(cp.getCodigo());
      }
      return sb.toString();
      }
    
    
   public Banco clone(){
       return new Banco(this);
   }
   
   public void insereconta(ContaPrazo cp){
       this.contas.put(cp.getCodigo(),cp.clone());
    }
    
    public List<String> titularcodigos(String nome){
        List<String> cod = new ArrayList<>();
        Iterator<ContaPrazo> it = this.contas.values().iterator();
        while(it.hasNext()){
            ContaPrazo conta = it.next();
            if(conta.getTitular().equals(nome)){
                cod.add(conta.getCodigo());
            
            }
        }
        return cod;
    }
    
    public List<String> titularcodigosF(String nome){
        return this.contas.values().stream().filter(f->f.getTitular().equals(nome)).map(ContaPrazo::getCodigo).collect(Collectors.toList());
    }
   
   public List<String> codigosTitular(List<String> titulares){
       List<String> tit= new ArrayList<>();
       Iterator<String> it= titulares.iterator();
       Iterator<ContaPrazo> itt = this.contas.values().iterator(); 
       while(it.hasNext()){
           String titular = it.next();
           while(itt.hasNext()){
               ContaPrazo c =itt.next();
               if(c.getTitular().equals(titular)){
                   tit.add(c.getCodigo());
                }
            }
        }
        return tit;
    }
    
   /* public List<String> codigosTitular(List<String> titulares){
        return this.contas.values().stream().foreach{f->f.titulares.equals(contas.getTitular());}.map(ContaPrazo::getCodigo).collect(Collectors.toList());
    }
    */
   
   public List<String> codigosacima(double valor){
       List<String> cod= new ArrayList<>();
       for(ContaPrazo cp: this.contas.values()){
           if(cp.getCapital()>valor){
               cod.add(cp.getCodigo());
            }
        }
        return cod;
    }
    
       
   public Map<String,ContaPrazo> taxacimavalor(double tx){
        Map<String,ContaPrazo> res= new HashMap<>();
        Iterator<Map.Entry<String,ContaPrazo>> it = this.contas.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,ContaPrazo> c= it.next();
            if(c.getValue().getTaxa()>tx){
                res.put(c.getKey(),c.getValue().clone());
            }
        }
        return res;
   }
    
   public Map<String,ContaPrazo> taxacimavalor2(double tx){
        Map<String,ContaPrazo> res= new HashMap<>(); 
        Iterator<ContaPrazo> it = this.contas.values().iterator();
        while(it.hasNext()){
            ContaPrazo c= it.next();
            if(c.getTaxa()>tx){
                res.put(c.getCodigo(),c.clone());
            }
        }
        return res;
    }
    
    public List<String> codigosvencidos(){
        GregorianCalendar a = new GregorianCalendar();
        List<String> cv= new ArrayList<>();
        for(ContaPrazo cp : this.contas.values()){
            if(cp.getPrazo().equals(a)){
                cv.add(cp.getCodigo());
            }
        }
        return cv;
    }
    
    public List<String> codigosvencidosF(){
         GregorianCalendar a = new GregorianCalendar();
        return this.contas.values().stream().map(ContaPrazo::clone).filter(f->f.getPrazo().equals(a)).map(ContaPrazo::getCodigo).collect(Collectors.toList());
    }
    
    public void incrementacodigos(List<String> codigos, double x){
        for(ContaPrazo p: this.contas.values()){
            if(p.getCodigo().equals(codigos)){
                p.setTaxa(p.getTaxa()+x);
            }
        }
    }
    
     public void incrementacodigosq(List<String> codigos, double x){
         for(String a: this.contas.keySet()){
             if(codigos.contains(a)){
                 this.contas.get(a).setTaxa((contas.get(a).getTaxa())+x);
                }
            }
        }
        
        public List<String> titulares(){
            List<String> berto = new ArrayList<>();
            for(ContaPrazo cp : this.contas.values()){
                if(!berto.contains(cp.getTitular())){
                berto.add(cp.getTitular());
            }
            }
            return berto;
        }
       
         
       public List<String> titulares2(){
             return this.contas.values().stream().map(ContaPrazo::getTitular).collect(Collectors.toList());
       }
       
       public double capTotal(String nome){
           double cap=0;
           for(ContaPrazo cp: this.contas.values()){
               if(cp.getTitular().equals(nome));
               cap+=cp.getCapital();
            }
            return cap;
        }
        
        public Map<String,Double> captitular(){
            Map<String,Double> res= new HashMap<>();
            for(String s : this.titulares()){
                for(ContaPrazo cp : this.contas.values()){
                    if(cp.getTitular().equals(s)){
                        res.put(s,capTotal(s));
                    }
                }
            }
            return res;
        }
                     
        
  
       
      
}
