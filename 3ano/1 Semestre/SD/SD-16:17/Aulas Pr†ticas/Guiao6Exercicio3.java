//??
//não deve estar correto
import java.io*;
import java.net.*;

public class Server{

    public static void main(String[] args) throws Exception{
		int port = Integer.parseInt(args[0]);
	    ServerSocket srv = new ServerSocket (port);
        
        while(true){
        	Socket cli = srv.accept();
        	BufferedReader in = new BufferedReader(new InputStreamReader(cli.getInputStream()));
        	PrintWriter out = new PrintWriter(cli.getOutputStream());
        	
        	while(true){
        		String str = in.readLine();
                if(str == null) break;
        		int strint = Integer.parseInt(str);
                int x = 0; int c = 0;
                x += strint;
                c ++;
        		out.flush();
        	}
            out.println(x/c);
        	out.close();
       }
    }

}