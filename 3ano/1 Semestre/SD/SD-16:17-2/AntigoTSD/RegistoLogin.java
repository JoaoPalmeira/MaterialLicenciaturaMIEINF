import java.util.*;

public class RegistoLogin
{
	
	class Login{

		String username;
		String password;
	
		public Login()
		{
			username = null;
			password = null;
		}
		public void newLogin(String username,String password)
		{
			this.username = username;
			this.password = password;
		}
	}
	
	static Vector<Login> registo;
	
	public RegistoLogin()
	{
		registo = new Vector<Login>();
	}
	public static synchronized void adicionaLogin(Login l)
	{
		registo.add(l);
	}
	public static synchronized void removeLogin(Login l)
	{
		registo.remove(l);
	}
}

	
	 
