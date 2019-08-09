package sdServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class Team {

    private HashMap<String, Integer> comp = new HashMap<>();
    private int[] arr = new int[30];
    private final ArrayList<String> eq;

    public Team(Collection<String> players) {
        eq = new ArrayList<>(players);
        for (int i = 0; i < 30; i++) {
            arr[i] = 0;
        }
    }
    
    public ArrayList<String> getTeamPlayers(){
        return this.eq;
    }
    
    public boolean contains(String u) {
        return eq.contains(u);
    }
    
    public HashMap<String, Integer> getComp(){
        return this.comp;
    }
    
    public synchronized int select(String u, int s) {
        if (arr[s] == 0) {
            arr[s] = 1;
            comp.put(u, s);
            return 1; // Heroi selecionado com sucesso

        } else {
            return 0; //Heroi ja selecionado por outro jogador da equipa
        }
    }

}
