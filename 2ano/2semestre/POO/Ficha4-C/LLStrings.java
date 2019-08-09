import java.lang.String;
public class LLStrings
{
    private int numeroElementos;
    private Elementos primeiro;
    private Elementos ultimo;
    
    public LLStrings()
    {
        this.primeiro=null;
        this.ultimo=null;
        this.numeroElementos=0;
    }
       
    public LLStrings(LLStrings l2)
    {
        this.primeiro=l2.getPrimeiro();
        this.ultimo=l2.getUltimo();
        this.numeroElementos=l2.getNumero();
    }
    
    public Elementos getPrimeiro()
    {
        return this.primeiro;
    }
    
    public Elementos getUltimo()
    {
        return this.ultimo;
    }
    
    public int getNumero()
    {
        return this.numeroElementos;
    }
    
    public void setPrimeiro(Elementos e)
    {
        this.primeiro = e;
    }
    
    public void setUltimo(Elementos e)
    {
        this.ultimo = e;
    }
    
    public void setNumero(int n)
    {
        this.numeroElementos = n;
    }
    
    public LLStrings clone()
    {
        return new LLStrings (this );
    }
    
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if ((o==null)||(o.getClass()!=this.getClass())) return false;
        else 
        {
            Elementos atual = this.primeiro;
            while (atual!=null)
            {
                if (atual.getTexto().equals(o)) return true;
                atual = atual.getProximo();
            }
            return false;
        }
    }
    
    public String toString()
    {
         if(this.primeiro==null)  return "Primeiro: null";
         StringBuilder s = new StringBuilder();
         Elementos atual = this.primeiro;
         s.append("Primeiro: "+atual.getTexto()+", ");
         for(int i=0;i<this.numeroElementos-1;i++)
         {
             atual = atual.getProximo();
             s.append("Elemento: "+atual.getTexto()+", ");
         }
         s.append("Ultimo: "+atual.getTexto());
         s.append(".");
         return s.toString();
    }
    
    public int tamanho()
    {
        return this.numeroElementos;
    }
    
    public boolean vazia()
    {
        if(this.primeiro==null) return true;
        else return false;
    }
    
    public void adicionar(String s)
    {
        if (this.primeiro == null)
        {
            Elementos e = new Elementos (s,this.primeiro);
            this.primeiro = e;
            if (this.numeroElementos == 0) this.ultimo = this.primeiro;
            this.numeroElementos++;
        }
        else
        {
            Elementos e = new Elementos(s);
            this.ultimo.setProximo(e);
            this.ultimo = e;
            this.numeroElementos++;
        }
    }
    
    private Elementos getElementos(int posicao) 
    {
       Elementos atual = this.primeiro;
        for (int i = 0; i < posicao; i++) 
        {
            atual = atual.getProximo();
        }
        return atual;
    }
    
    public void inserir(int i, String s)
    {
        Elementos e = new Elementos(s,null);
        int conta=0;
        if(this.primeiro==null)
        {
            this.primeiro = e;
            this.numeroElementos++;
        }
        if(i<=0)
        {
            this.primeiro = e;
            this.numeroElementos++;
        }
        if(i>=this.numeroElementos)
        {
            adicionar(s);
            this.numeroElementos++;
        }
        else
        {
            Elementos anterior = this.getElementos(i- 1);
            Elementos novo = new Elementos(s,anterior.getProximo());
            anterior.setProximo(novo);
            this.numeroElementos++;
        }
    }
    
    public String get(int i)
    {
       if (i>=0&&i<=this.numeroElementos)
       return this.getElementos(i).getTexto();
       else return "Posição Invalida";
    }
    
    public void esvaziar()
    {
        this.primeiro=null;
        this.ultimo=null;
        this.numeroElementos=0;
    }
}
