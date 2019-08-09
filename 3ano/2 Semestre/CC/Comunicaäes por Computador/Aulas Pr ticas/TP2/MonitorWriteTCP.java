import java.io.*;
import java.net.*;
import java.util.*;

public class MonitorWriteTCP extends Thread{

	private Socket socket;
	private String str;
	private static Object obj = new Object();
    private boolean cond=true;

	public MonitorWriteTCP(Socket monitor){
		this.socket=monitor;
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
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			while(true){
				cond=true;
                synchronized(obj){
                    while(cond)
                        obj.wait();
                }
				if(str==null)
					break;
				out.println(str);
				out.flush();
			}
			socket.shutdownOutput();
			socket.close();
		}
		catch(Exception e){}
		System.out.println("Sai do writer");
	}
}	
