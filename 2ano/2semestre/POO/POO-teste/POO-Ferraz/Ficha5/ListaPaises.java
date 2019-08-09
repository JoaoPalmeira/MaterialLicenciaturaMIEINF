import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
/**
 * Write a description of class ListaPaises here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaPaises
{
   private List<FichaPais> paises;
   
   public ListaPaises(){
       this.paises= new ArrayList<FichaPais>();
   }
    
   public ListaPaises(ArrayList<FichaPais> paises){
       this.setPaises(paises);
    }
    
   public ListaPaises(ListaPaises paises){
        this.paises=paises.getPaises();
   }
   
   public ArrayList<FichaPais> getPaises(){
       return this.paises.stream().map(FichaPais::clone).collect(Collectors.toCollection
       (ArrayList::new));
    }
       
       
       public void setPaises(ArrayList<FichaPais> paises){
       this.paises.clear();
       for(FichaPais fp : paises){
           this.paises.add(fp.clone());
        }
    }

    public boolean equals(Object o) {
        if(o==this) {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) {
            return false;
        }
        ListaPaises p = (ListaPaises) o;
        return paises.equals(p.getPaises());
    }
    
     public String toString(){
         StringBuilder sb  = new StringBuilder();  
        for(FichaPais i : paises){
         sb.append(i.getNome());
         }
         return sb.toString();
        }
       
    public ListaPaises clone() {
        return new ListaPaises(this);
    }
    
    public void adicionar(String nome, String continente,int populacao){
        FichaPais fp = new FichaPais(nome,continente,populacao);
        paises.add(fp.clone());
    
    }
    
    public int numPaises(){
        return paises.size();
    }
    
    public int numPaises(String continente){
        int r=0;
        for(FichaPais fp : paises){
            if(fp.getContinente().equals(continente)){
                r++;
            }
        }
        return r;
    }
    
    public int numPaisesF(String continente){
        return (int) paises.stream().filter(fp->fp.getContinente().equals(continente)).count();
    }
    
    public FichaPais getFicha(String nome){
        FichaPais p= new FichaPais();
        for(FichaPais fp : paises){
            if(fp.getNome().equals(nome)){
                p = fp.clone();
            
            }    
        }
    return p;
}
/*
 public FichaPais getFichaF(String nome){
     return this.paises.stream().m.collect(Collectors.toCollection
       (ArrayList::new));
    }
     
*/

    public List<String> nomesPaises(double valor){
        List<String> nomes= new ArrayList<String>();
        for(FichaPais fp : paises){
            if(fp.getPopulacao()>valor){
                nomes.add(fp.clone().getNome());
            }
        }
        return nomes;
    }

   /* public List<String> nomesPaisesF(double valor){
      return paises.stream().map(FichaPais::clone).filter(fp->fp.getPopulacao()>valor).map(f-> new String(f.getNome())).collect(Collectors.toList());
   }
   */
   public List<String> nomesPaisesF(double valor){
      return paises.stream().map(FichaPais::clone).filter(fp->fp.getPopulacao()>valor).map(f-> new String(f.getNome())).collect(Collectors.toCollection(ArrayList::new));
   }
   
   
    /*Retirar os elementos repetidos*/
    public List<String> nomesContinentes(double valor){
        List<String> nomes = new ArrayList<String>();
        int i=0;
        for(FichaPais fp :paises){
            if(fp.getPopulacao()>valor){
                nomes.add(i,fp.clone().getContinente());
                i++;
            }         
        }
        return nomes;
    }
    
    public List<String> nomesContinentesF(double valor){
        return paises.stream().map(FichaPais::clone).filter(fp->fp.getPopulacao()>valor).map(f-> new String(f.getContinente())).collect(Collectors.toList());
    }
    
    public double somatorio(String continente){
        double r=0;
        Iterator<FichaPais> it = paises.iterator();
        while(it.hasNext()){
            FichaPais fp= it.next();
            if(fp.getContinente().equals(continente)){
                r += (double) fp.getPopulacao();
            }
        }
        return r;  
    }
    public double somatorioF(String continente){
        return (double) paises.stream().filter(f->f.getContinente().equals(continente)).mapToDouble(FichaPais::getPopulacao).count();
        
    }
  
    public void actualiza(ArrayList<FichaPais> fichas){
        int i= paises.size();    
        for(FichaPais ff : fichas){
                for(FichaPais fp : paises){
                    if(fp.getNome().equals(ff.getNome())){
                        fp.setPopulacao(ff.getPopulacao());
                    }
                } 
        paises.add(ff.clone());
    }
}
    /*public void actualizaF(ArrayList<FichaPais> fichas){
           

    }*/
    public void remove(ArrayList<String> nomepaises){
        for(String nome : nomepaises){
            FichaPais fp = getFicha(nome);
            paises.remove(fp);
        }
    }
}
    
    
