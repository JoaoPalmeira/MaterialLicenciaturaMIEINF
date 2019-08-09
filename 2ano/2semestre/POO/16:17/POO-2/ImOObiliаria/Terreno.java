
public class Terreno extends Imovel
{
    private double area;
    private char tipo;
    private double diametro;
    private long kwh;
    private char esgoto;
    
    public Terreno()
    {
        super();
        this.area = 0.0;
        this.tipo = 'H';
        this.diametro = 0.0;
        this.kwh = 0;
        this.esgoto = 'N';
    }
    
    public Terreno(String id, char estado, String rua, double preco, double precoMin, double area, char tipo, double diametro, long kwh, char esgoto)
    {
        super(id,estado,rua,preco,precoMin);
        this.area = area;
        this.tipo = tipo;
        this.diametro = diametro;
        this.kwh = kwh;
        this.esgoto = esgoto;
    }
    
    public Terreno(Terreno t2)
    {
        super(t2);
        this.area = t2.getArea();
        this.tipo = t2.getTipo();
        this.diametro = t2.getDiametro();
        this.kwh = t2.getKwh();
        this.esgoto = t2.getEsgoto();
    }
    
    public double getArea()
    {
        return this.area;
    }
    
    public char getTipo()
    {
        return this.tipo;
    }
    
    public double getDiametro()
    {
        return this.diametro;
    }
    
    public long getKwh()
    {
        return this.kwh;
    }
    
    public char getEsgoto()
    {
        return this.esgoto;
    }
    
    public void setArea(double area)
    {
         if(area>=0.0) this.area = area;
    }
    
    public void setTipo(char tipo)
    {
        if(tipo=='h' || tipo=='H' || tipo=='a' || tipo=='A')this.tipo=tipo; 
    }
    
    public void setDiametro(double diametro)
    {
        if(diametro>=0.0)this.diametro=diametro;
    }
    
    public void setKwh(long kwh)
    {
        if(kwh>=0)this.kwh=kwh;
    }
    
    public void setEsgoto(char esgoto)
    {
        if(esgoto=='s' || esgoto=='S' || esgoto=='n' || esgoto=='N')this.esgoto=esgoto;
    }
    
    public Terreno clone()
    {
        return new Terreno(this);
    }
    
    public boolean equals(Object o)
    {
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        else
        {
            Terreno t = (Terreno) o;
            return (super.equals(t) &&
                    this.area==t.getArea() &&
                    this.tipo==t.getTipo() &&
                    this.diametro==t.getDiametro() &&
                    this.kwh==t.getKwh() &&
                    this.esgoto==t.getEsgoto());
        }
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(super.toString()).append("\n");
        s.append("Terreno\n");
        s.append("Area: ");
        s.append(this.area+"\n");
        s.append("Tipo: ");
        s.append(this.tipo+"\n");
        s.append("Diametro: ");
        s.append(this.diametro+"\n");
        s.append("Tem esgoto? ");
        s.append(this.esgoto+"\n");
        return s.toString();
    }
}
