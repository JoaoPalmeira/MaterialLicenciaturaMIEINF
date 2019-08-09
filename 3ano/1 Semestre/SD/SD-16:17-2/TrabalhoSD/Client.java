import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
	private Socket clientsocket;
	//private String username;
	private boolean tipo;// para depois ver se e vendedor ou comprador | true -> vendedor && false -> comprador
	private Vector<String> mensagens = new Vector<String>();

	public Client()
	{
		this.clientsocket = new Socket();
	}
	public Client(String host,int pn) throws IOException
	{
		this.clientsocket = new Socket(host,pn);
	}
	public void MenuLogin(PrintWriter pw , BufferedReader l , BufferedReader br, boolean t) throws IOException 
	{
	    try{
		if (t == true)
		{
			while(true){
				  
				System.out.println("MENU");
				System.out.println("A - Adicionar leilão");
				System.out.println("* - Listar os meus leiloes");
				System.out.println("E - Encerra o meu leilão");
				System.out.println("S - Sair");					
										  
				if (br.readLine().equals("A"))
				{
				    mensagens.add("A/");
				    pw.println(mensagens.lastElement());
												  	
				    String desc, desc_nr;

				    System.out.println("Indique uma descrição do item a leiloar");
				    desc = br.readLine();
				    System.out.println("Indique um número do item a leiloar");
				    desc_nr = br.readLine();
								
				    mensagens.add(desc + "/");
				    pw.println(mensagens.lastElement());
				    mensagens.add(desc_nr + "/");
				    pw.println(mensagens.lastElement());
				}
				else if (br.readLine().equals("*"))
				{
				    mensagens.add("*/");
				    pw.println(mensagens.lastElement());
				}
				else if (br.readLine().equals("E"))
				{ 
				   mensagens.add("E/");
				   pw.println(mensagens.lastElement());
							
				   String nr;
				   System.out.println("Indique um número do item que pretende encerrar o leilão");
				   nr = br.readLine();
							
				   mensagens.add(nr + "/");
				   pw.println(mensagens.lastElement());
					  
				}
				else if (br.readLine().equals("S"))
				{ break; }
		     	}
		     }
		     else
		     {
			while(true){
					
				System.out.println("MENU");
				System.out.println("Li -  Licitar num leilão");
				System.out.println("Ve -  Verificação se tenho a licitação mais alta");
				System.out.println("S  -  Sair");
					
				if (br.readLine().equals("LI"))
				{
				   mensagens.add("LI/");
				   pw.println(mensagens.lastElement());
							  
				   String nr_leilao , valor;
				   System.out.println("Digite o nr do leilão que pretende licitar");
				   nr_leilao = br.readLine();
				   mensagens.add(nr_leilao + "/");
				   pw.println(mensagens.lastElement());
				   System.out.println("Digite o valor qe pretende licitar");
				   valor = br.readLine();
				   mensagens.add(valor + "/");
				   pw.println(mensagens.lastElement());
				}
				else if (br.readLine().equals("Ve"))
				{ 
				   mensagens.add("Ve/");
				   pw.println(mensagens.lastElement());
				}
				else if (br.readLine().equals("S"))
				{ break; }
			}
		   }
	      }catch(IOException e) {}	
	}
	public void MenuRegisto(PrintWriter pw , BufferedReader l ,BufferedReader br) throws IOException 
	{
	    try{		
		mensagens.add("R/");
		pw.println(mensagens.lastElement());
		System.out.println("MENU");
		System.out.println("V - Registo como Vendedor");
		System.out.println("C - Registo como Comprador");
		
		if (br.readLine().equals("V"))
		{
			/*REGISTO E ENVIO*/
			mensagens.add("V/");
			pw.println(mensagens.lastElement());
					
			String username,password;
			System.out.println("Digite o seu username");
			username = br.readLine();
			mensagens.add(username + "/");
			pw.println(mensagens.lastElement());
			System.out.println("Digite a sua password");
			password = br.readLine();
			mensagens.add(password + "/");
			pw.println(mensagens.lastElement());
			tipo = true;
			// confirmação do server
			MenuLogin(pw,l,br,tipo);
		}
		else if (br.readLine().equals("C"))
		{
		  	mensagens.add("C/");
			pw.println(mensagens.lastElement());

			String username,password;
			System.out.println("Digite o seu username");
			username = br.readLine();
			mensagens.add(username + "/");
			pw.println(mensagens.lastElement());
			System.out.println("Digite a sua password");
			password = br.readLine();
			mensagens.add(password + "/");
			pw.println(mensagens.lastElement());
			tipo = false;
			// confirmação do server
			MenuLogin(pw,l,br,tipo);		
		}
		else
		{
			System.out.println("Comando digitado inválido!");
			MenuRegisto(pw,l,br);
		}
	  
	  }catch(IOException e){}

	}	
	public void MenuPrincipal() throws IOException
	{
		 BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		 BufferedReader in = new BufferedReader(new InputStreamReader(this.clientsocket.getInputStream()));
		 PrintWriter out = new PrintWriter(this.clientsocket.getOutputStream(),true);
		try{
		 	System.out.println("MENU");
		 	System.out.println("R - Registo");
		 	System.out.println("L - Login");
		
		 	if(stdin.readLine().equals("R"))
		 	{  MenuRegisto(out,in,stdin); }
		 	else if (stdin.readLine().equals("L"))
		 	{  MenuLogin(out,in,stdin,tipo); }
			else
			{
			  System.out.println("Comando digitado inválido!");
			  MenuPrincipal();
			}
		  }finally
	 		{
	 			try
	 			{
		 		  stdin.close();
				  out.close();
				  in.close();
				  clientsocket.close();
	    			}catch(IOException e){};
	 		}
	}
	 
	public void NewClient() throws IOException
	{
		MenuPrincipal();		
	}
	
	public static void main(String args[]) throws IOException
    	{
    		Client c = new Client(args[0],9999);
		c.NewClient();
    	
    	}
}
										
					
				
			
