
public class Loja extends Imovel
{
    private double area;
    private char wc;
    private String tipo;
    private int porta;
    
    public Loja()
    {
        super();
        this.area = 0.0;
        this.wc = 'N';
        this.tipo = "";
        this.porta = 0;
    }
    
    public Loja(String id, char estado, String rua, double preco, double precoMin, double area, char wc, String tipo, int porta)
    {
        super(id,estado,rua,preco,precoMin);
        this.area = area;
        this.wc = wc;
        this.tipo = tipo;
        this.porta = porta;
    }
    
    public Loja(Loja l2)
    {
        super(l2);
        this.area = l2.getArea();
        this.wc = l2.getWc();
        this.tipo = l2.getTipo();
        this.porta = l2.getPorta();
    }
    
    public double getArea()
    {
        return this.area;
    }
    
    public char getWc()
    {
        return this.wc;
    }
    
    public String getTipo()
    {
        return this.tipo;
    }
    
    public int getPorta()
    {
        return this.porta;
    }
    
    public void setArea(double area)
    {
         if(area>=0.0) this.area = area;
    }
    
    public void setWc(char wc)
    {
        if(wc=='s' || wc=='S' || wc=='n' || wc=='N')this.wc = wc;
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
    
    public void setPorta(int porta)
    {
        if(porta>=0)this.porta = porta;
    }
    
    public Loja clone()
    {
        return new Loja(this);
    }
    
    public boolean equals(Object o)
    {
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        else
        {
            Loja l = (Loja) o;
            return (super.equals(l) &&
                    this.area==l.getArea() &&
                    this.wc==l.getWc() &&
                    this.tipo.equals(l.getTipo()) &&
                    this.porta==l.getPorta());
        }
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(super.toString()).append("\n");
        s.append("Loja\n");
        s.append("Area: ");
        s.append(this.area+"\n");
        s.append("Tem casa de banho? ");
        s.append(this.wc+"\n");
        s.append("Tipo: ");
        s.append(this.tipo+"\n");
        s.append("Numero da porta: ");
        s.append(this.porta+"\n");
        return s.toString();
    }
}
