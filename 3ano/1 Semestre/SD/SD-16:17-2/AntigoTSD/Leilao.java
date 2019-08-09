
public class Leilao
{
	class ItemLeilao
	{
		String descricao;
		double preco_atual; // preço que está a ganhar

		public ItemLeilao ()
		{
			this.descricao = null;
			this.preco_atual = 0.0;
		}

		public ItemLeilao (String descricao , double preco_atual)
		{
			this.descricao = descricao;
			this.preco_atual = preco_atual;
		}
		public synchronized double getPreco ()
		{
		return this.preco_atual;
		}
		public synchronized void setPreco (double preco)
		{
			this.preco_atual = preco;
		}
		public String getDescricao()
		{
			return this.descricao;
		}
	}

	int nr;
	boolean status; // true para aberto e false para fechado
	ItemLeilao item; // item do leilao
	String nome; // nome de quem vai á frente no leilao deste item

	// método inicia leilao
	public Leilao ()
	{
		nr = 0; //indefinido ainda , cliente primeiro inicia leilao e dps o servidor dá um nr
		status = true;
		item = new ItemLeilao();
	}
	public Leilao (int nr, boolean status ,ItemLeilao novo_item,String nome) 
	{
		this.nr = nr;
		this.status = status;
		this.item = new ItemLeilao (novo_item.getDescricao(),novo_item.getPreco());
		this.nome = nome;
	}
	//////////// Métodos aplicados pelo Servidor (Pela thread potencialmente) ///////////
	public int getNr ()
	{ return this.nr;}

	public void setNr (int nr)
	{ this.nr = nr;}

	////////////////////////////

	public String getNome ()
	{ return this.nome;}

	public boolean getStatus()
	{ return this.status;}

	public void setStatus(boolean status)
	{  this.status = status; }

	public ItemLeilao getItem()
	{
		return item;
	}
	
	public void iniciaLeilao (String descricao)
	{
		status = true;
		item.descricao = descricao;
	}
	public synchronized void LicitaLeilao (int nrleilao, double preco, String nome)
	{
		if (nr == nrleilao )
		{
			if (preco > this.item.getPreco())
			{
				this.item.setPreco(preco);
				this.nome = nome;
			}
		}
	}
	public void encerraLeilao (int nr_leilao)
	{
		if (nr == nr_leilao)
		{ this.status = false;}
	}

}
