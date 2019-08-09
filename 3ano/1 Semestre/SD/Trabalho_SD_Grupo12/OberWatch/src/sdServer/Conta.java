package sdServer;

import java.io.*;

public class Conta {

    private BufferedReader in;
    private PrintWriter out;
    private final String username;
    private String password;
    private boolean ligacao;
    private boolean matchmaking;
    private int ingame;
    private int rank;
    private int nrJogos;
    private int seqWins; //numero total de vitorias seguidas
    private int rankWins; //numero de vitorias seguidas no rank
    private int totWins;

    public Conta(String username, String password) {
        this.in = in;
        this.out = out;
        this.username = username;
        this.ligacao = false;
        this.password = password;
        this.rank = 5;
        this.nrJogos = 0;
        this.totWins = 0;
    }

    public Conta(String username, String password, int rank, int nrJogos, int totWins) {
        this.username = username;
        this.ligacao = false;
        this.password = password;
        this.rank = rank;
        this.nrJogos = nrJogos;
        this.seqWins = 0;
        this.rankWins = 0;
        this.totWins = 0;
    }

    public Conta(Conta c) {
        this.username = c.getUsername();
        this.password = c.getPassword();
        this.ligacao = c.getLigacao();
        this.rank = c.getRank();
        this.nrJogos = c.getNrJogos();
        this.seqWins = c.getSeqWins();
        this.totWins = c.getTotWins();
        this.rankWins = 1;
    }
    
    public void setInGame(int gid){
        ingame = gid;
    }
    public int getInGame(){
        return ingame;
    }
    
    public void connect(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
        this.ligacao = true;
    }

    public void disconnect() {
        this.ligacao = false;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean getLigacao() {
        return this.ligacao;
    }

    public boolean getMatchmaking() {
        return matchmaking;
    }

    public void setMatchmaking(boolean b) {
        this.matchmaking = b;
    }
    // public void setLigacao(boolean ligacao){
    //     this.ligacao = ligacao;
    // }

    public int getSeqWins() {
        return seqWins;
    }

    public int getRank() {
        return rank;
    }

    public int getTotWins() {
        return totWins;
    }

    public int getNrJogos() {
        return nrJogos;
    }
    // prob nao precisamos disto
    // public void setNrJogos(int nrJogos) {
    //     this.nrJogos = nrJogos;
    // }

    public void win() {
        nrJogos++;
        seqWins++;
        totWins++;
        if (rankWins >= 1 && rank < 9) {
            rank++;
            rankWins = 0;
        } else {
            rankWins++;
        }
    }

    public void loss() {
        seqWins = 0;
        nrJogos++;
        if (rankWins < 1 && rank > 0) {
            rank--;
            rankWins = 1;
        } else {
            rankWins--;
        }
    }

    public void write(String s) {
        out.println(s);
    }
}
