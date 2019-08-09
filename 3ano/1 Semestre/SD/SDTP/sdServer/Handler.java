package sdServer;


import java.util.*;
import java.net.*;
import java.io.*;

public class Handler implements Runnable{
  private PrintWriter out;
  private BufferedReader in;
  private Socket cs;
  private Registo reg;
  private Barrier bar = new Barrier(10);

  public Handler(Socket cs, Registo registo){
    reg = registo;
    this.cs = cs;
  }

  public String regista(String u, String p){
    if(reg.containsConta(u)) return "Utilizador já existente!";
    reg.addC(  u  ,(   new Conta(u,p, in, out)).connect()  );
    return "Utilizador registado com sucesso!";
  }

  public String login(String u, String p){
    if(reg.containsConta(u)){
      if(reg.getc(u).getPassword().equals(p)){
        reg.addC(reg.getc(u).connect());
        return "Login efetuado com sucesso"
      }
      else
        return "Password Incorreta"
    }
    return "Utilizador inexistente"
  }
  // depois vemos isto
  // nos 10 players que vao jogar a diferença de ranks nao pode ser >1
  public ArrayList<Conta> checkQueue(){
    Comparator<Conta> cmp = new Comparator<Conta>(){
      public int compare(Conta a, Conta b){
        return (int) (a.getRank() - b.getRank());
      }
    };
    ArrayList<Conta> a = reg.getAllC().stream().filter(c -> c.getMatchmaking()).sorted(cmp).collect(ArrayList :: new, ArrayList :: add, ArrayList :: AddAll);
    boolean match = false;
    for(int i = 0; !match && (i + 9)< a.size(); i++){
      if(  ( a.get(i).getRank() - a.get(i+9).getRank() ) <= 1) match = true;
    }
    if(match) return a.subList(i, i+10);
    else return null;
  }

  public void startGame(ArrayList<Conta> players){
    for(Conta c : players){
      c.setMatchmaking(false);
      reg.addc(c);
      c.write("Game Started");
    }
    ArrayList<String> p = players.stream().map(c -> c.getUsername()).collect(ArrayList :: new, ArrayList :: add, ArrayList :: AddAll);
    Collection<String> eq1 = new ArrayList<String>(5);
    Collection<String> eq2 = new ArrayList<String>(5);
    eq1.add(p.get(0));
    eq2.add(p.get(1));
    eq2.add(p.get(2));
    eq1.add(p.get(3));
    eq1.add(p.get(4));
    eq2.add(p.get(5));
    eq2.add(p.get(6));
    eq1.add(p.get(7));
    eq1.add(p.get(8));
    eq2.add(p.get(9));

    Game g = new Game(eq1, eq2);
    g.start(this.in, this.out);
  }

  public void startMatchmaking(String u){
    reg.getc(u).setMatchmaking(true);
    notifyAll();
    boolean match = false;
    ArrayList<Conta> a;
    while(!match){
      if((a = checkQueue()) != null) match = true;
      else wait();
    }
    startGame(a);
  }

  


  public void playerStats(String u){
    Conta p = new Conta(reg.getc(u));
    out.println("Player:" + u);
    out.println("Rank =" + p.getRank());
    out.println("Numero de Jogos =" + p.getNrJogos());
    out.println("Total Vitórias =" + p.getTotWins());
    out.println("Vitórias seguidas =" + p.getSeqWins());
    out.println("Winrate =" + (p.getTotWins())/(p.getNrJogos()));
  }

  public synchronized boolean hand(String s) throws ParseException{
    hand = true;
    String[] arg = s.split("\\|");
    switch (arg[0]) {
      case "Registo": out.println(regista(arg[1],arg[2]));break;
      case "Login": out.println(login(arg[1],arg[2]));break;
      case "Sair" : reg.addC(reg.getc(arg[1]).setLigacao(false));break;
      case "Estatisticas": playerStats(arg[1]);break;
      default: break;
    }
    return hand;
  }

  public void run(){
    try{
      out = new PrintWriter(cs.getOutputStream(),true);
      in = new BufferedReader( new InputStreamReader(cs.getInputStream()));

      while(hand(in.readLine())){
        out.flush();
      }
    }
    catch(IOException e){
      e.printStackTrace();
    }
    catch(ParseException e){
      e.printStackTrace();
    }
    catch(NullPointerException e){
    }
  }
}
