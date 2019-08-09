
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.*;
import java.io.*;


public class Worker implements Runnable {
	
	private String nickname;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Hashtable<String, PrintWriter> clients;


	public Worker(Socket socket, Hashtable<String, PrintWriter> cl) throws IOException {
		this.socket = socket;
		this.clients = cl;
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out =  new PrintWriter(socket.getOutputStream(), true);
	}


	public synchronized boolean registerClient(String nick, PrintWriter writer){
		if(!clients.containsKey(nick)){
			clients.put(nick, writer);
			return true;
		}

		return false;
	}

	public synchronized void deRegisterClient(String nick){
		clients.remove(nick);
	}

	public synchronized void multicast(String userSender, String msg){
		msg = userSender + ": " + msg;
		for(String user : clients.keySet()) {
			if(!user.equals(userSender)){
				PrintWriter bw = clients.get(user);
				bw.println(msg);	
			}
		}
	}

	public void run() {
		try {
			//registar nick
			nickname = in.readLine();
			while(!registerClient(nickname, out)){
				System.out.println("> Nickname "+nickname+" already registered! Ask client for a different one");
				out.println("false");
				nickname = in.readLine();
			}

			System.out.println("> Nickname "+nickname+" successfully registered!");
			out.println("ok");

			//receber mensagens do utilizador e difundir pelos restantes utilizadores
			String msg = null;
			while((msg = in.readLine()) != null) {
				multicast(nickname, msg); 
			}

			System.out.println("Client: " + nickname + " disconnected.");

			//fechar sockets
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			deRegisterClient(nickname);			
		}

	}
}