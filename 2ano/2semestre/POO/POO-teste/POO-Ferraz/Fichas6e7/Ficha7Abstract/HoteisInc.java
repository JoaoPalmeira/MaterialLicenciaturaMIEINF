import java.util.*;
import java.util.stream.Collectors;
import java.util.Iterator;
/**
 * Escreva a descrição da classe HoteisInc aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class HoteisInc
{
    private String nome;
    private Map<String,Hotel> hoteis;
    
    public HoteisInc(){
        this.nome = "";
        this.hoteis = new HashMap<>();
    }
    
    public HoteisInc(HoteisInc h){
        this.nome = h.getNome();
        this.hoteis = h.getHoteis(); 
    }
    
    public HoteisInc(String nome, Map<String,Hotel> unsHoteis){
       this.nome = nome;
       this.hoteis = new HashMap<>();
       for(Hotel h: unsHoteis.values())
            this.hoteis.put(h.getCodigo(),h.clone());
    }
    
    public Map<String,Hotel> getHoteis(){
        Map<String,Hotel> res = new HashMap<>();
        for(Hotel h: this.hoteis.values())
            res.put(h.getCodigo(),h.clone());
        return res;
        
        /* ou
         * this.hoteis.entrySet().forEach(l->{res.put(l.getKey(),l.getValue().clone()});
         */
    }
    
    public void setHoteis(Map<String,Hotel> unsHoteis){
        this.hoteis = new HashMap<>();
        for(Hotel h: unsHoteis.values())
            this.hoteis.put(h.getCodigo(),h.clone());
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public HoteisInc clone() {
        return new HoteisInc(this);
    }
    
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        HoteisInc o = (HoteisInc) obj;
        return o.getNome().equals(this.nome) && o.getHoteis().equals(this.hoteis);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.nome).append(" \n");
        sb.append("Hóteis: ").append(this.hoteis.toString()).append(" \n");
        return sb.toString();
    }
    
    public boolean existeHotel(String cod){
        //boolean res = false;
        //for(Hotel h: this.hoteis.values())
        //    if(h.getCodigo().equals(cod)) res = true; 
        //return res;
        
        return this.hoteis.containsKey(cod);
    }
    
    public int quantos(){
        return this.hoteis.size();
    }
    
    public int quantos(String loc){
        int c = 0;
        for(Hotel h: this.hoteis.values())
            if(h.getLocalidade().equals(loc))c++;
        return c;
    }
    
    public Hotel getHotel(String cod){
        return this.hoteis.get(cod);
    }
    
    public void adiciona(Hotel h){
        this.hoteis.put(h.getCodigo(),h.clone());
    }
    
    public List<Hotel> getListaHoteis(){
        List<Hotel> lista = new ArrayList<>();
        for(Hotel h: this.hoteis.values())
            lista.add(h);
        return lista;
    }
    
    public void adiciona(Set<Hotel> hs){
        
    }
    
    public List<CartaoPontos> daoPontos(){
        return this.hoteis.values().stream()
                   .filter(h->h instanceof CartaoPontos) //filtramos aqueles que implementam a interface CartaoPontos
                   .map(Hotel::clone) //neste ponto temos os hoteis que implementam a interface clonados
                   .map(h->(CartaoPontos) h)
                   .collect(Collectors.toList());
    }
    
    public List<CartaoPontos> daoPontosExeterno(){
        ArrayList<CartaoPontos> res = new ArrayList();
        for(Hotel h: this.hoteis.values()){
            if(h instanceof CartaoPontos){
                res.add((CartaoPontos)(h.clone()));
            }
        }
        return res;
    }
}
