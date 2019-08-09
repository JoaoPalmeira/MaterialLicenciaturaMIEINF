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
    else reg.addC(u,new Conta(u,p));
    return "Utilizador registado com sucesso!";
  }

  public String login(String u, String p){
    if(reg.containsConta(u)){
      if(reg.getC(u).getPassword().equals(p)){
        reg.addC(reg.getC(u).setLigacao(true);
        return "Login efetuado com sucesso"
      }
      else
        return "Password Incorreta"
    }
    return "Utilizador inexistente"
  }

  public synchronized boolean hand(String s) throws ParseException{
    hand = true;
    String[] arg = s.split("\\|");
    switch (arg[0]) {
      case "Registo": out.println(regista(arg[1],arg[2]));break;
      case "Login": out.println(login(arg[1],arg[2]));break;
      case "Sair" : reg.addC(reg.getC(arg[1]).setLigacao(false));break;
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
