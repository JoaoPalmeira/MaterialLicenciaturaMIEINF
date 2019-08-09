
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.HashMap;
import java.io.Serializable;

import static java.util.stream.Collectors.toMap;

/**
 * Classe de gestão do complexo de hoteis
 * @author Rui Couto
 */
public class HoteisInc implements Serializable{

    public static final boolean DEVELOPMENT = true;
    
    /** Nome da cadeia */
    private String nome;
    
    /** Data de início de actividade */
    private Date inicio;
    
    /** Mapeamento de código de hotel para hotel */
    private Map<String, Hotel> hoteis;
    
    /** Comparadores*/
    private Map<String, Comparator<Hotel>> comparadores;
    
    /**
     * Construtor vazio
     */
    public HoteisInc() {
        nome = "HoteisInc";
        inicio = new Date();  // VALIDAR
        hoteis = new HashMap<String, Hotel>();
        comparadores = new HashMap<String, Comparator<Hotel>>();
        comparadores.put("codigo", new ComparadorCodigo());
        comparadores.put("preco", new ComparadorPreco());
        //comparadores.put( ... );
    }

    /**
     * Construtor por cópia
     * @param c 
     */
    public HoteisInc(HoteisInc c) {
        this.nome = c.getNome();
        this.inicio = c.getInicio();
        this.hoteis = c.getHoteis();
    }

    /**
     * Construtor por parâmetro
     * @param hoteis 
     */
    public HoteisInc(String nome, Date inicio, Map<String, Hotel> hoteis) {
        this.nome = nome;           // String são imutáveis
        this.inicio = inicio;       // Dates são imutáveis
        this.hoteis = new HashMap<String,Hotel>();
        setHoteis(hoteis);
    }

    /**
     * Obter o nome da cadeia de hoteis
     * @return o nome
     */
    public String getNome() {
        return nome;    // Strings são imutáveis (não é necessário clone)
    }
    
    /**
     * Obter a data de início da cadeia de hoteis
     * @return um Date
     */
    public Date getInicio() {
        return inicio;    // Dates são imutáveis (não é necessário clone)
    }
    
    /**
     * Obter uma cópia do mapeamento de hoteis
     * (Método auxiliar para os construtores)
     * @return um Map
     */
    private Map<String, Hotel> getHoteis() {
        return this.hoteis.entrySet()
                          .stream()
                          .collect(toMap(e->e.getKey(), e->e.getValue().clone()));
    }

    /**
     * Definir os hoteis
     * (Método auxiliar para os construtores)
     * @param hoteis 
     */
    private void setHoteis(Map<String, Hotel> hoteis) {
        this.hoteis = hoteis.entrySet()
                            .stream()
                            .collect(toMap(e->e.getKey(), e->e.getValue().clone()));
    }   
    
    // Métodos pedidos na AULA 1
    
    /**
     * Verificar a existência de um hotel, dado o seu código.
     */
    public boolean existeHotel(String cod) {
        return hoteis.containsKey(cod);
    }
    
     /**
     * Devolver a quantidade de hotéis existentes na cadeia.
     */
    public int quantos() {
        return hoteis.size();
    }
    
    /**
     * Devolver o número total de hotéis de uma dada localidade.
     */
    public int quantos(String loc) {
        return (int) hoteis.values().stream()
                                    .filter( h -> h.getLocalidade().equals(loc))
                                    .count();
    }
    
    /**
     * Devolver a quantidade de hotéis de um dado tipo.
     */
    public int quantosT(String tipo) {
        int c=0;
        for (Hotel h : hoteis.values()){
            String cn = h.getClass().getSimpleName();
            if(cn.equals(tipo)){
                c++;
            }
        }
        return c;
    }
    
   /**
     * Devolver a ficha de um hotel, dado o seu código
     */
    public Hotel getHotel(String cod) throws HotelInexistenteException {
        if(!hoteis.containsKey(cod)){
            throw new HotelInexistenteException("Código inexistente");
        }
        return hoteis.get(cod).clone();
    }
    
    /**
     * Adicionar a informação de um novo hotel
     */
    public void adiciona(Hotel h) {
    }
    
    /**
     * Calcular o valor total diário recebido, considerando uma ocupação dos hotéis de 100%.
     */
    public long total100() {
        return 0;
    }
    
    public void mudaPara (String epoca){
       for(Hotel h : hoteis.values()){
         if(h instanceof HotelStandard){
            HotelStandard h2 = (HotelStandard) h;
            if(epoca.equals("alta")){
               h2.setEpocaAlta(true);
            }
            else{
               h2.setEpocaAlta(false);
            }
         }
       }
    }
 
    public List<CartaoPontos> daoPontos(){
       List<CartaoPontos> r = new ArrayList<CartaoPontos>();
       for(Hotel h : hoteis.values()){
           if(h instanceof CartaoPontos){
               r.add((CartaoPontos) h.clone());
           }
       }
       return r;
    }
    
    public TreeSet<Hotel> ordenarHoteis(){
       TreeSet<Hotel> r = new TreeSet<Hotel>(new ComparadorCodigo());
       for(Hotel h : hoteis.values()){
           r.add(h.clone());
       }
       return r;
    }
    
    /**
     * Ex.6:
     * 1- Implementar comparador por preço; comparador nº quartos
     * 2- Map<String, Comparador<Hotel>>
     * 3- Ex.7
     */
    /*public TreeSet<Hotel> ordenarHoteis(Comparator<Hotel> c){
       
    }*/
    
    
    /*public Iterator<Hotel> ordenarHoteis(String criterio){
       Comparator<Hotel> c = comparadores.get(criteiro);
       TreeSet<Hotel> r = ...
       return r.iterator()
    }
    */
    
    // clone, equals, toString...
    
    /**
     * Devolver uma cópia da instância
     * @return 
     */
    public HoteisInc clone() {
        return new HoteisInc(this);
    }

    /**
     * Verifica a igualdade com outro objecto
     * @param obj
     * @return 
     */
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        HoteisInc hi = (HoteisInc) obj;
        return hi.getNome().equals(nome) && hi.getInicio().equals(inicio) && 
               hi.getHoteis().equals(hoteis);
    }

    /**
     * Devolve representação textual dos hoteis
     * @return 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(nome);
        sb.append("(");
        sb.append(inicio.toString());
        sb.append(", ");
        sb.append(hoteis.toString());
        sb.append(")");
        return sb.toString();
    }
    
}