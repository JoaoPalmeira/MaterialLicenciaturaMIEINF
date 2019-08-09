import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;
/**
 * Escreva a descrição da classe Playlist aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Playlist
{
   private ArrayList<Faixa> faixas; //declaração da lista de faixas a serem reproduzidas
   
   /**
    * Construtor vazio. Inicializa a playlist vazia.
    */
   public Playlist(){
       faixas = new ArrayList<Faixa>();
   }
   
   /**
    * Construtor por parâmetro. Cria uma playlist, dando uma lista de faixas. 
    */
   public Playlist (ArrayList<Faixa> faixas){
       this.faixas = new ArrayList<Faixa>();
       setFaixas(faixas);
   }
   
   public void setFaixas (ArrayList<Faixa> faixas){
       this.faixas.clear();
       for(Faixa f : faixas){
           this.faixas.add(f.clone());
       }
       
       //alternativa funcional
       /*
       this.faixas = faixas.stream()
                           .map(Faixa::clone)
                           .collect(Collectors.toCollection(ArrayList::new));
       */
       }
   
   /**
    * Construtor por cópia. Cria uma playlist a partir de outra instância de playlist.
    */
   public Playlist (Playlist faixas){
       this.faixas = faixas.getFaixas();
   }
   
   /**
    * Devolve uma cópia da lista de músicas.
    */
   public ArrayList<Faixa> getFaixas(){
      ArrayList<Faixa> res = new ArrayList<Faixa>();
      for(Faixa m : faixas){
           res.add(m.clone());
      }
      return res;
    
      //alternativa funcional
      /*
      return faixas.stream()
                   .map(Faixa::clone)
                   .collect(Collectors.toCollection(ArrayList::new));
      */
   }
   
   /**
    * Contagem do número de faixas na playlist.
    */
   public int numFaixas(){
      return faixas.size();     
   }
   
   /**
    * Adicionar uma faixa à playlist.
    */
   public void addFaixa(Faixa f){
       faixas.add(f.clone());
   }
   
   /**
    * Remover uma faixa da playlist.
    */
   public void removeFaixa(Faixa m){
       faixas.remove(m);
   }
   
   public void adicionar (List<Faixa> faixas){
       for (Faixa p : faixas){
           if (!this.faixas.contains(p)){
               this.faixas.add(p.clone());
           }
       }
   }
   
   public void adicionarF (List<Faixa> faixas){
       faixas.stream().forEach(f -> {
           this.faixas.add(f.clone());
       });
   }
   
   public int classificacaoSuperior (Faixa f){
       int c=0;
       for(Faixa t : faixas){
           if(t.getClassificacao()>f.getClassificacao()){
               c++;
           }
       }
       return c;
   }
   
   public int classificacaoSuperiorF (Faixa f){
       return (int) faixas.stream()
                          .filter(t -> t.getClassificacao() > f.getClassificacao())
                          .count();
   }
   
   public boolean equals (Object o){
       if(o==this){
           return true;
       }
       if(o==null || o.getClass() != this.getClass()){
           return false;
        }
       Playlist p = (Playlist) o;
       if (faixas.size()!=p.getFaixas().size()){
            return false;
       }
       for (int i=0; i<faixas.size(); i++){
            if(!faixas.get(i).equals(p.getFaixas().get(i))){
                return false;
            }
       }
       return true;
    }
   
   public Playlist clone(){
       return new Playlist (this);
   }
   
   public boolean duracaoSuperior (double d){
       boolean r = false;
       Iterator<Faixa> it = faixas.iterator();
       while (it.hasNext() && !r){
           Faixa t = it.next();
           if(t.getDuracao()>d){
               r = true;
           }
       }
       return r;
   }
   
   public boolean duracaoSuperiorF (double d){
       return faixas.stream().anyMatch(f -> f.getDuracao()>d);
   }
   
   
   public List<Faixa> getCopiaFaixas (int n){
       List<Faixa> l = new ArrayList<Faixa>();
       for(Faixa f : faixas){
           l.add(new Faixa(f.getNome(), f.getAutor(), f.getDuracao(), n));
       }
       return l;
   }
   
   public List<Faixa> getCopiaFaixasF (int n){
        return faixas.stream()
                     .map (f -> new Faixa(f.getNome(),f.getAutor(),f.getDuracao(),n))
                     .collect(Collectors.toList());
   }
   
   public String toString(){
        return "";
   }
   
   public double duracaoTotal(){
       double d = 0.0;
       return d;
   }
   
   public double duracaoTotalF(){
      double d = 0.0;
      return d;
   }
   
   
   public void removeFaixas (String autor){ 
       Iterator<Faixa> it = faixas.iterator();
       while (it.hasNext()){
           Faixa t = it.next();
           if(t.getAutor() == autor){
               faixas.remove(t);
           }
       }
    }
   
   public void removeFaixasF (String autor){
       return faixas.stream()
                    .filter (f -> f.getAutor() == autor);
                    
   }
}
   
  
