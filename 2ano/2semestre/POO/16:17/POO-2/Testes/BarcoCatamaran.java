import java.util.*;
public class BarcoCatamaran extends Barco implements Seguro
{
    private double area;
    private double seguro;
    
    public BarcoCatamaran(String id, double milhas, String categoria, 
    double autonomia, Pessoa skipper, Set<Pessoa> tripulantes, double area, 
    double seguro)
    {
        super(id,milhas,categoria,autonomia,skipper,tripulantes);
        this.area=area;
        this.seguro=seguro;
    }
    
    public BarcoCatamaran(BarcoCatamaran b2)
    {
        super(b2);
        this.area=b2.getArea();
        this.seguro=b2.getSeguro();
    }
    
    public double getArea()
    {
        return this.area;
    }
    
    public double getSeguro()
    {
        return this.seguro;
    }
    
    public BarcoCatamaran clone()
    {
        return new BarcoCatamaran(this);
    }
}
