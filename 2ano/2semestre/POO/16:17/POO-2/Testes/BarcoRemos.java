import java.util.*;
public class BarcoRemos extends Barco
{
    private double largura;
    
    public BarcoRemos(String id, double milhas, String categoria, 
    double autonomia, Pessoa skipper, Set<Pessoa> tripulantes, double largura)
    {
        super(id,milhas,categoria,autonomia,skipper,tripulantes);
        this.largura=largura;
    }
    
    public BarcoRemos(BarcoRemos b2)
    {
        super(b2);
        this.largura=b2.getLargura();
    }
    
    public double getLargura()
    {
        return this.largura;
    }
    
    public BarcoRemos clone()
    {
        return new BarcoRemos(this);
    }
}