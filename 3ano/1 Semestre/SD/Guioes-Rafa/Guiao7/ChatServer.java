
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.*;
import java.io.*;

public class ChatServer {

	private int porto;
	private Hashtable<String, PrintWriter> clients;

	public ChatServer(int po){
		this.clients = new  Hashtable<String, PrintWriter>();
		this.porto = po;
	}

	public void startChatServer(){

		try{
			System.out.println("---> CHAT SERVER <---");

			ServerSocket serverchat = new ServerSocket(this.porto);

			while(true){

				System.out.println("> ChatServer -> ChatServer is runnig, waiting for connection...");

				Socket clientchat = serverchat.accept();

				System.out.println("> ChatServer -> Connection received, create worker to handle connection...");

				Worker w = new Worker(clientchat, this.clients);

				new Thread(w).start();
			}

		} catch(IOException e){
			System.out.println("> ChatServer -> Error on connection..");
		}

		
	}

	public static void main(String args[]){

		//Povoar com clientes

		ChatServer chat = new ChatServer(9999);
		chat.startChatServer();
		
	}
}