package sdServer;

import java.util.*;
import java.net.*;
import java.io.*;

public class Registo{
  private HashMap<String,Conta> logC;
  private HashMap<Integer,Game> logG;
  private ArrayList<Hero> logH; //mudar para HashMap depois de ter nome

  public Registo(){
    this.logC = new HashMap<String,Conta> ();
    this.logG = new HashMap<Integer,Game> ();
    this.logH = new ArrayList<Hero>(30); // HashMap
    for(int i = 0; i<30; i++)
      logH.add(new Hero(i));
    // String[] a = new String[30]();
    // a[0]=" ";
    // a[1]=" ";
    // a[2]=" ";
    // a[3]=" ";
    // a[4]=" ";
    // a[5]=" ";
    // a[6]=" ";
    // a[7]=" ";
    // a[8]=" ";
    // a[9]=" ";
    // a[10]=" ";
    // a[11]=" ";
    // a[12]=" ";
    // a[13]=" ";
    // a[14]=" ";
    // a[15]=" ";
    // a[16]=" ";
    // a[17]=" ";
    // a[18]=" ";
    // a[19]=" ";
    // a[20]=" ";
    // a[21]=" ";
    // a[22]=" ";
    // a[23]=" ";
    // a[24]=" ";
    // a[25]=" ";
    // a[26]=" ";
    // a[27]=" ";
    // a[28]=" ";
    // a[29]=" ";
    // for(int i = 1; i<=30, i++){
    //   logH.put(new Integer(i), new Hero(i,a.get(i)));
    // }
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
