
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientReceive implements Runnable{

	private Socket client;
	private BufferedReader in;

	public ClientReceive(Socket c){
		this.client = c;
	}

	public void run(){
		String msg;
		try{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while((msg = in.readLine()) != null){
				System.out.println(msg);
			}
		} catch (SocketException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}