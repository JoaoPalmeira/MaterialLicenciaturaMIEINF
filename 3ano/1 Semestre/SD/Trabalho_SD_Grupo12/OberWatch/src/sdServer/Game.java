package sdServer;

import java.util.*;
import java.net.*;
import java.io.*;

public class Game {

    private static int gameid = -1;
    private Team blue;
    private Team orange;
    private Random r = new Random();
    private final boolean result = r.nextBoolean();
    // private HashMap<String,Integer> selected;

    public Game(Collection<String> blue, Collection<String> orange) {
        this.blue = new Team(blue);
        this.orange = new Team(orange);
    }

    public void incId() {
        gameid++;
    }

    public int getId() {
        return gameid;
    }

    public Team getBlue() {
        return this.blue; //talvez fazer clone fosse bom
    }

    public Team getOrange() {
        return this.orange; //talvez fazer clone fosse bom
    }

    public Team getTeam(String u) {
        if (blue.getTeamPlayers().contains(u)) {
            return blue;
        }
        if (orange.getTeamPlayers().contains(u)) {
            return orange;
        }
        return null;
    }

    public boolean getResult() {
        return this.result;
    }

    public boolean containsPlayer(String u) {
        return (blue.contains(u) || orange.contains(u));
    }

}
