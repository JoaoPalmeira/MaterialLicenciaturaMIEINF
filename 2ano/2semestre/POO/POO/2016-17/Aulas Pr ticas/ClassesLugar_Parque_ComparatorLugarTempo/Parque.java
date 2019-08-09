import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Write a description of class Parque here.
 * 
 * @author anr 
 * @version 2017.04.10
 */
public class Parque {
    private Map<String, Lugar> lugares;
    private String nome;

    public Parque() {
        this.lugares = new HashMap<>();
        this.nome = new String();
    }
    
    public Parque(String nome, Map<String, Lugar> lugs) {
        this.nome = nome;
        this.lugares = new HashMap<>();
        /*
         * Versão com iteradores externos.
         * 
         * 
        for(Lugar l: lugs.values()) 
          this.lugares.put(l.getMatricula(),l.clone());
        * 
        */
       
       this.lugares = lugs.values().stream()
                                   .collect(Collectors.toMap((e) -> e.getMatricula(),
                                                             (e) -> e.clone())); 
                                                             
      /*
       * Uma alternativa funcional:
       * 
       * this.lugares = lugs.values().stream()
       *                              .collect(Collectors.toMap((e) -> e.getMatricula(),
       *                                                        (e) -> e.clone()));                                                       
      */
                                                                 
                                                             
    }
    
    
    /*
     * Construtor de cópia de Parque.
     */
    
    public Parque(Parque umParque) {
        this.nome = umParque.getNome();
        this.lugares = umParque.getLugares();    
    }
    

    
    /*
     * Método que devolve as chaves do Map.
     * 
     * Nota: não é necessário fazer clone dos objectos, tendo em conta que as chaves são do 
     * tipo String.
     * 
     */
    public Set<String> getMatriculas() {
        return this.lugares.keySet();
    }
    
    
    /*
     * Método que devolve uma cópia do Map de lugares.
     * Tem de ser criada uma cópia dos objectos do tipo Lugar.
     */
    
    public Map<String,Lugar> getLugares() {
        Map<String,Lugar> res = new HashMap<>();
        
        res = this.lugares.values().stream()
                                   .collect(Collectors.toMap((e) -> e.getMatricula(),
                                                             (e) -> e.clone())); 
        return res;
                         
    }
    
    /*
     * Alternativas recorrendo ao entrySet(), e tendo acesso a um conjunto de EntrySet.
     * Abaixo uma alternativa com iteradores externos e outra com iteradores internos.
     * 
     * public Map<String,Lugar> getLugares2() { 
     *     Map<String,Lugar> np = new TreeMap<>();
     *     Iterator<Map.Entry<String,Lugar>> it = this.lugares.entrySet().iterator();
     *     while (it.hasNext()) { 
     *         Map.Entry<String,Lugar> l = it.next();
     *         np.put(l.getKey(),l.getValue().clone());
     *      }
     *      return np; 
     * }
     * 
     *
     * public Map<String,Lugar> getLugares3() { 
     *     Map<String,Lugar> np = new TreeMap<>();
     *     this.lugares.entrySet().forEach( l ->{  np.put(l.getKey(),l.getValue().clone()); }); 
     *     
     *     return np; 
     * }
     * 
     */
    
    
    public String getNome() {
        return this.nome;
    }
    
    public void registaLugar(Lugar l) {
        this.lugares.put(l.getMatricula(), l.clone());
    }
    
    public void removeLugar(Lugar l) {
        this.lugares.remove(l.getMatricula());
    }
    
    public void alteraTempo(String mat, int min) {
        this.lugares.get(mat).setMinutos(min);
    }
    
    public int totalMinutos() {
        int r = 0;
        for(Lugar l : this.lugares.values()) {
            r += l.getMinutos();
        }
        return r;
    }
    
    public int totalMinutosF() {
        return this.lugares.values().stream().mapToInt(Lugar::getMinutos).sum();
    }
    
    public boolean existe(String mat) {
        return this.lugares.containsKey(mat);
    }
    
    public List<String> getMatriculasTempo(int minutos) {
        return this.lugares.values().stream()
                .filter(l -> l.getMinutos()>minutos)
                .map(Lugar::getMatricula)
                .collect(Collectors.toList());
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
    
    
    public TreeSet<Lugar> ordenacaoMinutos() {
        
        TreeSet<Lugar> res = new TreeSet<>(new ComparatorLugarTempo());
        
        for(Lugar l: this.lugares.values())
          res.add(l.clone());
          
          
        return res;
        
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
        
        return (this.ordenacaoMinutos()).first().getMatricula();
    }
}
