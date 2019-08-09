import java.util.GregorianCalendar;
import java.util.Map;
import java.util.HashMap;
import static java.util.stream.Collectors.toMap;

public class Comprador extends Utilizador
{
    private Map<String, Imovel> favoritos;
    
    public Comprador()
    {
        super();
        this.favoritos = new HashMap<String, Imovel>();
    }
    
    public Comprador(String email, String nome, String password, String morada, GregorianCalendar dataNascimento, Map<String, Imovel> favoritos)
    {
        super(email,nome,password,morada,dataNascimento);
        this.favoritos = new HashMap<String, Imovel>();
        setFavoritos(favoritos);
    }

    public Comprador(String email, String nome, String password, String morada, GregorianCalendar dataNascimento)
    {
        super(email,nome,password,morada,dataNascimento);
        this.favoritos = new HashMap<String, Imovel>();
    }
    
    public Comprador(Comprador c2)
    {
        super(c2);
        this.favoritos = c2.getFavoritos();
    }
    
    public Map<String, Imovel> getFavoritos()
    {
        return this.favoritos;
    }
    
    public void setFavoritos(Map<String, Imovel> favoritos)
    {
        this.favoritos = favoritos.entrySet()
                                  .stream()
                                  .collect(toMap(e->e.getKey(), e->e.getValue().clone()));
    }
    
    public Comprador clone()
    {
        return new Comprador(this);
    }
    
    public boolean equals(Object o)
    {
       if (this == o) return true;
       if ((o==null)||(o.getClass()!=this.getClass())) return false;
       else
       {
           Comprador c = (Comprador) o;
           return (super.equals(c) &&
                   this.favoritos.equals(c.getFavoritos()));
       }
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(super.toString()).append("\n");
        s.append("Comprador\n");
        s.append("Favoritos: ");
        s.append(this.favoritos+"\n");
        return s.toString();
    }
}