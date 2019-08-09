import java.io.*;
import java.net.*;
import java.util.*;

public class MonitorUDP
{
   public static void main(String args[]) throws Exception
   {
        DatagramSocket clientSocket = new DatagramSocket(5555);
        MonitorUDPUpd monitorUDPUpd = new MonitorUDPUpd(clientSocket);
        monitorUDPUpd.start();
        MonitorTCP monitorTCP = new MonitorTCP();
        monitorTCP.start();
        InetAddress ipAdress = InetAddress.getByName(args[0]);
        byte[] sendData = new byte[1];
        while(true){
            sendData[0] = 0;
            DatagramPacket sendPacket = new DatagramPacket(sendData, 1, ipAdress, 5555);
            clientSocket.send(sendPacket);
            try{
                Thread.sleep(10000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
