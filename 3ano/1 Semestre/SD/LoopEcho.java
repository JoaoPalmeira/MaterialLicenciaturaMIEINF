import java.io.*;
import java.net.*;

public class LoopEcho{

	public static void main(String args[]) throws IOException{

		ServerSocket ss = new ServerSocket(9999);

		Socket cs = ss.accept();

		PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
		BufferedReader in = new BufferedReader( new ImputStreamReader(cs.getImputStream()));

		Stream current;
		while ((current = in.readLine()) != null){

			//assert current != null;
			out.println(current);
			System.out.println("echo: " + current)
		}

		in.close()
		out.close();
		cs.close();
		ss.close();
	}
}