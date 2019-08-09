import java.io.*;
import java.net.*;
import java.util.*;

public class Client{

	public static void main(String[] args) throws Exception{
		Socket socket = new Socket(args[0],80);
		ClientWriter writer = new ClientWriter(socket);
		ClientReader reader = new ClientReader(socket);
		writer.start();
		reader.start();
	}

}