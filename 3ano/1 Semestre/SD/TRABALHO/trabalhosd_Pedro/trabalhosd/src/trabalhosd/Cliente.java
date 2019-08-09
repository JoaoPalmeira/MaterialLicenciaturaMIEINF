/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhosd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;

/**
 *
 * @author Pedro
 */
public class Cliente {

    public static Socket cliente;
    public static BufferedReader in;
    public static PrintWriter out;
    public static BufferedReader sin;
    
   public static void main(String[] args) throws IOException, ParseException{
            
            Socket cliente = new Socket("localhost", 4321);

            out = new PrintWriter (cliente.getOutputStream(), true);
            in = new BufferedReader (new InputStreamReader (cliente.getInputStream()));
            sin = new BufferedReader (new InputStreamReader(System.in));
            String line;
            
            try{
            menu_inicial();
            String current=sin.readLine();
            int converse =0;
            converse = Integer.parseInt(current);
            while( converse != 0 ){
               switch ( converse ) {
                   case 1 : System.out.println("Qual o username que deseja?");
                            String u = sin.readLine();
                            System.out.println("Qual a pass que deseja?");
                            String p = sin.readLine();
                            out.println("REGISTO|" + u + "|" + p);
                            System.out.println(current = in.readLine());
                            if ( !current.equals("UTILIZADOR JA EXISTENTE")){ 
                                menu_utilizador(u);
                                
                            }
                            else {
                                main(args);
                            break;}
                   case 2 : System.out.println("Qual o username?");
                            String ul = sin.readLine();
                            System.out.println("Qual a pass?");
                            String pl = sin.readLine();
                            out.println("LOGIN|" +ul + "|" +pl );
                            System.out.println(current=in.readLine());
                            if ( current.equals("[UTILIZADOR JA CONECTADO]")) main(args);
                            if ( current.equals("[LOGIN FEITO COM SUCESSO]")){ 
                                menu_utilizador(ul);
                                int op = -1;
        //op=Integer.parseInt(sin.readLine());
        //String current;
        while(op!=0){
            op=Integer.parseInt(sin.readLine());
            switch(op){
                case 1:{
                       
                        System.out.println("Qual o item?");
                        String i = sin.readLine();
                        System.out.println("Qual o valor inicial?");
                        String v = sin.readLine();
                        System.out.println("Qual a data final?");
                        String d = sin.readLine();
                        out.println("Começa|"+ul+"|"+i+"|"+v+"|"+d);
                        System.out.println(current = in.readLine());
                           /* if ( current.equals("[LEILAO ADICIONADO COM SUCESSO]")){
                                menu_utilizador(ul);
                                break;
                            }
                        else System.out.println("[LEILAO NAO ADICIONADO");*/
                        
                        switch(current){
                            case("[LEILAO ADICIONADO COM SUCESSO]"): menu_utilizador(ul);break;
                            case("[Item Inválido]"):menu_utilizador(ul);break;
                            case("[Valor Invalido]"): menu_utilizador(ul);break;
                            case("[Data Invalida]") : menu_utilizador(ul);break;
                            default: menu_utilizador(ul);break;
                        }
                    ;menu_utilizador(ul); break;
                }
                case 2:{
                    boolean estado = true;
                    while(estado==true){
                    out.println("Individual|"+ul);
                    String ago=null;
                    System.out.println(ago=in.readLine());
                        if ( ago.equals("leiloes individuais") ){ estado =false;}
                    }
                    menu_utilizador(ul);
                    break;}

                case 3:{
                    boolean estado = true;
                    while(estado==true){
                    out.println("Geral|"+ul);
                    String ago=null;
                    System.out.println(ago=in.readLine());
                        if ( ago.equals("leiloes ativos") ){ estado =false;}
                    }
                    menu_utilizador(ul);
                    break;}
                case 4:{
                    System.out.println("Qual o id do leilao?");
                    String id = sin.readLine();
                    System.out.println("Qual o valor da licitacao?");
                    String v = sin.readLine();
                    out.println("Licitar|"+ul+"|"+v+"|"+id);
                    String s = null;
                    System.out.println(s = in.readLine());
                    menu_utilizador(ul);

                    break;
                }
                case 5:{
                    
                    //in1.close();
                    //out1.close();
                    current = in.readLine();
                    System.out.println(current);
                    main(args);
                    break;
                }
                default:System.exit(0);
            }
            break;
        }break;
                            }
                            else {main(args);break;}
                            
                    default : System.out.println("COMANDO INVALIDO"); main(args);
                }
               current = sin.readLine();
               converse = Integer.parseInt(current);
            sin.close();
            in.close();
            out.close();
            }
            System.out.println("O CLIENTE SAIU DA APLICAÇAO");
            System.exit(0);
            } catch (IOException ex) {
            System.out.println("[SERVIDOR INDISPONIVEL, TENTE MAIS TARDE!]");
        }
        
        }

   
   private static void menu_inicial(){
        System.out.println("__________________________");
        System.out.println("|        BEM-VINDO       |");
        System.out.println("|________________________|");
        System.out.println("|                        |");
        System.out.println("| [1]    REGISTAR        |");
        System.out.println("| [2]    LOGIN           |");
        System.out.println("| [0]    SAIR            |");
        System.out.println("|                        |");
        System.out.println("|------------------------|");
    }
   
   
   
