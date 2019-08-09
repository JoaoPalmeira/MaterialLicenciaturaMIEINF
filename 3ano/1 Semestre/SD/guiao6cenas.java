import java.io.*;
import java.net.*;

public class JustHello{

	public static void main(String args[]) throws IOException{

		ServerSocket ss = new ServerSocket(9999);

		Socket cs = ss.accept();

		// out with autoflush for printlns
		PrintWriter out = new PrintWriter(cs.getOutputStream(), true);

		String hello="Hello World";
		out.println(hello);

		out.close();
		cs.close();
		ss.close();
	}
}