import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public abstract class Imovel implements Serializable
{
    private String id;
    private char estado;
    private String rua;
    private double preco;
    private double precoMin;
    private List<Consulta> consultas;
    
    public Imovel()
    {
        this.id = "";
        this.estado = 'V';
        this.rua = "";
        this.preco = 0.0;
        this.precoMin = 0.0;
        this.consultas = new ArrayList<Consulta>();
    }
    
    public Imovel(String id, char estado, String rua, double preco, double precoMin)
    {
        this.id = id;
        this.estado = estado;
        this.rua = rua;
        this.preco = preco;
        this.precoMin = precoMin;
        this.consultas = new ArrayList<Consulta>();
    }
    
    public Imovel(String id, char estado, String rua, double preco, double precoMin, List<Consulta> consultas)
    {
        this.id = id;
        this.estado = estado;
        this.rua = rua;
        this.preco = preco;
        this.precoMin = precoMin;
        this.consultas = consultas;
    }
    
    public Imovel(Imovel i2)
    {
        this(i2.getId(),i2.getEstado(),i2.getRua(),i2.getPreco(),i2.getPrecoMin(),i2.getConsultas());
    }
    
    public String getId()
    {
        return this.id;
    }
    
    public char getEstado()
    {
        return this.estado;
    }
    
    public String getRua()
    {
        return this.rua;
    }
    
    public double getPreco()
    {
        return this.preco;
    }
    
    public double getPrecoMin()
    {
        return this.precoMin;
    }
    
    public List<Consulta> getConsultas()
    {
        return this.consultas;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public void setRua(String rua)
    {
        this.rua = rua;
    }
    
    public void setEstado(char estado)
    {
        this.estado = estado;
    }
    
    public void setPreco(double preco)
    {
        if(this.precoMin<=preco) this.preco = preco;
    }
    
    public void setPrecoMin(double precoMin)
    {
        if(precoMin>=0) this.precoMin = precoMin;
    }
    
    public void setConsultas(List<Consulta> consultas)
    {
        this.consultas = consultas;
    }
    
    public abstract Imovel clone();
    
    public boolean equals(Object o)
    {
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        else
        {
            Imovel i = (Imovel) o;
            return (this.id.equals(i.getId()) &&
                    this.estado==i.getEstado() &&
                    this.rua.equals(i.getRua()) &&
                    this.preco==i.getPreco() &&
                    this.precoMin==i.getPrecoMin() &&
                    this.consultas.equals(i.getConsultas()));
        }
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("\nImovel\n");
        s.append("Id: ");
        s.append(this.id+"\n");
        s.append("Estado: ")    ;
        s.append(this.estado+"\n");
        s.append("Rua: ");
        s.append(this.rua+"\n");
        s.append("Preço: ");
        s.append(this.preco+"\n");
        s.append("Preço Mínimo: ");
        s.append(this.precoMin+"\n");
        return s.toString();
    }
}
