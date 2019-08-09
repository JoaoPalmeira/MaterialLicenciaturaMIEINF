import java.util.*;
public class BarcoHibrido extends Barco implements Seguro
{
    private long bateria;
    private long potencia;
    private double autonomiaE;
    private double seguro;
    
    public BarcoHibrido(String id, double milhas, String categoria, 
    double autonomia, Pessoa skipper, Set<Pessoa> tripulantes, long bateria, 
    long potencia, double autonomiaE, double seguro)
    {
        super(id,milhas,categoria,autonomia,skipper,tripulantes);
        this.bateria=bateria;
        this.potencia=potencia;
        this.autonomiaE=autonomiaE;
        this.seguro=seguro;
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(super.toString()).append("\n");
        s.append("Hibrido\n");
        s.append("Bateria: ");
        s.append(this.bateria+"\n");
        s.append("Potencia: ");
        s.append(this.potencia+"\n");
        s.append("Autonomia Eletrica: ");
        s.append(this.autonomiaE+"\n");
        s.append("Seguro: ");
        s.append(this.seguro+"\n");
        return s.toString();
    }
    
    public BarcoHibrido(BarcoHibrido b2)
    {
        super(b2);
        this.autonomiaE=b2.getAutonomiaE();
        this.bateria=b2.getBateria();
        this.potencia=b2.getPotencia();
        this.seguro=b2.getSeguro();
    }
    
    public double getAutonomiaE()
    {
        return this.autonomiaE;
    }
    
    public long getBateria()
    {
        return this.bateria;
    }
    
    public long getPotencia()
    {
        return this.potencia;
    }
    
    public double getSeguro()
    {
        return this.seguro;
    }
    
    public BarcoHibrido clone()
    {
        return new BarcoHibrido(this);
    }
    
    double getAutonomiaTotal()
    {
        return this.autonomiaE+this.getAutonomia();
    }
}
