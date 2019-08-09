
import com.sun.management.OperatingSystemMXBean;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
//
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jeter15
 */
public class AgenteUDP {
    
    public static void main(String args[]) throws IOException, InterruptedException{
        
        double ram = 0.0;
	double cpu = 0.0;
        Coder c = new Coder();
	
        
            
        // Vai escutar os pedidos enviados pelo MonitorUDP em Multicast
        
        try {
            InetAddress group = InetAddress.getByName("239.8.8.8");
            MulticastSocket  s = new MulticastSocket(8888);
            
            System.out.println("Multicast Receiver running at:"
                    + s.getLocalSocketAddress());
            
            
            s.joinGroup(group);
            byte[] buf = new byte[1000];
            DatagramPacket recv = new DatagramPacket(buf,buf.length);
            
            System.out.println("Waiting for a  multicast message...");
            while(true) {
                s.receive(recv);
                String msg = new String(recv.getData(), recv.getOffset(),
                        recv.getLength());
                System.out.println("[Multicast  Receiver] Received:" + msg);
                                
                //s.leaveGroup(group);
                //s.close();
            
                // Por cada mensagem recebida, o AgenteUDP vai responder com uma mensagem ao Monitor com o ram e cpu
                // Para tal, usar o OperatingSystemMXBean
                
                String sentence = new String(recv.getData(), 0, recv.getLength());
		String[] parts = sentence.split(",");
		String chave = c.calculateMessage(parts[0]);
		System.out.println("key from Monitor: " + parts[1] + "\n" + "key calculated: " + chave + "\n");
		if(chave.equals(parts[1])) {
                    InetAddress ipaddress = recv.getAddress();
                    OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
                    float total_mem = (float) (osBean.getTotalPhysicalMemorySize() / 1024);
                    float used_mem = (float) (total_mem - (osBean.getFreePhysicalMemorySize() / 1024));
                    float cpu_load = (float) osBean.getSystemCpuLoad();
                    ram = (double) (used_mem / total_mem);
                    cpu = (double) (cpu_load);
                    String data = "" + ram + "" + cpu;
                    String keyToSend = c.calculateMessage(data);
                    Random rand = new Random();
                    int wait = rand.nextInt(10);
                
                    try{
			TimeUnit.MILLISECONDS.sleep(wait);
			} catch(InterruptedException ex) {
                        	ex.printStackTrace();
			}            
                
      
		String message = ram + "," + cpu + ","  + keyToSend;                        
                                        
                DatagramPacket dp = new DatagramPacket(message.getBytes(),message.length(),ipaddress,8888);
		DatagramSocket ds = new DatagramSocket();
                
                
                // Vai criar um atraso nas respostas ( 0 a 10 ms ) para não serem enviadas ao mesmo tempo
                
                Random random = new Random();
                int espera = random.nextInt(10);
                
                

                ds.send(dp);
                ds.close();
                    
                    
                
                }else {
                    System.out.println("The key did not match with the one the monitor has!\n");
		}
            }  } catch (UnknownHostException ex) {
            Logger.getLogger(AgenteUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    
    
    
    
    
    
    
    }
}
