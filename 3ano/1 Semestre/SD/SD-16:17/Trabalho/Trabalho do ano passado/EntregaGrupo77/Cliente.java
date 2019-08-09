import java.io.*;
import java.net.*;
import java.util.*;

class Client {

	public static void main(String[] args) {
		try {
			Socket cs = new Socket("localhost",6063);
			new ClienteSender(cs).start();
			new ClienteReceiver(cs).start();
		}
		catch (Exception e) {
			System.out.println("Servidor indisponivel, tente mais tarde...");
		}
	}
}

class ClienteSender extends Thread {
	private Socket cs;

	public ClienteSender (Socket cs) {
		this.cs = cs;
	}

	public void run() {
		try {
			PrintWriter pw = new PrintWriter(cs.getOutputStream(),true);
			InputStreamReader sysin = new InputStreamReader(System.in);
			BufferedReader bf = new BufferedReader(sysin);
			String tmp;
			while((tmp = bf.readLine())!=null) {
				if (tmp.equals("exit")) break;
				pw.println(tmp);
			}
			cs.shutdownOutput();
		}
		catch (Exception e) {
			System.out.println (e.toString());
		}
	}
}

class ClienteReceiver extends Thread {
	private Socket cs;

	public ClienteReceiver (Socket cs) {
		this.cs = cs;
	}

	public void run() {
		try {
			InputStreamReader ir = new InputStreamReader(cs.getInputStream());
			BufferedReader bf = new BufferedReader(ir);
			String tmp;
			while((tmp = bf.readLine())!=null) {
				System.out.println("Server: "+tmp);
			}
		}
		catch (Exception e) {
			System.out.println (e.toString());
		}
	}
}
