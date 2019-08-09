import java.net.*;
import java.io.*;

public class Server
{
	private ServerSocket sSock;
	private int portNumber;
    
    public Server(int pn) throws IOException
    {
      this.portNumber=pn;//numero de porta da ligação
   	  this.sSock = new ServerSocket(pn);//criar o socket servidor

    }

    public void myServer() throws IOException
    {
    	Socket clientSock = new Socket();//criamos o socket cliente
    	try
    	{
    		clientSock = sSock.accept();//vamos estar a espera de sermos contactados por um socket cliente

   //os readers and writers
    		
            //queremos o output do socket
    		PrintWriter out = new PrintWriter(clientSock.getOutputStream(),true);

    		//queremos o input do socket
    		BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
    	
    	//escrever o que o cliente nos envia
    	while(true)
    	{
    		//ler do socket
    		String input = in.readLine();

            //para ver se temos de acabar a conversação
    		if(input == null || input.equals("."))
    		{
    			break;
    		}

    		//caso contrario vamos escrevendo o que vamos lendo do socket
    		out.write(input);
    		out.flush();
    	}

    	}finally{
             try
             {
             	//no final devemos fechar o canal de comunicação
               clientSock.close();
             }catch(IOException e){};
    	}

    }

    public static void main(String args[]) throws IOException
    {

    	System.out.println("O servidor vai começar a correr.");
    	Server s = new Server(9999);
    	s.myServer();
    	//criar o run
    }
}