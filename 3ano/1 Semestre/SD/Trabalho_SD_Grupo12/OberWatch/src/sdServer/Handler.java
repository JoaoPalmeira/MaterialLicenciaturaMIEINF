package sdServer;

import java.util.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Handler implements Runnable {

    private final UserMenu tui = new UserMenu();
    private PrintWriter out;
    private BufferedReader in;
    private Socket cs;
    private Registo reg;
    private static boolean marker = true;
    private static Barrier bar1 = new Barrier(10);
    private static Barrier bar2 = new Barrier(10);
    private static Barrier bar3 = new Barrier(10);
    private static Barrier bar4 = new Barrier(10);
    private Game g;
    private static CountDownLatch l = new CountDownLatch(10);
    private static CountDownLatch l1 = new CountDownLatch(10);

    public Handler(Socket cs, Registo registo) {
        reg = registo;
        this.cs = cs;
    }

    public synchronized String regista(String u, String p) {
        if (u == null) {
            return "Nome Inválido";
        }
        if (reg.containsConta(u)) {
            return "Utilizador já existente!";
        }
        Conta c = new Conta(u, p);
        c.connect(out, in);
        reg.addc(c);
        return "Utilizador registado com sucesso!";
    }

    public synchronized String login(String u, String p) {
        if (reg.containsConta(u) ) {
            if (reg.getc(u).getPassword().equals(p) && !reg.getc(u).getLigacao()) {
                Conta c = reg.getc(u);
                c.connect(out, in);
                reg.addc(c);
                return "Login efetuado com sucesso";
            } else if (reg.getc(u).getLigacao()){
                return "Utilizador com sessão iniciada";
            } else {
                return "Password Incorreta";
            }
        }
        return "Utilizador inexistente";
    }
    // depois vemos isto
    // nos 10 players que vao jogar a diferença de ranks nao pode ser >1

    public ArrayList<Conta> checkQueue() {
        Comparator<Conta> cmp = (Conta a, Conta b) -> (int) (a.getRank() - b.getRank());
        ArrayList<Conta> a = reg.searching(cmp);
        boolean match = false;
        int i;
        for (i = 0; !match && (i + 9) < a.size(); i++) {
            if ((a.get(i).getRank() - a.get(i + 9).getRank()) <= 1) {
                match = true;
            }
        }
        i--;
        ArrayList<Conta> b = new ArrayList<>();
        if (match) {
            while (b.size() < 10) {
                b.add(a.get(i));
                i++;
            }
            return b;
        } else {
            return null;
        }
    }

    public void startGame(ArrayList<Conta> players, String u) {
        int gid = -1;
        Conta pl;
        for (Conta c : players) {
            if (c.getUsername().equals(u)) {
                c.setMatchmaking(false);
                reg.addc(c);
            }
        }
        out.println("Game Started!");
        try {
            if (l1.getCount() > 1) {
                l1.countDown();
                l1.await();
                gid = reg.getc(u).getInGame();
                System.out.println(gid);
                this.g = reg.getg(gid);
            } else {
                ArrayList<String> p = players.stream().map(c -> c.getUsername()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
                Collection<String> eq1 = new ArrayList<>(5);
                Collection<String> eq2 = new ArrayList<>(5);
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
                this.g = new Game(eq1, eq2);
                reg.addg(g);
                gid = g.getId();
                System.out.println("Creator: " + gid);
                for (String s : p) {
                    Conta c = reg.getc(s);
                    c.setInGame(gid);
                    reg.addc(c);
                }
                l1.countDown();
                l1 = new CountDownLatch(10);
            }
        } catch (InterruptedException e) {
            System.out.println("Latch Error");
        }
    }

    public void startMatchmaking(String u) {
        Conta b = reg.getc(u);
        b.setMatchmaking(true);
        reg.addc(b);
        boolean match = false;
        ArrayList<Conta> a = null;
        l.countDown();
        while (!match) {
            a = checkQueue();
            if (a != null) {
                match = true;
            } else {
                try {
                    l.await();
                } catch (InterruptedException e) {
                    System.out.println("Barrier Error");
                }

            }
        }
        try {
            bar1.barrier();
        } catch (InterruptedException e) {
            System.out.println("Barrier Error");
        }

        l = new CountDownLatch(10);

        for (Conta c : a) {
            c.setMatchmaking(false);
            reg.addc(c);
        }
        startGame(a, u);
    }

    public void playerStats(String u) {
        Conta p = new Conta(reg.getc(u));
        out.println("Player:" + u);
        out.println("Rank =" + p.getRank());
        out.println("Numero de Jogos =" + p.getNrJogos());
        out.println("Total Vitórias =" + p.getTotWins());
        out.println("Vitórias seguidas =" + p.getSeqWins());
        if (p.getNrJogos() == 0) {
            out.println("Winrate = 0");
        } else {
            out.println("Winrate =" + (p.getTotWins()) / (p.getNrJogos()));
        }
        out.println(" \n");
    }

    public void heroselect(Team eq, String user, int hero) {
        int suc = eq.select(user, hero);
        if (suc != 1) {
            out.println("Heroi já escolhido ou inválido");
            out.println("Primeiro Heroi Disponível Atribuido");
            Random r = new Random();
            for (int i = 1; suc != 1 && i <= 30; i++) {
                suc = eq.select(user, hero = i);
            }
        }
        
        out.println("Heroi escolhido com sucesso");

        for (String s : eq.getTeamPlayers()) {
            reg.getc(s).write(user + " escolheu o heroi #" + hero);
        }
        try {
            bar2.barrier();
        } catch (InterruptedException e) {
            System.out.println("Barrier Error");
        }
        for (String s : eq.getTeamPlayers()) {
            reg.getc(s).write(" ");
        }
        declareWinners(this.g.getResult(),user);
    }

    public void declareWinners(boolean winner, String u) {
        ArrayList<String> arr;

        if (winner) {
            arr = g.getBlue().getTeamPlayers();
        } else {
            arr = g.getOrange().getTeamPlayers();
        }
        Conta c = reg.getc(u);
        System.out.println(reg.games());
        if (arr.contains(u)) {
            c.win();
        } else {
            c.loss();
        }
        reg.addc(c);
        out.println(" E Equipa vencedora ééééé.....");
        out.println(" ***Som de tambores***        ");
        if (winner) {
            out.println("Equipa Blue");
        } else {
            out.println("Equipa Orange");
        }
        
        for (int i = 0; i < 5; i++) {
            out.println(" Jogador " + i + " :" + arr.get(i));
        }
        out.println(" ");
    }

    public synchronized boolean hand(String s) {
        System.out.println(s);

        boolean hand = true;
        String[] arg = s.split("\\|");
        switch (arg[0]) {
            case "Registo":
                out.println(regista(arg[1], arg[2]));
                break;
            case "Login":
                out.println(login(arg[1], arg[2]));
                break;
            case "Sair": {
                hand = false;
                Conta c = reg.getc(arg[1]);
                c.disconnect();
                reg.addc(c);
                break;
            }
            case "Estatisticas": {
                playerStats(arg[1]);
                break;
            }
            case "Matchmaking": {
                startMatchmaking(arg[1]);
                break;
            }
            case "Select": {
                heroselect(this.g.getTeam(arg[1]), arg[1], Integer.parseInt(arg[2]));
                //boolean win = this.g.getResult();
                //declareWinners(win, arg[1]);
            }
            case "MenuInicial": {
                tui.menuInicial(out, in);
                break;
            }
            case "MenuConta": {
                tui.menuConta(out, in);
                break;
            }
            case "MenuJogo": {
                tui.menuJogo(out, in, g.getBlue().getTeamPlayers(), g.getOrange().getTeamPlayers());
                break;
            }
            case "MenuHeroi": {
                tui.menuHeroi(out, in);
                break;
            }
            case "MenuJogoHeroi": {
                tui.menuJogoHeroi(out, in, g.getBlue().getComp(), g.getOrange().getComp());
                break;
            }
            default:
                break;
        }
        return hand;
    }

    public void run() {
        try {
            out = new PrintWriter(cs.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(cs.getInputStream()));

            while (hand(in.readLine())) {
                out.flush();
            }
        } catch (IOException e) {
        }
    }
}
