import java.io*;
import java.net.*;

public class Server{

    public static void main(String[] args) throws Exception{
        int port = Integer.parseInt(args[0]);
        ServerSocket srv = new ServerSocket (port);
        Register r = new Register();
        while(true){
            Socket cli = srv.accept();
            new ClientHandler(cli, r).start();
        }
    }

}

class ClientHandler extends Thread{
    
    Socket cli;
    Register r;
    ClientHandler(Socket cli, Register r){this.cli = cli; this.r = r;}
    
    public void run(){
        BufferedReader in = new BufferedReader(new InputStreamReader(cli.getInputStream()));
        PrintWriter out = new PrintWriter(cli.getOutputStream());
        int soma = 0;
        int n = 0;
        while(true){
                String str = in.readLine();
                if(str == null) break;
                int i = Integer.parseInt(str);
                r.soma(i);
                soma += i;
                n += 1;
                out.println(r.value());
                out.flush();
        }
        out.println(soma/n);
        out.close();
    }
}

class Register{
    private int val;
    synchronized void soma(int v){val += v;}
    synchronized int value(){return val;}
}