package sdServer;


import java.io.*;
import java.net.*;
import java.util.*;

public class Hero{
  private int cod;
  private String nome;
  private boolean select;
  // private ReentrantLock lock;

  public Hero(int cod, String nome){
      this.cod = cod;
      this.nome = nome;
      this.select = false;
  }

  public synchronized int select(){
    if(this.select) return 0;
    this.select = true;
    notifyAll();
    return 1;
  }

  public int getCod(){
    return cod;
  }

}
