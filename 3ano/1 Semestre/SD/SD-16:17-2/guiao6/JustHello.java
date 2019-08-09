import java.net.*;
import java.io.*;

public class JustHello
{
	public static void main(String args[]) throws IOException
	{
	    ServerSocket  ss = new ServerSocket(9999);

	    Socket cs = ss.accept();
        
        //out with autoflush for println
	    PrintWriter out = new PrintWriter(cs.getOutputStream(),true);

	    String hello = "Hello World";
	    out.println(hello);

	    out.close();
	    cs.close();
	    ss.close();
	}
}