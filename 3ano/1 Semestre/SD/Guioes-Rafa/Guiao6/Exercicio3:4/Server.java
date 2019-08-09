import java.io.*;
import java.net.*;

public class Server{

	private ServerSocket servsocket;
	private int porto;

	public Server(int porto){
		this.porto = porto;
	}
	
	public void startServer(){

		try{
			System.out.println("---> SERVER <---");

			// Criar a sockect com o porto defenido
			this.servsocket = new ServerSocket(this.porto);

			//while(true){

				System.out.println("> Server is starting, waiting for connection...");

				// Dar accept da socket (Receber o cliente);
				Socket socket = this.servsocket.accept();

				System.out.println("Connection received!");

				// Criar os canais de comunicação (leitura/escrita) socket

				// Leitura
				BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));

				// Escrita
				BufferedWriter out = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));

				int sum = 0;

				String line;

				while((line = in.readLine()) != null){
					System.out.println("\n> Received from Client the value: " +line); // Imprimir o que o clinete escreveu
					sum += Integer.parseInt(line);

					//Escrever no canal
					out.write(String.valueOf(sum));

					out.newLine();
					out.flush();

					System.out.println("\n> Reply: "+sum);
				}

				// Fechar as sockets e os canis;
				socket.shutdownInput();
				socket.shutdownOutput();
				socket.close();

			//}

		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws IOException{

		Server s = new Server(9999);
		s.startServer();
	}
}