import java.net.*;
import java.io.*;

public class ServerSum
{
	private int sum,med,cont;

	
	public static void main (String[] args) throws InterruptedException e
	{
		int sum = 0;
		int med = 0;
		int cont = 0; 
		ServerSocket ss = new ServerSocket (9999);
		Socket cs  = new Socket();
		Server cs = null;
		cs = ss.accept();		
		BufferedReader fromClient = new BufferedReader (new InputStreamReader(cs.InputStream()));
		PrintWriter toClient = new PrintWriterReader ( cs.getOutputStream(),true);

		try{
			
			while (true){ // servidor sempre a correr (no background)	
			
				try{
					String input = fromClient.readLine();
				
					if (input != null) // lido nr
					{
				  		sum += Integer.parseInt(input);
				  		cont++;
				  		toClient.write(sum);
				  		toClient.flush();
					}
					med = sum/cont;
					toClient.write(med);
					toClient.flush();
					sum = 0;
					med = =0;
				}
				catch(InterruptedException e)
				{}
			}
		}
		finally{
			ss.close();
			fromClient.close();
			toClient.close();
		}

	}
}		
			
/* As mudanças para thread seria criar uma class aparte (ex: TrataClient) que implementa Runnable e exceções e o block try ser usado no run().
   As mudanças no main seria lançar a thread ex: [Thread t = new Thread(new TrataClient(Socket cliente))]; e fazer start.
  ex: main
		ServerSocket s = new ServerSocket(9999);
		Socket c = new Socket();
		
		try{
			c = s.accept();
			Thread t = new Thread(new TrataClient(c));
			t.start();
		   }
		  catch ...
*/

        
