import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.Timestamp;

public class ProxyReadUDP extends Thread{

   private Map<InetAddress, TabelaMonitor> tabela;
   private Map<InetAddress, ProxyUpdateIP> updates;
   
   public ProxyReadUDP(Map<InetAddress, TabelaMonitor> t, Map<InetAddress, ProxyUpdateIP> u){
      this.tabela = t;
      this.updates = u;
   }

   public void run(){
      try{
         DatagramSocket serverSocket = new DatagramSocket(5555);
         byte[] receiveData = new byte[1024];
         while(true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData, 1024);
            serverSocket.receive(receivePacket);
            InetAddress ipAddress = receivePacket.getAddress();
            switch(receiveData[0]){
               case 0:
                  System.out.println("Periódico: " + ipAddress);
                  if(!tabela.containsKey(ipAddress)){
                     synchronized(tabela){
                        TabelaMonitor t = new TabelaMonitor(ipAddress);
                        tabela.put(ipAddress,t);
                        tabela.notify();
                     }
                     synchronized(updates){
                        ProxyUpdateIP pUIP = new ProxyUpdateIP(tabela, ipAddress, serverSocket);
                        updates.put(ipAddress,pUIP);
                        pUIP.start();
                     }
                  }
                  else{
                     TabelaMonitor t;
                     synchronized(tabela){ t = tabela.get(ipAddress); }
                     synchronized(t){
                        Timestamp now = new Timestamp(System.currentTimeMillis());
                        t.setTime(now);
                        t.incReset();
                     }
                  }
                  break;
               case 1:
                  ProxyUpdateIP p;
                  p = updates.get(ipAddress);
                  p.setPacket(receiveData);
                  break;
            }
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
   
}
