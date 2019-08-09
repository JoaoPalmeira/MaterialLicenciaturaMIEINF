import java.io.*;
import java.net.*;

class Client{
	
	private String hostName;
	private int porto;
	private Socket socket;

	public Client(String host, int porto){
		this.hostName = host;
		this.porto = porto;
	}

	public void clientStart(){

		try{
			System.out.println("---> CLIENT <---");
			System.out.println("> Connecting to server...");

			// Criar a ligação ao servidor
			this.socket = new Socket(this.hostName, this.porto);

			// Criar canais de ligação com o servidor (leitura / escrita)

			// Leitura 
			BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

			// Escrita
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));

			// Criar canal de leitura a partir do stdin
			BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));

			String userInput;  // String para ler input do utilizador
			String response;   // String para ler resposta do servidor

			System.out.print("$ ");

			while(((userInput = systemIn.readLine()) != null) && (!userInput.equals("quit"))){

				try{
					int value = Integer.valueOf(userInput);
					out.write(userInput);
					out.newLine();
					out.flush();

					response = in.readLine();

				} catch(NumberFormatException e){
					System.out.println("[ERROR] "+userInput+" is not an integer. Please insert one.");
				}
				finally{
					System.out.print("\n$ ");	
				}
			}

			//receber média dos valores enviados
			socket.shutdownOutput();
			//response = in.readLine();
			//System.out.println("> Closed connection. Received from server the average: " + response);	
			
			//fechar sockets
			socket.shutdownInput();
			socket.close();


		} catch (UnknownHostException e) {
			System.out.println("ERRO: Server doesn't exist!");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]){

		Client c = new Client("127.0.0.1",9999);

		c.clientStart();
	}

}






