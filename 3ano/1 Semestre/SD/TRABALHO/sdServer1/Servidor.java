package sdServer;

import java.util.*;
import java.net.*;
import java.io.*;

public class Servidor{

  public static void main(String[] args) throws IOException{
    ServerSocket ss = new ServerSocket(9999);
    Socket cs;
    Registo registo;

    registo = new Registo();

    while (true){
      cs = ss.accept();
      Thread t = new Thread( new Matchmaking(cs,registo) );
      t.start();
    }
  }
}
