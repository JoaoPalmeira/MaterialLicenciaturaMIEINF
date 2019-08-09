import java.net.*;
import java.io.*;
import java.util.*;

public class exe3 {

	final List<Client_Thread> clients = new ArrayList<>();
	
	public static void main (String args[]) throws Exception {

 
	ServerSocket server = new ServerSocket (9898);
	
	try {
		while(true){
		
		Client_Thread client = new Client_Thread(server.accept());
		client.start();
		clients.add(client);
		}
	}finally{
		server.close();
	}
	}
private static class Client_Thread extends Thread  {

	private Socket socket;
	

	public Client_Thread(Socket s){

		this.socket = s;
		
	}

	public void run() {
            
			 try {

               // out -> mensagens para cliente , in - mensagens para servidor
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            
              
                while (true) {
                	// ler a string
                    String input = in.readLine();
		
		    if (input == null || input.equals(".")) {
                        break;
                    }
                    System.out.println(input);
		    
		   for (Client_Thread client : clientes)
		    { client.out.println(input); }
		    
		}
            } catch (IOException e) {}
	    finally{
		//in.close();		
		//out.close();
		socket.close();
	    }
	}
    }	
}
