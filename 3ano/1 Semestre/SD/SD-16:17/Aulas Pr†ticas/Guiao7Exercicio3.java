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
            new ClientChat(cli).start();
        }
    }

}

class ClientChat extends Thread{
    
    Socket cli;
    ClientChat(Socket cli){this.cli = cli;}
    
    public void chat(){
        BufferedReader in = new BufferedReader(new InputStreamReader(cli.getInputStream()));
        PrintWriter out = new PrintWriter(cli.getOutputStream());
        while(true){
                String str = in.readLine();
                if(str == null) break;
                out.println(str);
                out.flush();
        }
        out.close();
    }
}
