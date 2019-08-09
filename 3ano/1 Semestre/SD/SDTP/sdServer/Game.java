package sdServer;


import java.util.*;
import java.net.*;
import java.io.*;

public class Game{
  private Team blue;
  private Team orange;
  private boolean result;
  // private HashMap<String,Integer> selected;

  public Game(Collection<String> blue, Collection<String> orange){
    this.blue = new Team(blue);
    this.orange = new Team(orange);
  }

  public void start(){
    
  }

  public void giveResult(){
    //se der tempo por resultado a variar com winrate de certos herois e com winrate do jogador com esse heroi.
    Random r = new Random();
    result = r.nextBoolean();
  }

  public void heroselect(){

  }

  public boolean containsPlayer(String u){
    return (e1.contains(u) || e2.contains(u));
  }

}
