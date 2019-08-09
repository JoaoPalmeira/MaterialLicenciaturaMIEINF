import java.net.*;
import java.io.*;

public class Client
{
	private Socket clientSock;

	public Client()
	{
		this.clientSock = new Socket();
	}

	public Client(String host,int pn) throws IOException
	{
		this.clientSock = new Socket(host,pn);
	}

	public void myClient()  throws IOException
	{
		try
		{
		  PrintWriter out = new PrintWriter(this.clientSock.getOutputStream(),true);
		  BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSock.getInputStream()));
	      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	      String username;
	      String password;
	      System.out.println("Username: ");
	      if ((username = stdIn.readLine()) != null)
	      out.write(username);
	  	  out.flush();
	  	  System.out.println("password: ");
	  	  if ((password = stdIn.readLine()) != null)
	      System.out.println("Password: " + password);

	  /*
	      while((input = stdIn.readLine()) != null)
	      {
	   	    
	   	    out.write(input);
	   	    out.flush();
	      }*/
	}finally
	 {
	 	try
	 	{
		 clientSock.shutdownInput();
		 clientSock.shutdownOutput();
		 clientSock.close();
	    }catch(IOException e){};
	 }
	}
	
public static void main(String args[]) throws IOException
    {
    	Client c = new Client(args[0],9999);
	c.myClient();
    	
    }
}