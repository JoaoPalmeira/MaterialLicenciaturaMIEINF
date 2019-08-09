package sdlosmuchachos;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.Socket;
import java.text.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PalBruno
 */
public class Cliente {
    
    
    public static Socket player;
    public static BufferedReader input;
    public static PrintWriter output;
    public static BufferedReader ler;
    
    private static void menu_inicial(){
            System.out.println("++++++++++++++++++++++++++");
            System.out.println("|     Welcome Players    |");
            System.out.println("|************************|");
            System.out.println("|                        |");
            System.out.println("| [1]    REGISTAR        |");
            System.out.println("| [2]    LOGIN           |");
            System.out.println("| [0]    SAIR            |");
            System.out.println("|                        |");
            System.out.println("++++++++++++++++++++++++++");
    }
    
    public static void main(String[] args) throws IOException, ParseException{
            
            Socket player = new Socket("localhost", 4322);

            output = new PrintWriter (player.getOutputStream(), true);
            input = new BufferedReader (new InputStreamReader (player.getInputStream()));
            ler = new BufferedReader (new InputStreamReader(System.in));
            //String line;
            
            try{
            menu_inicial();
            String current=ler.readLine();
            int converse = 0;
            converse = Integer.parseInt(current);
            while( converse != 0 ){
               switch ( converse ) {
                   case 1 : System.out.println("Qual o username que deseja?");
                            String recebe_nome; recebe_nome = ler.readLine();
                            System.out.println("Qual a pass que deseja?");
                            String recebe_pass = ler.readLine();
                            out.println("REGISTO|" + recebe_nome + "|" + recebe_pass);
                            System.out.println(current = input.readLine());
                            /* if ( !current.equals("UTILIZADOR JA EXISTENTE")){ 
                                menu_utilizador(recebe_nome);
                                
                            }
                            else {
                                main(args);
                            break;}*/
                   case 2 : System.out.println("Qual o username?");
                            String recebe_nome1 = ler.readLine();
                            System.out.println("Qual a pass?");
                            String recebe_pass1 = ler.readLine();
                            out.println("LOGIN|" +recebe_nome1 + "|" +recebe_pass1 );
                            System.out.println(current=ler.readLine());
                            if ( current.equals("[UTILIZADOR JA CONECTADO]")) main(args);
                            /*if ( current.equals("[LOGIN FEITO COM SUCESSO]")){ 
                                menu_utilizador(ul);
                                int op = -1;   
    */
               }
            
    
    
            }
            }catch (IOException ex) {
            System.out.println("[SERVIDOR INDISPONIVEL, TENTE MAIS TARDE!]");
        }
        
    }
    
    
}
