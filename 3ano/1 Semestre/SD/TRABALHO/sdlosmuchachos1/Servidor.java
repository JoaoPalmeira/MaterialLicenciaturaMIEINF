import java.util.*;
import java.net.*;
import java.io.*;

class Registo{
  private HashMap<String,Conta> logC;
  private HashMap<Integer,Game> logG;

  public Registo(){
    this.logC = new HashMap<String,Conta> ();
    this.logG = new HashMap<Integer,Game> ();

  }
  public synchronized addc(Conta c){
    log.putIfAbsent( c.getUsername() , c );
    notifyAll();
  }
  public synchronized addg(Game g){
    log.putIfAbsent( new Integer(logG.size()) , g );
    notifyAll();
  }
}
class Matchmaking implements Runnable{
  private Socket cs;
  private Registo reg;

  public Matchmaking(Socket cs, Registo registo){
    reg = registo;
    this.cs = cs;
  }
  public void run(){
    /*
    selecionar 10 jogadores conectados
    ordenar
    criar jogo
    terminar jogo/guardar no Registo
    aplicar funções win/loss aos jogadores de cada equipa
    */
  }
}

public class Servidor{
    private Registo registo;
    public static void main(String[] args) {
      ServerSocket ss = new ServerSocket(9999);
      Socket cs;

      this.registo = new Registo();

      while (true){
        cs = ss.accept();
        Thread t = new Thread( new Matchmaking(cs,registo) );
        //Thread tw = new Thread( new TreatClientWrite(cs,l) );
        t.start();
        //tw.start();
      }
    }
}
