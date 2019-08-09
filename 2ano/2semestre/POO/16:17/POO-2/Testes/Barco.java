import java.util.*;
public abstract class Barco 
{
    private String id;
    private double milhas;
    private String categoria;
    private double autonomia;
    private Pessoa skipper;
    private Set<Pessoa> tripulantes;
    private List<RegistoEtapa> registos;
    
    public Barco(String id, double milhas, String categoria, double autonomia, Pessoa skipper, Set<Pessoa> tripulantes)
    {
        this.id=id;
        this.milhas=milhas;
        this.categoria=categoria;
        this.autonomia=autonomia;
        this.skipper=skipper;
        this.tripulantes=tripulantes;
    }
    
    public Barco(Barco b2)
    {
        this.id=b2.getId();
        this.milhas=b2.getMilhas();
        this.categoria=b2.getCategoria();
        this.autonomia=b2.getAutonomia();
        this.skipper=b2.getSkipper();
        this.tripulantes=b2.getTripulantes();
    }
    
    public String getId()
    {
        return this.id;
    }
    
    public double getMilhas()
    {
        return this.milhas;
    }
    
    public String getCategoria()
    {
        return this.categoria;
    }
    
    public double getAutonomia()
    {
        return this.autonomia;
    }
    
    public Pessoa getSkipper()
    {
        return this.skipper;
    }
    
    public Set<Pessoa> getTripulantes()
    {
        return this.tripulantes;
    }
    
    public List<RegistoEtapa> getRegistos()
    {
        return this.registos;
    }
    
    public abstract Barco clone();
}
