import java.io.*;
import java.net.*;
import java.util.*;

public class MonitorUDPUpd extends Thread{

   private DatagramSocket clientSocket;

   public MonitorUDPUpd(DatagramSocket c){
      this.clientSocket = c;
   }

   public void run(){
      try{
         byte[] receiveData = new byte[1024];
         byte[] sendData = new byte[1];
         DatagramPacket sendPacket;
         DatagramPacket receivePacket;
         while(true){
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            InetAddress ipAddress = receivePacket.getAddress();
            switch(receiveData[0]){
               case 1:
                  sendData = new byte[2];
                  sendData[0] = 1;
                  sendData[1] = receiveData[1];
                  sendPacket = new DatagramPacket(sendData, 2, ipAddress, 5555);
                  clientSocket.send(sendPacket);
                  break;
            }
         }
    	}
      catch(Exception e){}
   }
}