
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import static java.util.stream.Collectors.toMap;

/**
 * Classe de gestão do complexo de hoteis
 * @author Rui Couto
 */
public class HoteisInc {

    /** Nome da cadeia */
    private String nome;
    
    /** Data de início de actividade */
    private Date inicio;
    
    /** Mapeamento de código de hotel para hotel */
    private Map<String, Hotel> hoteis;
    
    /**
     * Construtor vazio
     */
    public HoteisInc() {
        nome = "HoteisInc";
        inicio = new Date();  // VALIDAR
        hoteis = new HashMap<String, Hotel>();
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
     * Obter a da início da cadeia de hoteis
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
        return false;
    }
    
     /**
     * Devolver a quantidade de hotéis existentes na cadeia.
     */
    public int quantos() {
        return 0;
    }
    
    /**
     * Devolver o número total de hotéis de uma dada localidade.
     */
    public int quantos(String loc) {
        return 0;
    }
    
    /**
     * Devolver a quantidade de hotéis de um dado tipo.
     */
    public int quantosT(String tipo) {
        return 0;
    }
    
   /**
     * Devolver a ficha de um hotel, dado o seu código
     */
    public Hotel getHotel(String cod) {
        return null;
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