import java.util.*;
import java.util.concurrent.locks.*;
import java.io.*;
import java.net.*;

class ProcessaComprador implements Runnable
{
	Socket cs;

	ProcessaComprador(Socket cs)
	{ this.cs = cs; }

	/*
	  public void run()
	  {
		
		try{
			  BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                	  PrintWriter out = new PrintWriter(cs.getOutputStream(), true);

			   	1- PARTIR O QUE VEM DE IN EM STRING[]
				2 - Ver se a opção em STRING[] foi de vendedor (se não for vai embora)
				2- PROCESSAR O HASHmap users e ver se o utilizador está lá (se tiver vai embora e manda msg ao cliente 

				// if (registo foi sucesso)

			  	try{
					-Processamento da string (while STRING[X] != null ou uma coisa assim com o x++)
						-- se for licitar
						-- vai ao hashmap e adiciona ( usar lock.lock() antes da operação e lock.unlock(); depois da operação
						-- fazer noLeiloes.await() -> espera até que o vendedor encerre o leilão
						-- dps do await() o leilão acabou e o vendedor já fez noLeiloes.signalAll() a dizer que no leilão em questão o comprador ganhador e o dinheiro do item qual foi 
						--- se for verificar	
						--- vai ao hasmap e vê quem tá a aganhar
				   }
				   catch(InterruptedException e)
				   {}
			    else
			    { 
				out.println("Insucesso no registo");
		   }
		   finally{ 
			    in.close();
			    out.close();
			    cs.close();
			  
		   }
	} */

class ProcessaVendedor implements Runnable
{
	Socket cs;

	ProcessaVendedor(Socket cs)
	{ this.cs=cs; }
	/*
	public void run()
	{
		lock.lock();
		try{
			   BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                	   PrintWriter out = new PrintWriter(cs.getOutputStream(), true);

			    	1- PARTIR O QUE VEM DE IN EM STRING[]
				2 - Ver se a opção em STRING[] foi de vendedor (se não for vai embora)
				2- PROCESSAR O HASHmap users e ver se o utilizador está lá (se tiver vai embora e manda msg ao cliente 
			
			  // if (registo foi sucesso)

			  	try{
					//  operações mediante STRING[]  (processar-la em while , ver se existe algo na string )
					dentro do while usar no inicio lock.lock(); e no fim lock.unlock();
				   }
				   catch(InterruptedException e)
				   {}
			    else
			    { 
				out.println("Insucesso no registo");
		   }
		   finally{ 
			    in.close();
			    out.close();
			    cs.close();
		   }
	} */
}

public class Server
{
	private HashMap<int,Leilao> leiloes;
	private HashMap<String,Utilizador> users;
    
    	private final Lock lock = new ReentrantLock();
    	private final Condition noLeiloes = lock.newCondition();

	public static void main (String args[]) throws IOException 
	{
	
		ServerSocket s = new ServerSocket (9999);
		Socket cs = null;
	
		try{
			while(true){	
		
				cs = ss.accept();
				Thread vendedor  = new Thread(new ProcessaVendedor(cs));
				Thread comprador = new Thread(new ProcessaComprador(cs));
				vendedor.start();
				comprador.start();	
			
			}
	   	    }catch{}
	}
}
	
