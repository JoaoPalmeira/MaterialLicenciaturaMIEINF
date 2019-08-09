import java.io.*;
import java.net.*;
import java.util.*;

public class hero{
  private int cod;
  private String nome;
  private boolean select;
  // private ReentrantLock lock;

  public hero(int cod, String nome){
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
  
}
