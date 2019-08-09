import java.net.*;
import java.io.*;

public class EchoClient
{
	public static void main(String args[]) throws IOException,UnknownException
	{
         Socket cs = new Socket("127.0.0.1",9999);

         PrintWriter out = new PrintWriter(cs.getOutputStream(),true);
	     BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));

	     String current;
         BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

         while((current = stdin.readLine())!= null)
         {
         	out.print(current);
         	System.out.println("echo: "+current);
         }

         in.close();
         stdin.close();
         out.close();
         cs.close();

	}
}