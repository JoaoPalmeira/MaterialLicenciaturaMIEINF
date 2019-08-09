package sdServer;

import java.util.*;
import java.net.*;
import java.io.*;


public class Servidor {

    public static void main(String[] args) throws IOException {
        // try{
        ServerSocket ss = new ServerSocket(9999);
        Socket cs;
        Registo registo;

        registo = new Registo();

        while (true) {
            cs = ss.accept();
            Thread t = new Thread(new Handler(cs, registo));
            t.start();
        }
        // }
        // catch(IOException e){}
    }
}
