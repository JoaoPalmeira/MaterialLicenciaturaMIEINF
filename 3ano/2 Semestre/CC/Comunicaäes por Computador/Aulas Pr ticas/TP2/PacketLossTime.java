import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.Timestamp;

public class PacketLossTime extends TimerTask{

	private static Object obj;
	private boolean[] res;

	public PacketLossTime(Object o, boolean[] r){
		this.obj = o;
		this.res = r;
	}

	public void run() {
        synchronized(obj){
        	obj.notify();
        	res[0] = false;
        }
    }

}