import java.net.*;
import java.io.*;

/*Servidor qe recebe mensagens de múltiplos clients */

public class MsgServer {

	public static Integer client_number;

	public static void main (String args[]) throws Exception {

	client_number = 0;	
	ServerSocket server = new ServerSocket (9898);
	
	try {
		while(true){
		
		new Client_Thread(server.accept()).start();
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
                    synchronized (client_number)
                    {client_number++;}
                    
                    System.out.println("Received: "+ input + " from client nr# " + client_number);
                }
            } catch (IOException e) {
                log("Error handling client# " + client_number + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with client# " + client_number + " closed");
            }
  }

 		// Método que transmite a mensagens 
        private void log(String message) {
            System.out.println(message);
        }
    }
}
	