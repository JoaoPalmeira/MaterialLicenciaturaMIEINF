import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;
/**
 * Classe que representa um geocacher.
 * Um geocacher possui uma lista de caches
 * 
 * @author Rui Couto
 * @version 1.0
 */
public class Geocacher
{
    /**
     * Conjunto de caches
     */
    private Set<Cache> caches;
    
    /**
     * Construtor vazio
     */
    public Geocacher() {
        caches = new HashSet<>();
    }

    /**
     * Construtor por parametro
     */
    public Geocacher(Set<Cache> caches) {
        setCaches(caches);
    }
    
    /**
     * Construtor por copia
     */
    public Geocacher(Geocacher geocacher) {
        this.caches = geocacher.getCaches();
    }
    
    /**
     * Define o valor da variavel caches
     */
    public void setCaches(Set<Cache> caches) {
        this.caches = new HashSet<>();
        for(Cache c : caches) {
            this.caches.add(c.clone());
        }
    }

    /**
     * Retorna uma copia da variavel caches
     */
    public Set<Cache> getCaches() {
        Set<Cache> caches2 = new HashSet<>();
        for(Cache c : caches) {
            caches2.add(c.clone());
        }
        return caches2;
    }
    
    /**
     * Adiciona uma nova cache
     */
    public void addCache(String nome, String descricao, double lat, double lon, int dificuldade) {
        this.caches.add(new Cache(nome, descricao, lat, lon, dificuldade));
    }    
    
    /**
     * Adiciona uma nova cache
     */
    public void addCache(Cache c) {
        caches.add(c.clone());
    }
    
    /**
     * Retorna o número total de caches
     */
    public int numCaches() {
        return caches.size();
    }
        
    /**
     * Retorna o numero de caches com dificuldade superior à indicada
     */
    public int numCaches(int dificuldade) {
        return (int) caches.stream()
                           .filter(c -> c.getDificuldade() > dificuldade)
                           .count();
    }
    
    /**
     * Retorna a cache para a latitude e longitude indicadas
     */
    public Cache obterCache(double lat, double lon) {
        Cache c = null;
        Iterator<Cache> it = caches.iterator();
        while(it.hasNext()) {
            Cache t = it.next();
            if(t.getLat() == lat && t.getLon() == lon) {
                c = t.clone();
            }
        }
        return c;
    }
    
    /**
     * Obtem a cache com o nome igual ao indicado
     */
    public Cache obterCache(String nome) {
        Cache c = null;
        Iterator<Cache> it = caches.iterator();
        while(it.hasNext() && c==null) {
            Cache t = it.next();
            if(t.getNome() == nome) {
                c = t.clone();
            }
        }
        return c;
    }
    
    /**
     * Obtem as caches com latitude superior a indicada
     */
    public List<Cache> obterCaches(double lat) {
        return caches.stream()
                     .filter(c -> c.getLat() > lat)
                     .map(Cache::clone)
                     .collect(Collectors.toList());
    }
    
    /**
     * Obter o nome de todas as caches numa so string
     */
    public String obterNomeCaches() {
        return caches.stream()
                     .map(Cache::getNome)
                     .reduce("", (a, b) -> a +" "+ b);
    }
    
    
    /**
     * Retorna a soma dos pontos de todas as caches
     */
    public double totalPontos(int bonus) {
        return caches.stream()
                     .mapToDouble(c -> c.getDificuldade())
                     .sum();
    }
        
    /**
     * Retorna uma nova instância de Geocacher
     */
    public Geocacher clone() {
        return new Geocacher(this);
    }
    
    /**
     * Verifica a igualdade de dois Geocachers
     */
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Geocacher o = (Geocacher) obj;
        return o.getCaches().equals(caches);
    }
    
}
