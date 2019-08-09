import java.io.*;
import java.net.*;
import java.util.*;

public class ClientReader extends Thread{

	private Socket socket;

	public ClientReader(Socket cliente){
		this.socket=cliente;
	}

	public void run(){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str;
			while(true){
				str = in.readLine();
				if(str==null)
					break;
				System.out.println(str);
			}
			socket.shutdownOutput();
			socket.close();
		}
		catch(Exception e){}
		System.out.println("A conexão fechou.");
	}
}	
