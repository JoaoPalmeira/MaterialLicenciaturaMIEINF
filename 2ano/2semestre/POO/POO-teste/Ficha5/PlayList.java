import java.util.*;
import java.util.stream.Collectors;
/**
 * Escreva a descrição da classe PlayList aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class PlayList extends Faixa
{
   private ArrayList<Faixa> faixas;
    
   public PlayList(){
       this.faixas = new ArrayList<Faixa>();
   }
    
   public PlayList(ArrayList<Faixa> faixas){
       this.faixas = new ArrayList<Faixa>();
       setFaixas(faixas);
    }

   public PlayList(PlayList faixas){
       this.faixas = faixas.getFaixas();
   }
    
   public ArrayList<Faixa> getFaixas(){      
       /*
       List<Faixa> res = new ArrayList<Faixa>();
       for(Faixa m: faixas){
           res.add(m.clone());
       }
       return res;
       */
       
       
       //funcional
       return this.faixas.stream()
            .map(Faixa::clone)
            .collect(Collectors.toCollection(ArrayList::new));
       
            
            /*stream cria um iterador por onde passam todos os objetos do ArrayList. map é um
            construtor que faz alguma coisa com cada objeto que passa, ficando com o 
            resultado de aplicar um método a um objeto, sendo que o metodo chamado pelo map 
            tem de devolver um objeto. apos o map, os objetos estao todos clonados e é feito
            um collect deles todos para os levar para algum lado.*/
   }
   
   public void setFaixas(ArrayList<Faixa> faixas){
       //1
       this.faixas.clear();
       for(Faixa f: faixas){
           this.faixas.add(f.clone());
       }
       
       
       //2 funcional
       /*
       this.faixas = faixas.stream()
                   .map(Faixa::clone)
                   .collect((Collectors.toCollection(ArrayList::new));
       */
       
       //0 - nap faz clone -  nao garante encapsulamento
       /*
       this.faixas = new ArrayList<Faixa>(faixas);
       */
           
            /*versão 0 é errada, pois não ha encapsulamento, visto que estes tipos nao fazem
            clones. Só com tipo como Strings é que nao seria precisa clones.*/
   }
   
   
   /* equals
   if (o==null || o.getClass()!=this.getClass()){
       return false;
   }
   PlayList p = (PlayList) o;
   return p.getFaixas().equals(faixas);
   */
   
   
   /* versao errada por ser identica ao equals do ArrayList
    if(faixas.size()!=p.getFaixas().size()){
        return false;
    }
    for(int i=0; i<faixas.size(); i++){
        if(!faixas.get(i).equals(p.getFaixas().get(i))){
            return false;
        }
    }
    return true;
    */
   
   public PlayList clone(){
       return new PlayList(this);
   }
   
   //1 - versao errada, pois revela desconhecimento da API
   public int numFaixasErrado(){
       int i=0;
       for(Faixa f : faixas){
           i++;
        }
        return i;
   }
    
   //1 - versao correta
   public int numFaixas(){
       return faixas.size();
   }
    
   //2
   public void addFaixa(Faixa f){
       faixas.add(f.clone());
   }
    
   //3
   public void removeFaixa(Faixa m){
       faixas.remove(m);
   }
   
   //4
   public void adicionar(List<Faixa> faixas){
      for(Faixa p: faixas){
          if(!this.faixas.contains(p)){
              this.faixas.add(p.clone());
          }
      }
   }
   
   //4 - versao funcional
   public void adicionarF(List<Faixa> faixas){
      faixas.forEach(f -> {
          this.faixas.add(f.clone());
      });
   }
   
   //5
   public int classificaoSuperior(Faixa f){
      int c =0;
      for(Faixa t: faixas){
          if(t.getClassificacao()>f.getClassificacao()){
              c++;
          }
      }
      return c;
   }
   
   //5 - versao funcional
   public int classificaoSuperiorF(Faixa f){
      return (int) faixas.stream()
         .filter(t -> t.getClassificacao() > f.getClassificacao())
         .count();
   }
   
   public boolean duracaoSuperior(double d){
      /*
       boolean r = false;
      for(Faixa t: faixas){
          if(t.getDuracao() > d) r = true;
      }
      return r;
      */
      
     boolean r = false;
     Iterator<Faixa> it = faixas.iterator();
     while(it.hasNext()&&!r){
          Faixa t = it.next();
          if(t.getDuracao()>d){
              r = true;
          }
     }
     return r;
   }
   
   public boolean duracaoSuperiorF(double d){
      return faixas.stream().anyMatch(f -> f.getDuracao() > d);
   }
   
   public List<Faixa> getCopiaFaixas(int n){
       List<Faixa> l = new ArrayList<Faixa>();
       for(Faixa f: this.faixas){
           l.add(new Faixa(f.getNome(), f.getAutor(), f.getDuracao(), n));
       }
       return l;
       
       /* alteraria o original logo nao queremos esta versao
       for(Faixa f: this.faixas){
           f.setClasificacao(n);
           l.add(f.clone());
       }
       */
   }
   
   public List<Faixa> getCopiaFaixasF(int n){
       return faixas.stream()
           .map(f -> new Faixa(f.getNome(), f.getAutor(), f.getDuracao(), n))
           .collect(Collectors.toList());
           
       /* peek permite ir a um objeto e altera-lo. map pega nos resultados de metodos e o set
       da void, logo com map em vez de peek, iria obter-se uma lista de voids. peek pega num
       objeto, altera-o e volta a po-lo na stream, obtendo-se a lista como pretendido
       return faixas.stream()
          .map(Faixa::clone)
          .peek(f -> f.setClassificacao(n))
          .collect(Collectors.toList());
       */
   }
   
   public double duracaoTotal(){
       double t=0;
       for(Faixa f: faixas){
           t+=f.getDuracao();
        }
        return t;
   }
   
   public double duracaoTotalF(){
       return faixas.stream().mapToDouble(Faixa::getDuracao).sum();
    }
    
    public void removeFaixas(String autor){
        Iterator<Faixa> it = faixas.iterator();
        while(it.hasNext()){
            Faixa f= it.next();
            if(f.getAutor().equals(autor)){
                it.remove();
            }
        }
    }
   
    public void removeFaixasF(String autor){
        this.faixas.removeIf(f -> f.getAutor().equals(autor));
    }
}
