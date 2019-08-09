public class Utilizador
{
	private String username;
	private String password;
	private boolean vendedor;//false se for comprador

	public Utilizador()
	{
		this.username=" ";
		this.password=" ";
		this.vendedor = false;
	}

	public Utilizador(String u,String p,boolean b)
	{
		this.username=u;
		this.password=p;
		this.vendedor=b;
	}

	public Utilizador(Utilizador u)
	{
		this.username=u.getUsername();
		this.password=u.getPassword();
		this.vendedor=u.getVendedor();
	}
    
    public String getUsername()
    {
    	return this.username;
    }

    public String getPassword()
    {
    	return this.password;
    }

    public boolean getVendedor()
    {
    	return this.vendedor;
    }

    public void setUsername(String u)
    {
    	this.username=u;
    }

    public void setPassword(String p)
    {
    	this.password=p;
    }

    public void setVendedor(boolean b)
    {
    	this.vendedor=b;
    }

    public Utilizador clone()
    {
    	return new Utilizador(this);
    }

    public boolean equals(Object o)
    {
    	if(this == o)
    	{
    		return true;
    	}
    	else
    	{
    		if((o == null) || (o.getClass() != this.getClass()))
    		{
    			return false;
    		}
    	}

    	Utilizador u = (Utilizador) o;

    	return this.username.equals(u.getUsername()) && this.password.equals(u.getPassword()) && this.vendedor == u.getVendedor();
    }
    
    public String toString()
    {
    	StringBuilder s = new StringBuilder();

    	s.append("Bem-Vindo "+this.username);
    	return s.toString();
    }
}