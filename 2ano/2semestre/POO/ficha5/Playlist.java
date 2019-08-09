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
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
   private ArrayList<Faixa> faixas;
   
   //construtores
   public Playlist(){
       faixas = new ArrayList<Faixa>();
   }
   
   public Playlist(ArrayList<Faixa> faixas) {
       this.faixas = new ArrayList<Faixa>();
       setFaixas(faixas);
   }
   
   public Playlist(Playlist faixas){
       this.faixas = faixas.getFaixas();
   }
   
   public void setFaixas(ArrayList<Faixa> faixas) {
       this.faixas.clear();
       for(Faixa f : faixas) {
           this.faixas.add(f.clone());
       }
       
       //funcional
       //this.faixas = faixas.stream().map(Faixa::clone).collect(Collectors.toCollection(ArrayList::new));
   }
   
   public ArrayList<Faixa> getFaixas() {
       ArrayList<Faixa> res = new ArrayList<Faixa>();
       for(Faixa m : faixas) {
           res.add(m.clone());
       }
       return res;
       
       //funcional
       //return faixas.stream().map(Faixa::clone).collect(Collectors.toCollection(ArrayList::new));
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
       for(Faixa p : faixas) {
           if(this.faixas.contains(p)){
               this.faixas.add(p.clone());
           }
       }
   }
   
   public void adicionarF(List<Faixa> faixas){
       faixas.stream().forEach(f -> {
           this.faixas.add(f.clone());
       });
   }
   
   public int classificacaoSuperior(Faixa f){
       int c=0;
       for(Faixa t : faixas) {
           if(t.getClassificacao()>f.getClassificacao()){
               c++;
           }
       }
       return c;
   }
   
   public int classificacaoSuperiorF(Faixa f){
       return (int) faixas.stream().filter(t -> t.getClassificacao() > f.getClassificacao()).count();
   }
   
   public boolean duracaoSuperior(double d){
       boolean r = false;
       Iterator<Faixa> it = faixas.iterator();
       while(it.hasNext() && !r) {
           Faixa t = it.next();
           if(t.getDuracao()>d) {
               r = true;
           }
       }
       return r;
   }
   
   public boolean duracaoSuperiorF(double d){
       return faixas.stream().anyMatch(f -> f.getDuracao()>d);
   }
  
   public List<Faixa> getCopiaFaixas(int n){
       List<Faixa> l = new ArrayList<Faixa>();
       for(Faixa f : faixas) {
           l.add(new Faixa(f.getNome(), f.getAutor(), f.getDuracao(), n));
       }
       return l;
   }
   
   public List<Faixa> getCopiaFaixasF(int n){
       return faixas.stream().map(f -> new Faixa(f.getNome(), f.getAutor(), f.getDuracao(), n)).collect(Collectors.toList());
   }
   /*
   public double duracaoTotal(){
       
   }
   
   public double duracaoTotalF(){
       
   }
   
   public void removeFaixas(String autor){
       
   }
   
   public void removeFaixasF(String autor){
       
   }*/
   public Playlist clone() {
       return new Playlist(this);
   }
   public boolean equals(Object o){
       if (o==this) {
           return true;
        }
       if(o==null || o.getClass() != this.getClass()) {
            return false;
       }
       Playlist p = (Playlist) o;
       if(faixas.size()!=p.getFaixas().size()) {
           return false;
       }
       for(int i=0; i<faixas.size(); i++) {
           if(!faixas.get(i).equals(p.getFaixas().get(i))); {
               return false;
           }
       }
       return true;
   }
}
