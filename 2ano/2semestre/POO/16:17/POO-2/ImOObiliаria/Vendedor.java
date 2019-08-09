import java.util.GregorianCalendar;
import java.util.Map;
import java.util.HashMap;
import static java.util.stream.Collectors.toMap;

public class Vendedor extends Utilizador
{
    private Map<String, Imovel> avenda;
    private Map<String, Imovel> vendidos;
    
    public Vendedor()
    {
        super();
        this.avenda = new HashMap<String, Imovel>();
        this.vendidos = new HashMap<String, Imovel>();
    }
    
    public Vendedor(String email, String nome, String password, String morada, GregorianCalendar dataNascimento,Map<String, Imovel> avenda, Map<String, Imovel> vendidos)
    {
        super(email,nome,password,morada,dataNascimento);
        this.avenda = new HashMap<String, Imovel>();
        setAvenda(avenda);
        this.vendidos = new HashMap<String, Imovel>();
        setVendidos(vendidos);
    }
    
    public Vendedor(String email, String nome, String password, String morada, GregorianCalendar dataNascimento)
    {
        super(email,nome,password,morada,dataNascimento);
        this.avenda = new HashMap<String, Imovel>();
        this.vendidos = new HashMap<String, Imovel>();
    }
    
    
    public Vendedor(Vendedor v2)
    {
        super(v2);
        this.avenda = v2.getAvenda();
        this.vendidos = v2.getVendidos();
    }
    
    public Map<String, Imovel> getAvenda()
    {
        return this.avenda;
    }
    
    public Map<String, Imovel> getVendidos()
    {
        return this.vendidos;
    }
    
    public void setAvenda(Map<String, Imovel> avenda)
    {
        this.avenda = avenda.entrySet()
                            .stream()
                            .collect(toMap(e->e.getKey(), e->e.getValue().clone()));
    }
    
    public void setVendidos(Map<String, Imovel> vendidos)
    {
        this.vendidos = vendidos.entrySet()
                                .stream()
                                .collect(toMap(e->e.getKey(), e->e.getValue().clone()));
    }
    
    public Vendedor clone()
    {
        return new Vendedor(this);
    }
    
    public boolean equals(Object o)
    {
       if (this == o) return true;
       if ((o==null)||(o.getClass()!=this.getClass())) return false;
       else
       {
           Vendedor v = (Vendedor) o;
           return (super.equals(v) &&
                   this.avenda.equals(v.getAvenda()) &&
                   this.vendidos.equals(v.getVendidos()));
       }
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(super.toString()).append("\n");
        s.append("Vendedor\n");
        s.append("Imoveis à venda: ");
        s.append(this.avenda+"\n");
        s.append("Imoveis vendidos: ");
        s.append(this.vendidos+"\n");
        return s.toString();
    }
}
