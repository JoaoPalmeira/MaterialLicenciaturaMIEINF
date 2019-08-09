import java.util.*;

public class Leilao
{
	private boolean adecorrer;
	private String userwinner;
	private double maxbid;
	private Artigo aleiloar;
	private int numeroleilao;
	private Set<String> participantes;

	public Leilao()
	{
		this.adecorrer=false;
		this.userwinner=" ";
		this.maxbid=0.0;
		this.aleiloar=null;
		this.numeroleilao=0;
		this.participantes = new HashSet<String> ();
	}

	public Leilao(boolean b,Artigo a,int n)
	{
		this.adecorrer=b;
		this.userwinner=" ";
		this.maxbid=0.0;
		this.aleiloar=a;
		this.numeroleilao=n;
		this.participantes = new HashSet<String> ();
	}

	public Leilao(Leilao l)
	{
		this.adecorrer=l.getEstado();
		this.userwinner=l.getWinner();
		this.maxbid=l.getMaxbid();
		this.aleiloar=l.getArtigo();
		this.numeroleilao=l.getID();
		this.participantes = l.getParticipantes();
	}

	public boolean getEstado()
	{
		return this.adecorrer;
	}

	public String getWinner()
	{
		return this.userwinner;
	}

	public double getMaxbid()
	{
		return this.maxbid;
	}

	public Artigo getArtigo()
	{
		return this.aleiloar;
	}

	public int getID()
	{
		return this.numeroleilao;
	}

	public Set<String> getParticipantes()
	{
		Set<String> aux = new HashSet<String> ();

		for(String u : this.participantes)
		{
			aux.add(u);
		}

		return aux;
	}

	public synchronized  void setEstado(boolean b)
	{
	     this.adecorrer=b;
	}

	public synchronized void setWinner(String u)
	{
		this.userwinner=u;
	}

	public synchronized void setMaxbid(double m)
	{
		this.maxbid=m;
	}

	public synchronized void setArtigo(Artigo a)
	{
		 this.aleiloar=a;
	}

	public synchronized void setID(int n)
	{
	    this.numeroleilao=n;
	}

	public synchronized void setParticipantes(Set<String> p)
	{
		for(String u : p)
		{
			this.participantes.add(u);
		}
	}

	public synchronized Leilao iniciaLeilao(String desc,int numultlei)
	{
	   Artigo a = new Artigo();
	   a = a.getArtbydesc(desc);
       Leilao l = new Leilao(true,a,numultlei+1);

       return l;
	}

	public synchronized void fazlicitacao(double bid,String user)
	{
      if(participantes.contains(user) == false)
      {
      	participantes.add(user);
      }  

      if(bid > maxbid)
      {
      	maxbid = bid;
      	userwinner = user;
      }
	}

	public synchronized void encerraLeilao()
	{
		this.adecorrer = false;
		aleiloar.setEstadoArt("VENDIDO");
	}

	public Leilao clone()
	{
		return new Leilao(this);
	} 

	public boolean equals(Object o)
   {
         if(this == o)
        {
            return true;
        }
        
        if((o == null) || (this.getClass() != o.getClass()))
        {
            return false;
        }
        
        Leilao l = (Leilao) o;
        
        if(this.participantes.size() != l.participantes.size())
        {
            return false;
        }
        
        for(String p: l.participantes)
        {
            if(!this.participantes.contains(p)) return false;
        }
        
        return this.adecorrer == l.getEstado() && this.userwinner.equals(l.getWinner())
                                               && this.maxbid == l.getMaxbid()
                                               && this.aleiloar.equals(l.getArtigo())
                                               && this.numeroleilao == l.getID();
    }
}