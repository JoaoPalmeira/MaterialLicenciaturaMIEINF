package sdServer;

import java.util.*;

public class Registo {
    private HashMap<String, Conta> logC = new HashMap<>();
    private HashMap<Integer, Game> logG = new HashMap<>();
    private ArrayList<Hero> logH = new ArrayList<>(30); //mudar para HashMap depois de ter nome

    public Registo() {
        for (int i = 0; i < 30; i++) {
            logH.add(new Hero(i));
        }
    }
    
    public int games(){
        return logG.size();
    }
    
    public synchronized void addc(Conta c) {
        logC.put(c.getUsername(), c);
        notifyAll();
    }

    public synchronized void addg(Game g) {
        g.incId();
        logG.put(g.getId(), g);
        notifyAll();
    }

    public synchronized void addh(Hero h) {
        logH.add(h);
        notifyAll();
    }

    public Conta getc(String user) {
        return logC.get(user);
    }

    public Game getg(int cod) {
        return logG.get(cod);
    }

    public Hero geth(int cod) {
        return logH.get(cod);
    }

    public boolean containsConta(String user) {
        return logC.containsKey(user);
    }
    
    public ArrayList<Conta> searching(Comparator<Conta> cmp) {
        return logC.values().stream().filter(c -> c.getMatchmaking()).sorted(cmp).collect(ArrayList::new, ArrayList::add, (arrayList, c) -> arrayList.addAll(c));
    }
    // public boolean containsGame()
    // public boolean containsHero()
}
