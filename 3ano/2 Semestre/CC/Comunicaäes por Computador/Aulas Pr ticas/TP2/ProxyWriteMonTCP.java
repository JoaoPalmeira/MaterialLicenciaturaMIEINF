import java.io.*;
import java.net.*;
import java.util.*;

public class ProxyWriteMonTCP extends Thread{

    private Socket socket;
    private String str;
    private static Object obj = new Object();
    private boolean cond=true;

    public ProxyWriteMonTCP(Socket s){
        this.socket = s;
    }

    public void setMensagem(String st){
        str = st;
        cond=false;
        synchronized(obj){
            obj.notify();
        }
    }

    public void run(){
        try{
            boolean wh = true;
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while(wh){
                cond=true;
                synchronized(obj){
                    while(cond)
                        obj.wait();
                    System.out.println("Acordei");
                }
                if(str==null)
                    break;
                out.println(str);
                out.flush();
            }
            System.out.println("Saí do write monitor");
            socket.shutdownOutput();
            socket.close();
    	}
        catch(Exception e){}
    }
}