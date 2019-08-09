import java.io*;
import java.net.*;

public class Client{

    public static void main(String[] args) throws Exception{
		String host = args[0];
	    int port = Integer.parseInt(args[1]);
	    Socket s = new Socket(host, port);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream());
        	
        
        while(true){
            String s1 = System.console().readLine();
            if (s1 == null) break;
            out.println(s1);
            out.flush();
            String s2 = in.readLine();
            if (s2 == null) break;
            System.out.println(s2);
       }
       s.close();
    }

}