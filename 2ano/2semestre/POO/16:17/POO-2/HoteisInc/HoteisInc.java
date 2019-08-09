
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import static java.util.stream.Collectors.toMap;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Comparator;
import java.io.*;

/**
 * Classe de gestão do complexo de hoteis
 * @author Rui Couto
 */
public class HoteisInc 
{

    /** Nome da cadeia */
    private String nome;
    
    /** Data de início de actividade */
    private Date inicio;
    
    /** Mapeamento de código de hotel para hotel */
    private Map<String, Hotel> hoteis;
    
   // private TreeMap<String, Comparator<Hotel>> ordenacao = new TreeMap<String, Compare<Hotel>>();
    
    /**
     * Construtor vazio
     */
    public HoteisInc() 
    {
        nome = "HoteisInc";
        inicio = new Date();  // VALIDAR
        hoteis = new HashMap<String, Hotel>();
    }

    /**
     * Construtor por cópia
     * @param c 
     */
    public HoteisInc(HoteisInc c) 
    {
        this.nome = c.getNome();
        this.inicio = c.getInicio();
        this.hoteis = c.getHoteis();
    }

    /**
     * Construtor por parâmetro
     * @param hoteis 
     */
    public HoteisInc(String nome, Date inicio, Map<String, Hotel> hoteis) 
    {
        this.nome = nome;           // String são imutáveis
        this.inicio = inicio;       // Dates são imutáveis
        this.hoteis = new HashMap<String,Hotel>();
        setHoteis(hoteis);
    }

    /**
     * Obter o nome da cadeia de hoteis
     * @return o nome
     */
    public String getNome() 
    {
        return nome;    // Strings são imutáveis (não é necessário clone)
    }
    
    /**
     * Obter a da início da cadeia de hoteis
     * @return um Date
     */
    public Date getInicio() 
    {
        return inicio;    // Dates são imutáveis (não é necessário clone)
    }
    
    /**
     * Obter uma cópia do mapeamento de hoteis
     * (Método auxiliar para os construtores)
     * @return um Map
     */
    private Map<String, Hotel> getHoteis() 
    {
        return this.hoteis.entrySet()
                          .stream()
                          .collect(toMap(e->e.getKey(), e->e.getValue().clone()));
    }

    /**
     * Definir os hoteis
     * (Método auxiliar para os construtores)
     * @param hoteis 
     */
   private void setHoteis(Map<String, Hotel> hoteis) 
    {
        this.hoteis = hoteis.entrySet()
                            .stream()
                            .collect(toMap(e->e.getKey(), e->e.getValue().clone()));
    }   
    
    // Métodos pedidos na AULA 1
    
    /**
     * Verificar a existência de um hotel, dado o seu código.
     */
    public boolean existeHotel(String cod) 
    {
        return this.hoteis.containsKey(cod);
    }
    
     /**
     * Devolver a quantidade de hotéis existentes na cadeia.
     */
    public int quantos() 
    {
        return this.hoteis.size();
    }
    
    /**
     * Devolver o número total de hotéis de uma dada localidade.
     */
    public int quantos(String loc) 
    {
        return (int) this.hoteis
                    .values()
                    .stream()
                    .filter(h->h.getLocalidade().equals(loc))
                    .count();
    }
    
    /**
     * Devolver a quantidade de hotéis de um dado tipo.
     */
    public int quantosT(String tipo) 
    {
        int total = 0;
        for(Hotel h : this.hoteis.values())
            if (h.getClass().getSimpleName().equals(tipo)) total++;
        return total;
    }
    
    /**
     * Devolver a ficha de um hotel, dado o seu código
     */
    public Hotel getHotel(String cod) 
    {
        return this.hoteis.get(cod);
    }
    
    /**
     * Adicionar a informação de um novo hotel
     */
    public void adiciona(Hotel h) throws HotelExisteException
    {
        if(this.hoteis.containsKey(h.getCodigo())) throw new HotelExisteException();
        this.hoteis.put(h.getCodigo(),h.clone());
    }
    
    /**
     * Calcular o valor total diário recebido, considerando uma ocupação dos hotéis de 100%.
     */
    public long total100() {
        long conta = 0;
        for(Hotel h : this.hoteis.values())
            conta += h.getPrecoQuarto() * h.getNumeroQuartos();
        return conta;
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
    
    public List<CartaoPontos> daoPontos()
    {
        ArrayList<CartaoPontos> l = new ArrayList<CartaoPontos>();
        for(Hotel h : this.hoteis.values())
            if((h instanceof HotelStandard) || (h instanceof HotelPremium))
            {
                CartaoPontos cp = (CartaoPontos) h;
                l.add(cp);
            }
        return l;
    }
    
    public TreeSet<Hotel> ordemHoteis()
    {
        TreeSet<Hotel> hs = new TreeSet<Hotel>();
        for(Hotel h : this.hoteis.values())
            hs.add(h.clone());
        return hs;
    }
    
    public TreeSet<Hotel> ordemHoteis(Comparator<Hotel> c)
    {
        TreeSet<Hotel> hs = new TreeSet<Hotel>();
        for(Hotel h : this.hoteis.values())
            hs.add(h.clone());
        return hs;
    }
    
    /*private void addCriterio(String s,Comparator<Hotel> c)
    {
        this.ordenacao.put(s,c);
    }*/
    
    /*private Comparator<Hotel> getCriterio(String s)
    {
        return this.ordenacao.get(s);
    }*/
    
    public void gravaHoteis(String fich) throws IOException
    {
        PrintWriter pw = new PrintWriter(fich);
        pw.print(this);
        pw.flush();
        pw.close();
    }
}