   private static void menu_utilizador(String u) throws IOException, ParseException{
        int op = -1;
        while(op!=0){
        System.out.println("__________________________");
        System.out.println("|    QUE DESEJA FAZER?   |");
        System.out.println("|________________________|");
        System.out.println("|                        |");
        System.out.println("| [1] COMEÇAR LEILAO     |");
        System.out.println("| [2] LICITAÇOES TOTAIS  |");
        System.out.println("| [3] LEILOES ATIVOS     |");
        System.out.println("| [4] LICITAR ITEM       |");
        System.out.println("| [5] TERMINAR LEILAO    |");
        System.out.println("| [6] ITENS GANHOS       |");
        System.out.println("| [7] ITENS VENDIDOS     |");
        System.out.println("| [8] VOLTAR MENU INICIAL|");
        System.out.println("| [0]  SAIR              |");
        System.out.println("|                        |");
        System.out.println("|------------------------|");
        op=Integer.parseInt(sin.readLine());
        String current;            
            switch(op){
                case 1:{ System.out.println("Qual o item?");
                        String i = sin.readLine();
                        System.out.println("Qual o valor inicial?");
                        String v = sin.readLine();
                        System.out.println("Qual a data final?");
                        String d = sin.readLine();
                        out.println("Começa|"+u+"|"+i+"|"+v+"|"+d);
                        System.out.println(current = in.readLine());
                            if ( current.equals("[LEILAO ADICIONADO COM SUCESSO]")){
                                //menu_utilizador(u);
                                break;
                            }
                        else System.out.println("[LEILAO NAO ADICIONADO");
                    break;
                }
                case 2:{
                    boolean estado = true;
                    //while(estado==true){
                    out.println("Individual|"+u);
                    while(estado==true){
                    String ago=null;
                    System.out.println(ago=in.readLine());
                        if ( ago.equals("licitacoes totais") ){ estado =false;}
                    }
                    break;}

                case 3:{
                    boolean estado = true;
                    
                    out.println("Geral|"+u);
                    while(estado==true){
                    String ago=null;
                    System.out.println(ago=in.readLine());
                        if ( ago.equals("leiloes ativos") ){ estado =false;}
                    }
                    break;}
                case 4:{
                    System.out.println("Qual o id do leilao?");
                    String id = sin.readLine();
                    System.out.println("Qual o valor da licitacao?");
                    String v = sin.readLine();
                    out.println("Licitar|"+u+"|"+v+"|"+id);
                    System.out.println(current=in.readLine());
                    break;
                }
                case 5:{
                    System.out.println("Qual o id do leilao?");
                    String id = sin.readLine();
                    out.println("Termina|"+id+"|"+u);
                    System.out.println(current=in.readLine());
                    break;
                }
                case 6:{
                    boolean estado = true;
                    
                    out.println("Ganho|"+u);
                    while(estado==true){
                    String ago=null;
                    System.out.println(ago=in.readLine());
                        if ( ago.equals("leiloes ganhos") ){ estado =false;}
                    }
                    break;}
                case 7:{
                    boolean estado = true;
                    
                    out.println("Vendido|"+u);
                    while(estado==true){
                    String ago=null;
                    System.out.println(ago=in.readLine());
                        if ( ago.equals("leiloes vendidos") ){ estado =false;}
                    }
                    break;}
                case 8:{
                    String[] args = null;
                    main(args);
                    break;
                }
                case 0: {
                    out.println("Sair|"+u);
                    System.exit(0);
                }
                default:menu_utilizador(u); break;
            }
            
        }
        System.exit(0);
    }
    
    
        
}
