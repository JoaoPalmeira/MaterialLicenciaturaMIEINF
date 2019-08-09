public class Apartamento extends Imovel implements Habitavel
{
    private String tipo; 
    private double area;
    private int quartos;
    private int wcs;
    private int porta;
    private int andar;
    private char garagem;
    
    public Apartamento()
    {
        super();
        this.tipo = "";
        this.area = 0.0;
        this.quartos = 0;
        this.wcs = 0;
        this.porta = 0;
        this.andar = 0;
        this.garagem = 'N';
    }
    
    public Apartamento(String id, char estado, String rua, double preco, double precoMin, String tipo, double area, int quartos, int wcs, int porta, int andar, char garagem)
    {
        super(id,estado,rua,preco,precoMin);
        this.tipo = tipo;
        this.area = area;
        this.quartos = quartos;
        this.wcs = wcs;
        this.porta = porta;
        this.andar = andar;
        this.garagem = garagem;
    }
    
    public Apartamento(Apartamento a2)
    {
        super(a2);
        this.tipo = a2.getTipo();
        this.area = a2.getArea();
        this.quartos = a2.getQuartos();
        this.wcs = a2.getWcs();
        this.porta = a2.getPorta();
        this.andar = a2.getAndar();
        this.garagem = a2.getGaragem();
    }
    
    public String getTipo()
    {
        return this.tipo;
    }
    
    public double getArea()
    {
        return this.area;
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
    
    public int getAndar()
    {
        return this.andar;
    }
    
    public char getGaragem()
    {
        return this.garagem;
    }
    
    public void setTipo(String tipo)
    {
        if(tipo.equals("Simples") || tipo.equals("Duplex") || tipo.equals("Triplex"))
        this.tipo = tipo;
    }
    
    public void setArea(double area)
    {
         if(area>=0.0) this.area = area;
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
    
    public void setAndar(int andar)
    {
        if(andar>=0)this.andar = andar;
    }
    
    public void setGaragem(char garagem)
    {
        if(garagem=='s' || garagem=='S' || garagem=='n' || garagem=='N')this.garagem = garagem;
    }
    
    public Apartamento clone()
    {
        return new Apartamento(this);
    }
    
    public boolean equals(Object o)
    {
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        else
        {
            Apartamento a = (Apartamento) o;
            return (super.equals(a) &&
                    this.tipo.equals(a.getTipo()) &&
                    this.area==a.getArea() &&
                    this.quartos==a.getQuartos() &&
                    this.wcs==a.getWcs() &&
                    this.porta==a.getPorta() &&
                    this.andar==a.getAndar() &&
                    this.garagem==a.getGaragem());
        }
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(super.toString()).append("\n");
        s.append("Apartamento\n");
        s.append("Tipo: ");
        s.append(this.tipo+"\n");
        s.append("Area: ");
        s.append(this.area+"\n");
        s.append("Numero de quartos: ");
        s.append(this.quartos+"\n");
        s.append("Numero de casas de banho: ");
        s.append(this.wcs+"\n");
        s.append("Numero da porta: ");
        s.append(this.porta+"\n");
        s.append("Andar: ");
        s.append(this.andar+"\n");
        s.append("Tem Garagem? "); 
        s.append(this.garagem+"\n");
        return s.toString();
    }
}
