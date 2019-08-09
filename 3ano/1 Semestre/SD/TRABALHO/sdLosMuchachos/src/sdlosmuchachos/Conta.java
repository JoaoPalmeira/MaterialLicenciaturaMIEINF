package sdlosmuchachos;


import sdlosmuchachos.Player;
import java.util.HashMap;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BruPal
 */
public class Conta {
    
    private HashMap<String,Player> players;
    private Integer rank;
    private Integer nrJogos;

    public Conta(HashMap<String, Player> players, Integer rank, Integer nrJogos) {
        this.players = players;
        this.rank = rank;
        this.nrJogos = nrJogos;
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getNrJogos() {
        return nrJogos;
    }

    public void setNrJogos(Integer nrJogos) {
        this.nrJogos = nrJogos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.players);
        hash = 89 * hash + Objects.hashCode(this.rank);
        hash = 89 * hash + Objects.hashCode(this.nrJogos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conta other = (Conta) obj;
        if (!Objects.equals(this.players, other.players)) {
            return false;
        }
        if (!Objects.equals(this.rank, other.rank)) {
            return false;
        }
        if (!Objects.equals(this.nrJogos, other.nrJogos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conta{" + "players=" + players + ", rank=" + rank + ", nrJogos=" + nrJogos + '}';
    }
    

    
    

   
    
}
