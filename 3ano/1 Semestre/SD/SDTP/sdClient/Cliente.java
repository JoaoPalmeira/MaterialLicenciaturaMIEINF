import static java.lang.System.out;
import java.text.ParseException;
import java.util.*;
import java.net.*;
import java.io.*;


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

    public static void menu_comeca(String recebe_nome){
            System.out.println("++++++++++++++++++++++++++");
            System.out.println("|       Ob3rWaTcH        |");
            System.out.println("|************************|");
            System.out.println("|                        |");
            System.out.println("| [1]    ESTATÍSTICAS    |");
            System.out.println("| [2]    COMECAR JOGO    |");
            System.out.println("| [0]    SAIR            |");
            System.out.println("|                        |");
            System.out.println("++++++++++++++++++++++++++");
    }

    public static void menu_jogo(){

            System.out.println(" Equipa 1:" '\n'); 
            System.out.println(" Jogador 1 : " + jg1 + '\n');
            System.out.println(" Jogador 2 : " + jg2 + '\n');          
            System.out.println(" Jogador 3 : " + jg3 + '\n');
            System.out.println(" Jogador 4 : " + jg4 + '\n');     
            System.out.println(" Jogador 5 : " + jg5 + '\n\n');
            System.out.println(" Equipa 2:" '\n');
            System.out.println(" Jogador 6 : " + jg6 + '\n');
            System.out.println(" Jogador 7 : " + jg7 + '\n');
            System.out.println(" Jogador 8 : " + jg8 + '\n');
            System.out.println(" Jogador 9 : " + jg9 + '\n');
            System.out.println(" Jogador 10: " + jg10 +'\n');
            System.out.println(" [1] Escolha o Herói");
}

    
    public static void menu_jogo_heroi(){

            System.out.println(" Equipa 1:" '\n'); 
            System.out.println(" Jogador 1 : " + jg1 + " -> " '\n');
            System.out.println(" Jogador 2 : " + jg2 + " -> " '\n');          
            System.out.println(" Jogador 3 : " + jg3 + " -> " '\n');
            System.out.println(" Jogador 4 : " + jg4 + " -> " '\n');     
            System.out.println(" Jogador 5 : " + jg5 + " -> " '\n\n');
            System.out.println(" Equipa 2:" '\n');
            System.out.println(" Jogador 6 : " + jg6 + " -> " '\n');
            System.out.println(" Jogador 7 : " + jg7 + " -> " '\n');
            System.out.println(" Jogador 8 : " + jg8 + " -> " '\n');
            System.out.println(" Jogador 9 : " + jg9 + " -> " '\n');
            System.out.println(" Jogador 10: " + jg10 +" -> " '\n');
}

    public static void menu_heroi(){

            System.out.println(" Escolha um heroi: "'\n');
            System.out.println(" [1] - GULIAS     " '\t\t' " [11] - DEFUNTINA " '\t\t' " [21] - JASPION   " '\n');
            System.out.println(" [2] - GODZE      " '\t\t' " [12] - DOSOLINA  " '\t\t' " [22] - ROSSANO   " '\n');
            System.out.println(" [3] - BRUNECO    " '\t\t' " [13] - JUVENALDA " '\t\t' " [23] - DAILEON   " '\n');
            System.out.println(" [4] - BRONKS     " '\t\t' " [14] - FINADINA  " '\t\t' " [24] - ARACI     " '\n');
            System.out.println(" [5] - DR.FANCY   " '\t\t' " [15] - JOTACÁ    " '\t\t' " [25] - GETÚLIO   " '\n');
            System.out.println(" [6] - Sra.AFÍLIA " '\t\t' " [16] - NECROTI   " '\t\t' " [26] - GRACIOSA  " '\n');
            System.out.println(" [7] - BUCETILDES " '\t\t' " [17] - MERDOLIN  " '\t\t' " [27] - NOVELO    " '\n');
            System.out.println(" [8] - HUGH JASS  " '\t\t' " [18] - TRIBUTINA " '\t\t' " [28] - PALMARECO " '\n');
            System.out.println(" [9] - CARABINO   " '\t\t' " [19] - CLEMENSO  " '\t\t' " [29] - ADOLPH    " '\n');
            System.out.println(" [10] - DEUZIVALDO" '\t\t' " [20] - Sr.PÁSCOAS" '\t\t' " [30] - SANTACLAUS" '\n');
}

    public static void fim_jogo(){

            System.out.println(" E Equipa vencedora ééééé....."'\n');
            System.out.println(" ***Som de tambores***        "'\n\n\n');
            TimeUnit.SECONDS.sleep(5);
            System.out.println(" Equipa x" +"CHAMA FUNCAO"'\n\n');
            System.out.println(" Jogador 1 :" + jg1 + '\n');
            System.out.println(" Jogador 2 :" + jg2 + '\n');          
            System.out.println(" Jogador 3 :" + jg3 + '\n');
            System.out.println(" Jogador 4 :" + jg4 + '\n');     
            System.out.println(" Jogador 5 :" + jg5 + '\n\n');

    }
             
      
    public static void main(String[] args) throws IOException, ParseException{

            Socket player = new Socket("localhost", 4321);

            output = new PrintWriter (player.getOutputStream(), true);
            input = new BufferedReader (new InputStreamReader (player.getInputStream()));
            ler = new BufferedReader (new InputStreamReader(System.in));

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
                            out.println("Registo|" + recebe_nome + "|" + recebe_pass);
                            System.out.println(current = input.readLine());
                            if ( !current.equals("Utilizador já existente!")){
                                menu_comeca(recebe_nome);
                                }
                            
                            else {
                                main(args);
                            break;}
                   case 2 : System.out.println("Qual o username?");
                            String recebe_nome1 = ler.readLine();
                            System.out.println("Qual a pass?");
                            String recebe_pass1 = ler.readLine();
                            out.println("Login|" +recebe_nome1 + "|" +recebe_pass1 );
                            System.out.println(current=ler.readLine());
                            if ( current.equals("[UTILIZADOR JA CONECTADO]")) main(args);
                            if ( current.equals("[LOGIN FEITO COM SUCESSO]")){
                                menu_comeca(recebe_nome1);
                                int muda_menu = -1;
               }



            }
            }
            while(muda_menu!=0){
                
                muda_menu = Integer.parseInt(sin.readLine());
                switch(muda_menu){
                
                    case 1:{
                    	String
                        out.println("Estatisticas|"+recebe_nome1);
                        System.out.println(current = input.readLine());
                        break;}

                    case 2:{
                    	menu_jogo();
                    	Int escHeroi = 1;
                    	escHeroi = Integer.parseInt(escHeroi);
                    	break;
                    	while(escHeroi == 1){

                    		menu_heroi();
                    		String recebe_heroi = ler.readLine();
                    		break;

                    	}
                    	menu_jogo_heroi();
                    break;}
            }catch (IOException ex) {
            System.out.println("[SERVIDOR INDISPONIVEL, TENTE MAIS TARDE!]");
        }

    }


}
