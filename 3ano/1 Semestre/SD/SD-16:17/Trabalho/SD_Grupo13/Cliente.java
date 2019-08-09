import java.util.*;
import java.lang.*;
import java.net.*;
import java.io.*;

public class Cliente{
	public static void main(String [] args){
		try{
			Socket s=new Socket("localhost",1234);
			EnviaMensagem e=new EnviaMensagem(s);
			RecebeMensagem r=new RecebeMensagem(s);
			e.start();
			r.start();
		}catch(IOException e){
			System.out.println("Servidor Indisponivel ...");
		}
	}
}

class EnviaMensagem extends Thread{
	private Socket s;

	public EnviaMensagem(Socket s){
		this.s=s;
	}

	public void run(){
		try{
			OutputStream os=s.getOutputStream();
			InputStreamReader isr=new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(isr);
			PrintWriter pw=new PrintWriter(os,true);
			String str=null;
			while((str=br.readLine())!=null){
				pw.println(str);
			}
		}catch(IOException e){
			System.out.println("Erro no envio de mensagem...");
		}
	}
}


class RecebeMensagem extends Thread{
		private Socket s;

	public RecebeMensagem(Socket s){
		this.s=s;
	}

	public void run(){
		try{
			InputStream is=s.getInputStream();
			//OutputStream os=s.getOutputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			//PrintWriter pw=new PrintWriter(os,true);
			String str=null;
			while((str=br.readLine())!=null){
				//pw.println(str);
				System.out.println(str);
			}
		}catch(IOException e){
		System.out.println("Erro na recepção da mensagem...");
		}
	}
}
