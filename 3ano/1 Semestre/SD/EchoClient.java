import java.io.*;
import java.net.*;

public class EchoClient{

	public static void main(String args[]){

		Socket cs = new Socket("127.0.0.1", 9999);

		PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
		BufferedReader in = new BufferedReader( new ImputStreamReader(cs.getImputStream()));

		Stream current;
		BufferedReader sin = new BufferedReader( new ImputStreamReader(System.in));
		while ((current = in.readLine()) != null){

			//assert current != null;
			out.println(current);
			System.out.println("Get echo: " + in.readLine())
		}

		in.close()
		sin.close();
		out.close();
		cs.close();
	}
}