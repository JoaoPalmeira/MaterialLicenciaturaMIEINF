import java.io.*;
import java.net.*;
import java.util.*;

public class ProxyReadMonTCP extends Thread{

    private Socket socket;
    private Map<InetAddress, TabelaMonitor> tabela;
    private ProxyWriteClTCP write;

    public ProxyReadMonTCP(Socket s, Map<InetAddress, TabelaMonitor> t, ProxyWriteClTCP w){
        this.socket = s;
        this.tabela = t;
        this.write = w;
    }

    public void run(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                String str = in.readLine();
                if(str == null)
                    break;
                System.out.println(socket.getInetAddress() + " : " + str);
                write.setMensagem(str);
            }
            write.setMensagem(null);
            tabela.remove(socket.getInetAddress());
            socket.shutdownOutput();
            socket.close();
            System.out.println("Monitor Fechou");
    	}
        catch(Exception e){}
    }
}