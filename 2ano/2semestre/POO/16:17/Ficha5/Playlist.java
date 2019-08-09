import java.util.*;
import java.util.stream.Collectors;
/**
 * Escreva a descrição da classe Playlist aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Playlist extends Faixa
{
   private ArrayList<Faixa> faixas;
    
   /* construtores*/
   public Playlist(){
    this.faixas= new ArrayList<Faixa>();
   }
   public Playlist(ArrayList<Faixa> faixas){
       this.setFaixas(faixas);
   }
    public Playlist(Playlist faixas){
    this.faixas=faixas.getFaixas();
   }
   public void setFaixas(ArrayList<Faixa> faixas){
    this.faixas.clear();
    for(Faixa f : faixas){
        this.faixas.add(f.clone());
    }
    
    /*java 8
     * this.faixas= faixas.stream().map(Faixa::clone).collect(Collectors.toCollection(ArrayList::new)):
     */
   }
   public ArrayList<Faixa> getFaixas(){
    /* List<Faixa> res = new ArrayList<Faixa>();
        for(Faixa m : this.faixas) {
            res.add(m.clone());
        }
        return res;
        */
    /*java 8*/
    return this.faixas.stream().map(Faixa::clone).collect(Collectors.toCollection(ArrayList::new));
   }
   public int numFaixas(){
        return faixas.size();
    }
   public void addFaixa(Faixa f){
        faixas.add(f.clone());
   }
   public void removeFaixa(Faixa m){
        faixas.remove(m);
   }
   public void adicionar(List<Faixa> faixas){
        for(Faixa p : faixas){
            if(!this.faixas.contains(p)){
                this.faixas.add(p.clone());
            }
        }
   }
   public void adicionarF(List<Faixa> faixas){
        faixas.forEach(f->{this.faixas.add(f.clone());});
   }
   public int classificacaoSuperior(Faixa f){
        int c=0;
        for(Faixa t: this.faixas){
            if(t.getClassificacao()> f.getClassificacao()){
                c++;
            }
        }
        return c;
    }
   public int classificacaoSuperiorF(Faixa f){
        return (int) faixas.stream().filter(t-> t.getClassificacao()>f.getClassificacao()).count();
   }
   public boolean duracaoSuperior(double d){
        boolean c = false;
        Iterator<Faixa> it = faixas.iterator();
        while(it.hasNext() && !c){
            Faixa t= it.next();
            if(t.getDuracao()>d){
                c=true;
            }
        }
        /*percorre ate ao fim e nao é necessário
        for(Faixa t: faixas){
        if(t.getDuracao()> f.getDuracao()){
            c=true;
          }
        }*/
        return c;
   }
   public boolean duracaoSuperiorF(double d){
       return faixas.stream().anyMatch(f-> f.getDuracao()>d);
   }
   public List<Faixa> getCopiaFaixas(int n){
       List<Faixa> l = new ArrayList<Faixa>();
       for(Faixa f : this.faixas){
           l.add(new Faixa(f.getNome(),f.getAutor(),f.getDuracao(),n));
        }
        return l;
   }
   public List<Faixa> getCopiaFaixasF(int n){
       return faixas.stream()
              .map(f -> new Faixa(f.getNome(),f.getAutor(),f.getDuracao(),n))
              .collect(Collectors.toList());
              /* return faixas.stream().map(Faixa::clone).peek(f-> f.setClassificacao(n)).collect(Collectors.toList());
                 * 
                   */
   }
   public double duracaoTotal() {
        double t = 0;
        for(Faixa f : faixas) {
            t += f.getDuracao();
        }
        return t;
    }
   public double duracaoTotalF(){
    return faixas.stream().mapToDouble(Faixa::getDuracao).sum();
   }
   public void removeFaixas(String autor) {
        Iterator<Faixa> it = faixas.iterator();
        while(it.hasNext()) {
            Faixa f = it.next();
            if(f.getAutor().equals(autor)) {
                it.remove();
            }
        }
    }
   public void removeFaixasF(String autor) {
        this.faixas.removeIf(f-> f.getAutor().equals(autor));
    }
   

   public boolean equals(Object o) {
        if(o==this) {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) {
            return false;
        }
        Playlist p = (Playlist) o;
        return faixas.equals(p.getFaixas());
    
   }
   public String toString(){
         StringBuilder sb  = new StringBuilder();  
        for(Faixa i : faixas){
         sb.append(i.getNome());
         }
         return sb.toString();
   }   
   public Playlist clone() {
        return new Playlist(this);
   }
}
