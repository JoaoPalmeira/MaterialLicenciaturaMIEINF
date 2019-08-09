import java.io.*;
import java.net.*;
import java.util.*;

public class ClientWriter extends Thread{

	private Socket socket;

	public ClientWriter(Socket cliente){
		this.socket=cliente;
	}

	public void run(){
		try{
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			while(true){
				String s = System.console().readLine();
				if(s==null)
					break;
				out.println(s);
				out.flush();
			}
			socket.shutdownOutput();
			socket.close();
		}
		catch(Exception e){}
		System.out.println("Sai do writer");
	}
}	
