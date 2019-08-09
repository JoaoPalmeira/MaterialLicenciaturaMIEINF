public class Artigo
{
	private String nome;
	private String descricao;
	private String estado;
	private String owner;
	private int number;

	public Artigo()
	{
		this.nome=" ";
		this.descricao=" ";
		this.estado=" ";
		this.owner=" ";
		this.number=0;
	}

	public Artigo(String n,String d,String e,String o,int num)
	{
		this.nome = n;
		this.descricao = d;
		this.estado = e;
		this.owner=o;
		this.number=num;
	}

	public Artigo(Artigo a)
	{
		this.nome = a.getNome();
		this.descricao = a.getDesc();
		this.estado = a.getEstado();
		this.owner = a.getOwner();
		this.number = a.getNumber();
	}

	public String getNome()
	{
		return this.nome;
	}

	public String getDesc()
	{
		return this.descricao;
	}

	public String getEstado()
	{
		return this.estado;
	}

    public String getOwner()
    {
    	return this.owner;
    }

    public int getNumber()
    {
    	return this.number;
    }

    public Artigo getArtbydesc(String desc)
    {
    	Artigo a = null;

    	if(this.descricao.equals(desc))
    	{
    		return a.clone();
    	}
    	else
    		return a;
    }

	public void setNome(String n)
	{
		this.nome=n;
	}

	public void setDesc(String d)
	{
		this.descricao = d;
	}

	public void setEstadoArt(String e)
	{
		this.estado = e;
	}

	public void setOwner(String o)
	{
		this.owner=o;
	}

	public void setNumber(int n)
	{
		this.number=n;
	}

	public Artigo clone()
	{
		return new Artigo(this);
	}

	public boolean equals(Object o)
	{
		if(this == o)
		{
			return true;
		}
		else
		{
			if((o==null) || (o.getClass() != this.getClass()))
			{
				return false;
			}
		}

		Artigo a = (Artigo) o;

		return this.nome.equals(a.getNome()) && this.descricao.equals(a.getDesc()) && this.estado.equals(a.getEstado())
		                                     && this.owner.equals(a.getOwner()) && this.number == a.getNumber();
	}

	public String toString()
	{
		StringBuilder s = new StringBuilder();

		s.append("Nome do Artigo para venda: "+this.nome);
		s.append("Descrição do Artigo: "+this.descricao);
		s.append("Estado do Artigo: "+this.estado);
		s.append("Vendedor do Artigo: "+this.owner);
		return s.toString();
	}

}