import java.io.*;
import java.net.*;
import java.util.*;

public class ServerProxy{

	public static InetAddress getMonitor(HashMap<InetAddress,TabelaMonitor> tabela){
        InetAddress ip = null;
        Double status=-1.0;
        Collection<TabelaMonitor> col = tabela.values();
        for(TabelaMonitor t : col){
            if(status==-1){
                status = t.getStatus();
                ip = t.getIpMonitor();
            }
            else{
                if(status>t.getStatus()){
                    status = t.getStatus();
                    ip = t.getIpMonitor();
                }
            }
        }
        return ip;
    }

	public static void main(String[] args) throws Exception{
		HashMap<InetAddress,TabelaMonitor> tabela = new HashMap<InetAddress,TabelaMonitor>();
		HashMap<InetAddress, ProxyUpdateIP> updates = new HashMap<InetAddress, ProxyUpdateIP>();
		ProxyReadUDP pR = new ProxyReadUDP(tabela, updates);
		pR.start();
		ServerSocket srv = new ServerSocket(80);
		while(true){
			Socket cliente = srv.accept();
			ProxyWriteClTCP writeCl = new ProxyWriteClTCP(cliente);
			writeCl.start();
			InetAddress ip = getMonitor(tabela);
			Thread.sleep(10);
			while(ip==null){
				writeCl.setMensagem("Nenhum Monitor Disponível, aguarde");
				synchronized(tabela){
					tabela.wait();
				}
				ip = getMonitor(tabela);
			}
			writeCl.setMensagem("Monitor Disponível, conectado");
			TabelaMonitor t = tabela.get(ip);
			t.incTCPCon();
			Socket monitor = new Socket(ip, 6666);
			ProxyWriteMonTCP writeMon = new ProxyWriteMonTCP(monitor);
			ProxyReadClTCP readCl = new ProxyReadClTCP(cliente, tabela, writeMon);
			ProxyReadMonTCP readMon = new ProxyReadMonTCP(monitor, tabela, writeCl);
			readCl.start();
			readMon.start();
			writeMon.start();
			System.out.println("Criei TCP");
		}
	}

}