import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.*;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.*;

public class ClientChat{

	private Socket clientchat;
	private int porto;
	private String hostname;
	private BufferedReader in;
	private PrintWriter out;
	private String nickName;

	public ClientChat(String host, int po){
		this.hostname = host;
		this.porto = po;
	}

	public void startClientChat(){

		try{
			System.out.println("--> CLIENT CHAT <---");

			System.out.println("> ClientChat -> Waiting for connection...");

			// criar conexao conexao com o servidor
			clientchat = new Socket(this.hostname, this.porto);

			System.out.println("> ClientChat -> Connect sussesfuly...");

			// criar os canais com o server
			in = new BufferedReader(new InputStreamReader(clientchat.getInputStream()));
			out = new PrintWriter(clientchat.getOutputStream(), true);
			BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("> ClientChat -> Please chosse a NickName:");
			System.out.print(">> ");
			nickName = systemIn.readLine();

			out.println(nickName);

			String response;
			while((response = in.readLine()) != null && !response.equals("ok")){
				System.out.println("> Nickname already registered! Please choose another one:");
				nickName = systemIn.readLine();
				out.println(nickName);
			}

			System.out.println("> ClientChat -> NickName sussesfuly saved...");

			// criar uma thread com o client para receber as mensagens
			Thread receive = new Thread(new ClientReceive(clientchat));
			receive.start();

			String userInput;
			while((userInput = systemIn.readLine()) != null && !userInput.equals("quit")){
				out.println(userInput);
			}

			// fechar os canais e socket

			systemIn.close();
			clientchat.shutdownOutput();
			clientchat.shutdownInput();
			clientchat.close();


		}  catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]){
		ClientChat clientchat = new ClientChat("127.0.0.1",9999);
		clientchat.startClientChat();
	}
}