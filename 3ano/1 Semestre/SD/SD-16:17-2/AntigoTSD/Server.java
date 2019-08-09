import java.net.*;
import java.io.*;
import java.util.*;



/*
class Session 
{
	List<String> session = new ArrayList<String>();	

	public synchronized void add(String s)
	{
		session.add(s);

	}
	public List<String> getList()
	{ return session; }
}
*/
public class Server
{
	private static HashSet<String> utilizadores = new HashSet<String>();	

	public static void main(String args[]) throws IOException 
	{
		ServerSocket srv = new ServerSocket(9999);
		Socket cs = null;
	
		while (true)
		{
			cs = srv.accept();
			Thread tr = new Thread(new ReadClient(cs));
			//Thread tw = new Thread (new TreatClientWritee(cs,s));
			tr.start();
			//tw.start();
		}
  	}

private static class ReadClient implements Runnable
	{
		Socket cs;
		//Session s;
		//int check = 0;
	
		public ReadClient(Socket cs) 
		{
			this.cs = cs;
			//this.s = s;
		}
		public void run() {
	
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader( cs.getInputStream()));

			String current;

			System.out.println("New connection");
		
			while((current = in.readLine()) != null)
			{
				synchronized(utilizadores)
				{
					
                    if (!utilizadores.contains(current)) {
                            utilizadores.add(current);
                            System.out.println("Client " + current + " has sucessfully registered");
                    }
                    else
                    System.out.println("Client already registered . Authentication failed");
                 }

				/*if (check == 1)
				{System.out.println("Client already registered . Authentication failed");}
				else
				{
					System.out.println("Client " + current + " has sucessfully registered");
					s.add(current);
			
			}*/

		    }
		
		in.close();
		cs.close();
		System.out.println("Connection closed");

		}catch(IOException e)
		{}
	}
  }
}

/*
public class Server
{
	private static HashSet<String> utilizadores = new HashSet<String>();	

	public static void main(String args[]) throws IOException 
	{
		ServerSocket srv = new ServerSocket(9999);
		Socket cs = null;
	
	
	while (true)
	{
		cs = srv.accept();
		Thread tr = new Thread(new ReadClient(cs));
		//Thread tw = new Thread (new TreatClientWritee(cs,s));
		tr.start();
		//tw.start();
	}
  }
}*/