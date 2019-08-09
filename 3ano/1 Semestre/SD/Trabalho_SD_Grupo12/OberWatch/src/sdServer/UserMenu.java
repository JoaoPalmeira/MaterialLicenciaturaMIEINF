package sdServer;

import java.io.*;
import java.util.*;


public class UserMenu implements UserInterface {

    public UserMenu(){}
    public void menuInicial(PrintWriter out, BufferedReader in){
        
        out.printf("|++++++++++++++++++++++++|\n"
                + "|     Welcome Players    |\n"
                + "|************************|\n"
                + "|                        |\n"
                + "| [1]    REGISTAR        |\n"
                + "| [2]    LOGIN           |\n"
                + "| [0]    SAIR            |\n"
                + "|                        |\n"
                + "++++++++++++++++++++++++++\n"
                + " \n");
    }
    
    public void menuConta(PrintWriter out, BufferedReader in){
        out.printf("|++++++++++++++++++++++++|\n"
                + "|       Ob3rWaTcH        |\n"
                + "|************************|\n"
                + "|                        |\n"
                + "| [1]    ESTATÍSTICAS    |\n"
                + "| [2]    COMECAR JOGO    |\n"
                + "| [0]    SAIR            |\n"
                + "|                        |\n"
                + "++++++++++++++++++++++++++\n"
                + " \n");
    }
    public void menuHeroi(PrintWriter out, BufferedReader in){
        out.printf(" Escolha um heroi: \n"
                + " [1] - DOOMFIST        [11] - WINSTON        [21] - GAMBIT  \n"
                + " [2] - GENJI           [12] - ZARYO          [22] - VISAO   \n"
                + " [3] - MCCREE          [13] - ANA            [23] - PANTERA \n"
                + " [4] - PHARAH          [14] - SYMMETRA       [24] - HOWARD  \n"
                + " [5] - REAPER          [15] - NOTURNO        [25] - COLOSSUS\n"
                + " [6] - SOMBRA          [16] - MAGNETO        [26] - GALACTUS\n"
                + " [7] - TRACER          [17] - THOR           [27] - THANOS  \n"
                + " [8] - D.VA            [18] - CICLOPE        [28] - BUCKY   \n"
                + " [9] - BASTION         [19] - WOLVERINE      [29] - AJAX    \n"
                + " [10] -HANZO           [20] - FERA           [30] - ZATANNA \n"
                + " \n");
    }

    
    public void menuJogo(PrintWriter out, BufferedReader in, ArrayList<String> blue, ArrayList<String> orange) {
        out.printf("Blue:\n"
                +blue.get(0)+"\n"
                +blue.get(1)+"\n"
                +blue.get(2)+"\n"
                +blue.get(3)+"\n"
                +blue.get(4)+"\n"
                + "Orange:\n"
                +orange.get(0)+"\n"
                +orange.get(1)+"\n"
                +orange.get(2)+"\n"
                +orange.get(3)+"\n"
                +orange.get(4)+"\n"
                + " \n"
                );
    }
    
    public void menuJogoHeroi(PrintWriter out, BufferedReader in, HashMap<String,Integer> blue, HashMap<String,Integer> orange){
    }
}
