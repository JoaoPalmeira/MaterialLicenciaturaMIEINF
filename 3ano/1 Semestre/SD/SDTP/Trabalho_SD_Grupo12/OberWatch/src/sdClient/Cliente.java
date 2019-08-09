package sdClient;

import java.text.ParseException;
import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente {

    public static Socket player;
    public static BufferedReader input;
    public static PrintWriter output;
    public static BufferedReader ler;

    public static void main(String[] args) throws IOException, ParseException {

        Socket cs = new Socket("localhost", 9999);
        output = new PrintWriter(cs.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(cs.getInputStream()));
        ler = new BufferedReader(new InputStreamReader(System.in));
        try {
            output.println("MenuInicial");
            Iterator<String> it = input.lines().iterator();
            for (String s = it.next(); !(s.equals(" ")); s = it.next()) {
                System.out.println(s);
            }
            String current = ler.readLine();
            String username = null, password = null, hero = null;
            int converse = 0;
            int muda_menu = 1;
            converse = Integer.parseInt(current);
            loop:
            while (converse != 0) {
                switch (converse) {
                    case 1:
                        System.out.println("Qual o username que deseja?");
                        username = ler.readLine();
                        System.out.println("Qual a pass que deseja?");
                        password = ler.readLine();
                        output.println("Registo|" + username + "|" + password);
                        System.out.println(current = input.readLine());
                        if (!(current.equals("Utilizador registado com sucesso!"))) {
                            main(args);
                        }
                        break loop;

                    case 2:
                        System.out.println("Qual o username?");
                        username = ler.readLine();
                        System.out.println("Qual a pass?");
                        password = ler.readLine();
                        output.println("Login|" + username + "|" + password);
                        System.out.println(current = input.readLine());
                        if (!(current.equals("Login efetuado com sucesso"))) {
                            main(args);
                        }
                        if (current.equals("Login efetuado com sucesso")) {
                            muda_menu = -1;
                        }
                        break loop;
                }
            }
            while (muda_menu != 0) {
                output.println("MenuConta");
                it = input.lines().iterator();
                for (String s = it.next(); !(s.equals(" ")); s = it.next()) {
                    System.out.println(s);
                }
                muda_menu = Integer.parseInt(ler.readLine());
                switch (muda_menu) {
                    case 0:
                        output.println("Sair|" + username + "|" + password);
                    case 1: {
                        output.println("Estatisticas|" + username);
                        it = input.lines().iterator();
                        for (String s = it.next(); !(s.equals(" ")); s = it.next()) {
                            System.out.println(s);
                        }
                        ler.readLine();
                        break;
                    }
                    case 2: {
                        output.println("Matchmaking|" + username);

                        output.println("MenuJogo");
                        it = input.lines().iterator();
                        for (String s = it.next(); !(s.equals(" ")); s = it.next()) {
                            System.out.println(s);
                        }
                        int escHeroi = 1;

                        output.println("MenuHeroi");
                        it = input.lines().iterator();
                        for (String s = it.next(); !(s.equals(" ")); s = it.next()) {
                            System.out.println(s);
                        }

                        hero = ler.readLine();
                        output.println("Select|" + username + "|" + hero);
                        it = input.lines().iterator();
                        for (String s = it.next(); !(s.equals(" ")); s = it.next()) {
                            System.out.println(s);
                        }
                        //ler.readLine();
                        it = input.lines().iterator();
                        for (String s = it.next(); !(s.equals(" ")); s = it.next()) {
                            System.out.println(s);
                        }
                        //ler.readLine();
                        /*output.println("MenuJogoHeroi");
                        it = input.lines().iterator();
                        for (String s = it.next(); !(s.equals(" ")); s = it.next()) {
                            System.out.println(s);
                        }*/
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("[SERVIDOR INDISPONIVEL, TENTE MAIS TARDE!]");
        }

    }

}
