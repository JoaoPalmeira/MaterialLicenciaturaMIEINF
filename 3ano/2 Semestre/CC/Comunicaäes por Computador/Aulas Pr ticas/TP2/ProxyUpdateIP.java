import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.Timestamp;

public class ProxyUpdateIP extends Thread{

	private Map<InetAddress,TabelaMonitor> tabela;
	private InetAddress ipAddress;
	private DatagramSocket serverSocket;
	private byte[] packet;
	private static Object obj = new Object();
	private PacketLossTime task;
	private Timer timer;
	private boolean cond[] = new boolean[1];
	private byte nextPacket;
	private int duplicados;

	public ProxyUpdateIP(Map<InetAddress, TabelaMonitor> t, InetAddress i, DatagramSocket s){
      	this.tabela = t;
        this.ipAddress = i;
        this.serverSocket = s;
        packet = new byte[1024];
        cond[0] = true;
   	}

   	public void run(){
      	try{
      		while( tabela.containsKey(ipAddress) && checkStatus()){
      			pooling();
      			Thread.sleep(5000);
      		}
      	}
      	catch(Exception e){
      		e.printStackTrace();
      	}
      	tabela.remove(ipAddress);
 	}

 	public boolean checkStatus(){
 		Timestamp now = new Timestamp(System.currentTimeMillis());
 		TabelaMonitor t;
 		synchronized(tabela){ t = tabela.get(ipAddress); }
 		synchronized(t){
 			if(now.getTime()-t.getTime().getTime()>20000)
 				return false;
 		}
      	return true;
 	}

 	public synchronized void pooling(){
 		byte[] sendData = new byte[2];
 		Timestamp now, after;
 		int packetCount=0, rtt=0, packetLoss=0;
 		int tentativas = 0;
 		TabelaMonitor t;
 		duplicados=0;
 		try{
 			for(nextPacket=0;nextPacket<2;){
 				cond[0] = true;
	      		sendData[0] = 1;
	      		sendData[1] = nextPacket;
	      		DatagramPacket sendPacket = new DatagramPacket(sendData, 2, ipAddress, 5555);
	      		packetCount++;
		      	synchronized(obj){
		      		task = new PacketLossTime(obj, cond);
		      		timer = new Timer();
		      		now = new Timestamp(System.currentTimeMillis());
		      		serverSocket.send(sendPacket);
		      		timer.schedule(task, 50);
		      		while(cond[0])	
		      			obj.wait();
		      		if(packet[1]==nextPacket){
		      			after = new Timestamp(System.currentTimeMillis());
		      			rtt += after.getTime() - now.getTime();
		      			tentativas=0;
		      			nextPacket++;
		      		}
		      		else{
		      			packetLoss++;
		      			tentativas++;
		      		}
		      	}
		      	if(tentativas==4)
		      			break;
		    }
		    synchronized(tabela){ t = tabela.get(ipAddress); }
		    synchronized(t){
		    	t.addPacketCount(packetCount);
		    	t.addRtt(rtt);
		    	t.addPacketLoss(packetLoss);
		    	t.addDuplicados(duplicados);
			}
	  	}
      	catch(Exception e){
      		e.printStackTrace();
      	}
 	}

 	public void setPacket(byte[] d){
 		synchronized(obj){
 			if(nextPacket == d[1]){
 				this.packet = d;
 				timer.cancel();
 				timer.purge();
 				cond[0] = false;
 				obj.notify();
 			}
 			else{
 				duplicados++;
 			}
 		}
 	}

}