import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.Timestamp;

public class TabelaMonitor{

	private int rtt;
	private int packetLoss;
	private int packetCount;
	private int tcpCon;
	private InetAddress ipMonitor;
	private Timestamp time; //a última atualização que estava disponivel
	private int reset; //quando chegar aos 20, dá reset aos valores, menos ao tcpCon;
	private int duplicados;

	public TabelaMonitor(InetAddress ip){
		this.rtt = 0;
		this.packetLoss = 0;
		this.packetCount = 0;
		this.tcpCon = 0;
		this.ipMonitor = ip;
		this.time = new Timestamp(System.currentTimeMillis());
		this.duplicados = 0;
	}

	void setTime(Timestamp t){
		time = t;
	}

	void addDuplicados(int d){
		this.duplicados += d;
	}

	int getNDuplicados(){
		return duplicados;
	}

	float getDuplicados(){
		return ((float)duplicados/packetCount)*100;
	}

	int getNPacketLoss(){
		return packetLoss;
	}

	int getPacketCount(){
		return packetCount;
	}

	void addPacketCount(int p){
		this.packetCount += p;
	}

	Timestamp getTime(){
		return time;
	}

	String getString(){
		return "Rtt: " + rtt + ". PacketLoss: " + packetLoss + ". TCPConection: " + tcpCon 
		+ ". IPMonitor: " + ipMonitor + ".";
	}

	void addRtt(int r){
		this.rtt += r;
	}

	float getRtt(){
		return (float)rtt/packetCount;
	}

	void addPacketLoss(int p){
		this.packetLoss += p;
	}

	float getPacketLoss(){
		return ((float)packetLoss/packetCount)*100;
	}

	void incTCPCon(){
		this.tcpCon ++;
	}

	void decTCPCon(){
		this.tcpCon --;
	}

	int getTCPCon(){
		return tcpCon;
	}

	void setIpMonitor(InetAddress ip){
		this.ipMonitor = ip;
	}

	InetAddress getIpMonitor(){
		return ipMonitor;
	}

	void incReset(){
		reset++;
		if(reset==12){
			System.out.println("PacketLoss %: " + this.getPacketLoss() + ". RTT: " + this.getRtt() + ". Address: " + this.ipMonitor + "Nº PacketLoss:" + this.getNPacketLoss() + ". PacketCount: " + this.getPacketCount() + ". Duplicados: " + this.getDuplicados() + ". Nª Duplicados: " + this.getNDuplicados());
		    System.out.println("Status :" + this.getStatus());
			this.reset=0;
			this.packetLoss=0;
			this.packetCount=0;
			this.rtt=0;
			this.duplicados=0;
		}
	}

	int getReset(){
		return reset;
	}

	double getStatus(){
		return this.getPacketLoss() + this.getRtt() + 0.5*this.getDuplicados() + 10*this.tcpCon;
	}
}