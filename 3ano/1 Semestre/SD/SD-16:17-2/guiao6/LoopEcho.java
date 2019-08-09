import java.net.*;
import java.io.*;

public class LoopEcho
{
	public static void main(String args[]) throws IOException
	{
	  ServerSocket ss = new ServerSocket(9999);

	  Socket cs = null;
	  cs = ss.accept();

	  PrintWriter out = new PrintWriter(cs.getOutputStream(),true);
	  BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));

      String current;
     
      while((current = in.readLine()) != null)
      {
        out.print(current);
        System.out.println("echo: "+ current);
      }

      in.close();
      out.close();
      cs.close();
      ss.close();
	}
}