
public class Elementos
{
    private String texto;
    private Elementos proximo;
    
    public Elementos(String txt,Elementos prox)
    {
        this.texto=txt;
        this.proximo=prox;
    }
    
    public Elementos (String txt)
    {
        this.texto = txt;
    }
    
    public Elementos()
    {
        new Elementos("",null);
    }
   
    public Elementos(Elementos e2)
    {
        this.texto = e2.getTexto();
        this.proximo = e2.getProximo();
    }
    
    public String getTexto()
    {
        return this.texto;
    }
    
    public Elementos getProximo()
    {
        return this.proximo;
    }
    
    public void setTexto (String txt)
    {
        this.texto=txt;
    }
    
    public void setProximo (Elementos prox)
    {
        this.proximo=prox;
    }
    
    public Elementos clone()
    {
        return new Elementos (this.texto,this.proximo);
    }
    
     public boolean equals (Object o)
    {
        if (this == o) return true;
        if ((o==null)||(o.getClass()!=this.getClass())) return false;
        else 
        {
            Elementos e = (Elementos) o;
            return (e.getTexto()==this.texto && e.getProximo()==this.proximo);
        }
    }
    
    public String toStringElem()
    {
        StringBuilder s = new StringBuilder();
        s.append("Texto: "+this.texto+" ");
        s.append("Proximo: "+this.proximo+" ");
        return s.toString();
    }
}
