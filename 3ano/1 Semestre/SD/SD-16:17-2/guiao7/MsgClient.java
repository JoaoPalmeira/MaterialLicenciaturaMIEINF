import java.net.*;
import java.io.*;

public class MsgClient
{
	private Socket clientSock;

	public MsgClient()
	{
		this.clientSock = new Socket();
	}

	public MsgClient(String host,int pn) throws IOException
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
	      String input;

	      while((input = stdIn.readLine()) != null)
	      {
	   	    out.write(input);
	   	    out.flush();
	      }
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
    	MsgClient c = new MsgClient(args[0],9898);
	c.myClient();
    	
    }
}
