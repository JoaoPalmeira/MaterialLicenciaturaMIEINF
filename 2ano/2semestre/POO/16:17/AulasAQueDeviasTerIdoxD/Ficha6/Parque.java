import java.util.*;
/**
 * Escreva a descrição da classe Parque aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Parque
{
    private String nome;
    private Map<String,Lugar> lugares;
    
    public Parque(){
        this.nome="";
        this.lugares = new HashMap<>();
    }
    
    public Parque(String nome, Map<String,Lugar> unsLugares){
        this.nome = nome;
        this.lugares = new HashMap<>();
        for(Lugar l: unsLugares.values())
            this.lugares.put(l.getMatricula(),l.clone());
    }
    
    public Parque(Parque p){
        this.nome = p.getNome();
        this.lugares = p.getLugares();
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public Map<String,Lugar> getLugares(){
        HashMap<String,Lugar> res = new HashMap<>();
        for(Lugar l: this.lugares.values())
            res.put(l.getMatricula(),l.clone());
        
        /* versão com iterador interno
         * res = this.lugares.values().stream().
         *       collect(Collectors.toMap(l->l.getMatricula(),l->l.clone()));
         */
        
        return res;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setLugares(Map<String,Lugar> unsLugares){
        this.lugares = new HashMap<>();
        for(Lugar l: unsLugares.values())
            this.lugares.put(l.getMatricula(),l.clone());
    }
    
    public Set<String> getMatriculas(){
        return this.lugares.keySet();
    }
    
    public void registaLugar(Lugar lugar){
        this.lugares.put(lugar.getMatricula(),lugar.clone());
    }
    
    public void removeLugar(Lugar lugar){
        this.lugares.remove(lugar);
    }
    
    public void alteraTempo(String matricula, int minutos){
        /* versão 1 (pior)
         * Lugar x = this.lugares.get(matricula);
         * x.setMinutos(minutos);
         * this.lugares.put(matricula,x);
         */
        
        //versão 2 (melhor)
        this.lugares.get(matricula).setMinutos(minutos);
    }
    
    public int totalMinutos(){
        int soma = 0;
        for(Lugar l: this.lugares.values())
            soma += l.getMinutos();
        return soma;
    }
    
    public int totalMinutosInterno(){
        return this.lugares.values().stream().mapToInt(Lugar::getMinutos).sum();
    }
    
    //método que dá as sáidas dos parques ordenadas por tempo de saída, usando o método compareTo
    //definido na classe Lugar
    public TreeSet<Lugar> saidasOrdenadas(){
        TreeSet<Lugar> tabela = new TreeSet<>();
        for(Lugar l: this.lugares.values())
            tabela.add(l);
        return tabela;
    }
}
