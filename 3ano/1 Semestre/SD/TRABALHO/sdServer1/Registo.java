package sdServer;

import java.util.*;
import java.net.*;
import java.io.*;

public class Registo{
  private HashMap<String,Conta> logC;
  private HashMap<Integer,Game> logG;
  private HashMap<Integer,Hero> logH;

  public Registo(){
    this.logC = new HashMap<String,Conta> ();
    this.logG = new HashMap<Integer,Game> ();
    this.logH = new HashMap<Integer,Hero> ();
    ArrayList<String> a = new ArrayList<String>(30);
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    a.add(" ");
    for(int i = 1; i<=30, i++){
      logH.put(new Integer(i), new Hero(i,a.get(i)));
    }
  }
  public synchronized void addc(Conta c){
    logC.put(c.getUsername() , c );
    notifyAll();
  }
  public synchronized void addg(Game g){
    logG.put(new Integer(logG.size()) , g );
    notifyAll();
  }
  public synchronized void addh(Hero h){
    logH.put(new Integer(h.getCod()), h);
    notifyAll();
  }
  public Conta getc(String user){
    return logC.get(user);
  }
  public Game getg(int cod){
    return logG.get(new Integer(cod));
  }
  public Hero geth(int cod){
    return logH.get(new Integer(cod));
  }
  public boolean containsConta(String user){
    return logC.containsKey(user);
  }
  // public boolean containsGame()
  // public boolean containsHero()
}
