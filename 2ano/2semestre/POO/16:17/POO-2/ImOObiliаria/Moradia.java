
public class Moradia extends Imovel implements Habitavel
{
    private String tipo;
    private double area;
    private double areaCoberta;
    private double areaEnvolvente;
    private int quartos;
    private int wcs;
    private int porta;
    
    public Moradia()
    {
        super();
        this.tipo = "";
        this.area = 0.0;
        this.areaCoberta = 0.0;
        this.areaEnvolvente = 0.0;
        this.quartos = 0;
        this.wcs = 0;
        this.porta = 0;
    }
    
    public Moradia(String id, char estado, String rua, double preco, double precoMin, String tipo, double area, double areaCoberta, double areaEnvolvente, int quartos, int wcs, int porta)
    {
        super(id,estado,rua,preco,precoMin);
        this.tipo = tipo;
        this.area = area;
        this.areaCoberta = areaCoberta;
        this.areaEnvolvente = areaEnvolvente;
        this.quartos = quartos;
        this.wcs = wcs;
        this.porta = porta;
    }
    
    public Moradia(Moradia m2)
    {
        super(m2);
        this.tipo = m2.getTipo();
        this.area = m2.getArea();
        this.areaCoberta = m2.getAreaCoberta();
        this.areaEnvolvente = m2.getAreaEnvolvente();
        this.quartos = m2.getQuartos();
        this.wcs =m2.getWcs();
        this.porta = m2.getPorta();
    }
    
    public String getTipo()
    {
        return this.tipo;
    }
    
    public double getArea()
    {
        return this.area;
    }
    
    public double getAreaCoberta()
    {
        return this.areaCoberta;
    }
    
    public double getAreaEnvolvente()
    {
        return this.areaEnvolvente;
    }
    
    public int getQuartos()
    {
        return this.quartos;
    }
    
    public int getWcs()
    {
        return this.wcs;
    }
    
    public int getPorta()
    {
        return this.porta;
    }
    
    public void setTipo(String tipo)
    {
        if (tipo.equals("isolada") || tipo.equals("geminada") || tipo.equals("banda") || tipo.equals("gaveto"))
        this.tipo = tipo;
    }
    
    public void setArea(double area)
    {
         if(area>=0.0) this.area = area;
    }
    
    public void setAreaCoberta(double areaCoberta)
    {
         if(areaCoberta>=0.0 && this.area>=areaCoberta) this.areaCoberta = areaCoberta;
    }
    
    public void setAreaEnvolvente(double areaEnvolvente)
    {
         if(areaEnvolvente>=0.0 && this.area>=areaEnvolvente) this.areaEnvolvente = areaEnvolvente;
    }
    
    public void setQuartos(int quartos)
    {
        if(quartos>=0)this.quartos = quartos;
    }
    
    public void setWcs(int wcs)
    {
         if(wcs>=0) this.wcs = wcs;
    }
    
    public void setPorta(int porta)
    {
         if(porta>=0) this.porta = porta;
    }
    
    public Moradia clone()
    {
        return new Moradia(this);
    }
    
    public boolean equals(Object o)
    {
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        else
        {
            Moradia m = (Moradia) o;
            return (super.equals(m) &&
                    this.tipo.equals(m.getTipo()) &&
                    this.area==m.getArea() &&
                    this.areaCoberta==m.getAreaCoberta() &&
                    this.areaEnvolvente==m.getAreaEnvolvente() &&
                    this.quartos==m.getQuartos() &&
                    this.wcs==m.getWcs() &&
                    this.porta==m.getPorta());
        }
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(super.toString()).append("\n");
        s.append("Moradia\n");
        s.append("Tipo: ");
        s.append(this.tipo+"\n");
        s.append("Area: ");
        s.append(this.area+"\n");
        s.append("Area Coberta: ");
        s.append(this.areaCoberta+"\n");
        s.append("Area Envolvente: ");
        s.append(this.areaEnvolvente+"\n");
        s.append("Numero de quartos: ");
        s.append(this.quartos+"\n");
        s.append("Numero de casas de banho: ");
        s.append(this.wcs+"\n");
        s.append("Numero da porta: ");
        s.append(this.porta+"\n");
        return s.toString();
    }
}
