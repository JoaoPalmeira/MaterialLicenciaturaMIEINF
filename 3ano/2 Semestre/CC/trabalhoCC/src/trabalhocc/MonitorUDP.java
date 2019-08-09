
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
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

/**
 *
 * @author jeter15
 */
public class MonitorUDP {
    
    
    
    
    public static void main(String args[]) throws IOException{
        
        int port = 8888;
        Estrutura struct = new Estrutura();
        Coder c = new Coder();
        
        // De 3 em 3 segundos enviar uma mensagem de probing por UDP
        
        Tempo tempo = new Tempo(port,3,c);
        tempo.start();
        
        
        try{
        
        // Escutar respostas em Unicast
        byte[] buf = new byte[1000];
        DatagramPacket recv = new DatagramPacket(buf,buf.length);
        DatagramSocket ds = new DatagramSocket(port);
        ds.receive(recv);
        ds.close();
        
        
        //Fazer o parser para "partir" a mensagem em cpu e ram por uma vírgula
        
        String msg_recebida = new String(recv.getData(), 0,recv.getLength());
	String msg_partida[] = msg_recebida.split(",");
	String data = "" + msg_partida[0] + "" + msg_partida[1];
	long past_time = Long.parseLong(msg_partida[2]);
	long current_time = System.currentTimeMillis();
	long rtt = current_time - past_time;
	String chave = c.calculateMessage(data);
        
 
        if(chave.equals(msg_partida[3])){
            String endereco_IP = recv.getAddress().getHostAddress();
            struct.refresh_estrutura(endereco_IP,port,Integer.parseInt(msg_partida[0]),Integer.parseInt(msg_partida[1]));
            struct.imprimirTabela();
        } else {
            System.out.println("The key did not match the one the agent has!\n");
	} 
        
        
        // Necessário criar um método para fazer imprimir a tabela
        
        
        
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
        
        
        
        
      
    }
    
 
}