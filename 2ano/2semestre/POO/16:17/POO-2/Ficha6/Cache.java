/**
 * Classe que representa uma cache.
 * 
 * @author Rui Couto
 * @version 1.0
 */
public class Cache {
    /** Nome da cache */
    private String nome;
    /** Descricao da cache */
    private String descricao;
    /** Latitude */
    private double lat;
    /** Longitude */
    private double lon;
    /** Dificuldade*/
    private int dificuldade;

    /**
     * Construtor vazio
     */
    public Cache() {
        nome = "n/a";
        descricao = "n/a";
        lat = 0.0;
        lon = 0.0;
        dificuldade = 0;
    }
    
    /**
     * Construtor por copia
     */
    public Cache(Cache geocache) {
        nome = geocache.getNome();
        descricao = geocache.getDescricao();
        lat = geocache.getLat();
        lon = geocache.getLon();
        dificuldade = geocache.getDificuldade();
    }

    /**
     * Construtor por parametro
     */
    public Cache(String nome, String descricao, double lat, double lon, int dificuldade) {
        setNome(nome);
        setDescricao(descricao);
        setLat(lat);
        setLon(lon);
        setDificuldade(dificuldade);
    }

    /**
     * Devolve o nome da cache
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da cache
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Devolve a descricao da cache
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descricao da cache
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Devolve a latitude da cache
     */
    public double getLat() {
        return lat;
    }

    /**
     * Define a latitude da cache
     */
    public void setLat(double lat) {
        this.lat = lat;
    }
    
    /**
     * Devolve a longitude da cache
     */
    public double getLon() {
        return lon;
    }

    /**
     * Define a longitude da cache
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * Devolve a dificuldade da cache
     */
    public int getDificuldade() {
        return dificuldade;
    }

    /**
     * Define a dificuldade da cache.
     * A dificuldade deve ser um valor entre 0 e 5.
     */
    public void setDificuldade(int dificuldade) {
        if (dificuldade>=0 && dificuldade<=5)this.dificuldade = dificuldade;
    }
        
    /**
     * Método que calcula os pontos gerados nesta cache.
     * A fórmula de cálculo é:
     *   (|lat|*|lon|)/2 * dificuldade + bonus
     * @return Valor do bónus, que será sempre um valor positivo
     */
    public double pontos(int bonus) {
        return (lat*lon)/2*dificuldade-bonus;
    }
    
    /**
     * Devolve uma representação textual da cache
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cache:\n").append(nome).append(";").append(descricao)
                .append("\n(").append(lat).append(",").append(lon).append(")")
                .append(": ").append(dificuldade);
        return sb.toString();
    }
    
    /**
     * Devolve uma cópia do objecto cache
     */
    protected Cache clone() {
        return new Cache (this);
    }  
    
    public boolean Cache(Object o) 
    {
        if(o==this) 
        {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) 
        {
            return false;
        }
        Cache c = (Cache) o;
        return c.getNome().equals(nome) && c.getDescricao().equals(descricao) && c.getLat() == lat && c.getLon() == lon;
    }
}
