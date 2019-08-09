package sdServer;


import java.util.*;
import java.net.*;
import java.io.*;

public class Game{
  private ArrayList<String> e1 = new ArrayList<String>(5);
  private ArrayList<String> e2 = new ArrayList<String>(5);
  private boolean result;
  private HashMap<String,Integer> selected;

  public Game(Collection<String> e1, Collection<String> e2){
    this.e1.addAll(e1);
    this.e2.addAll(e2);
  }

  private void giveResult(){
    //se der tempo por resultado a variar com winrate de certos herois e com winrate do jogador com esse heroi.
    Random r = new Random();
    result = r.nextBoolean();
  }

  private void heroselect( /*????*/ ){

  }
}
