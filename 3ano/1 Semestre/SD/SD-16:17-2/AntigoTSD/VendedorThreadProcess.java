import java.io.*;
import java.net.*;
import java.util.*;

 // antes de entrar para esta thread, precisamos de saber se estamos perante o vendedor.
public class VendedorThreadProcess extends Thread
{
	Socket s = null;
	LeilaoList leilaolista = new LeilaoList();
	RegistoLogin r = new RegistoLogin(); //isto vai estar no servidor.java
	
	public VendedorThreadProcess(Socket s)
	{
		this.s = s;
	}
	public void run()
	{
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			
			Login l = new Login();
			
			out.println("Digite o seu username");
			String user = in.readLine();
			out.println("Digite a sua password");
			String pass = in.readLine();
			l.newLogin(user,pass);

			if (r.registo.contains(l) == false)
			{
			  r.adicionaLogin(l);
			  String login = "Sucesso no login";
			  out.println(login);
				
				while(true)
				{
				
				  String texto = in.readLine(); // todo os comandos feitos pelo cliente(neste caso vendedor)
				
				  if (texto.contains("*")) // listagem
				  {
					String txt;
				   	txt = leilaolista.imprimeLista();
					out.println("Aqui está a lista: " + txt);
				  }
					/* Falta ver o inicio do leilao e o seu encerramento */

				 else {
					System.out.println("Comando não reconhecido");
					out.println("Comando não reconhecido");
				}
			   }	
			}
			else
			{
				String login = "Login Failed";
				System.out.println(login);
				out.println(login);
			}
				// Fecha conecções
				in.close();
				out.close();
				s.close();
		} catch (Exception e) {}
      }
}

