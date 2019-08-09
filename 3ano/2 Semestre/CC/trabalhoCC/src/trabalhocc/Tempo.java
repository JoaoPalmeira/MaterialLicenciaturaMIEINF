
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//
/**
 *
 * @author jeter15
 */
public class Tempo extends Thread {
    
    int port;
    int diferenca_tempo;
    Coder c;
    
    public Tempo(int port,int diferenca_tempo, Coder c){
        
        this.port = port;
        this.diferenca_tempo = diferenca_tempo;
        this.c = c;
        
    }
    
    public void run(){
        
        MonitorUDP udp = new MonitorUDP();
        
        String msg = "Informação";
        try {
            
            
            InetAddress group = InetAddress.getByName("239.8.8.8");
            MulticastSocket s = new MulticastSocket(8888);
            s.joinGroup(group);
            DatagramPacket hi = new DatagramPacket(msg.getBytes(),msg.length(),group,8888);
          
            
            while(true){
                
               s.send(hi);                                             
            }
                        
        } catch (UnknownHostException ex) {
            Logger.getLogger(MonitorUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tempo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            
            Thread.sleep(diferenca_tempo*1000);           
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Tempo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
    
 
    
    
    
    