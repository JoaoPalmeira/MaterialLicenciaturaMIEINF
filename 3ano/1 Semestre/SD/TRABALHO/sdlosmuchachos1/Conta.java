import java.util.*;
import java.net.*;
import java.io.*;

public class Conta {

    private String username;
    private String password;
    private boolean ligacao;
    private Integer rank;
    private Integer nrJogos;
    private Integer seqWins; //numero de vitorias seguidas

    public Conta(String username,String password, Integer rank, Integer nrJogos) {
        this.username = username;
        this.ligacao = false;
        this.password = password;
        this.rank = rank;
        this.nrJogos = nrJogos;
    }

    public String getUsername(){
      return this.username;
    }
    public String getPassword(){
        return this.password;
    }

    public boolean getLigacao() {
        return this.ligacao;
    }

    public void setLigacao(boolean ligacao){
        this.ligacao = ligacao;
    }

    public Integer getRank() {
        return rank;
    }

    public void win() {
        if(seqWins >= 1 && rank<9) rank++;
        else seqWins++;
    }

    public void loss() {
      if(seqWins < 1 && rank>0)rank--;
      else seqWins--;
    }

    public Integer getNrJogos() {
        return nrJogos;
    }

    public void setNrJogos(Integer nrJogos) {
        this.nrJogos = nrJogos;
    }
}
