
/**
 * Classe Imoobiliaria: início e controlo de todas as operações 
 */
import java.lang.*;
import java.util.*;
import java.io.*;

public class Imoobiliaria
{
    //private String clientFile = "Clientes.txt";
    public Vector<Utilizador> users = new Vector<Utilizador>();
    public Imoobiliaria () { MenuInicial(); }
    private void MenuInicial() {
        System.out.println("______________________");
        System.out.println(" --- Imoobiliaria --- ");
        System.out.println("______________________");
        System.out.println("'R'egistar   'E'ntrar"); 
        System.out.println("'L'istar utils registados");
        Scanner input = new Scanner(System.in);
        String log1;
                
        log1 = input.next();
        
        while (!log1.equals("R") && !log1.equals("E") && !log1.equals("L")) { 
            System.out.println("Char introduzido não correspondente.");
            log1 = input.next();
        }    
        //Linhas de organização
        System.out.printf("\n \n \n \n ");
        if (log1.equals("R")) MenuRegUtil();
        else if (log1.equals("E")) MenuInitSessao();
        else if (log1. equals("L")) {
            showUsers();
            MenuInicial();
        }
    }
    private void MenuRegUtil() {
        String m,n,pw,mo,tipo;
        int d;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Pretende registar-se na plataforma Imoobiliaria. Para finalizar, são necessários os seguintes dados:");
        System.out.print("E-Mail: ");
        m = input.nextLine();
        for (int i = 0; i < users.size(); i++) {
            while (m.equals(users.get(i).getMail())) {
                System.out.println("O e-mail inserido consta da base de dados. Por favor tente novamente.");
                m = input.nextLine();
            }
        }
        System.out.print("Nome: "); 
        n = input.nextLine();
        System.out.print("Password: ");
        pw = input.nextLine();
        System.out.print("Morada: ");
        mo = input.nextLine();
        System.out.print("Data de Nascimento (DDMMAAA): ");
        d = input.nextInt();
        System.out.print("'V'endedor / 'C'omprador");
        //tipo = input.nextInt();
        
        Utilizador novo = new Utilizador(m,n,pw,mo,d);
        
        registarUtilizador(novo);
        MenuInicial();
    }
        
    private void MenuInitSessao() {
        String mail;
        String pass;
        Scanner input2 = new Scanner(System.in);
        System.out.println("Escolheu iniciar sessão como utilizador registado.");
        System.out.print("E-mail: ");
        mail = input2.next();
        System.out.print("Password: ");
        pass = input2.next();
        
        //iniciaSessao(mail,pass);
    }
    
    private void MenuVendedor(){
        // olá, nome de utilizador
        int n;
        Scanner num = new Scanner(System.in);
        System.out.printf("1. Consultar informação própria\n2. Colocar Imóvel à venda\n3.Alterar estado de Imóvel\n4. Ver Imovéis mais consultados.");
        n = num.nextInt();
        
        //case
        
    }
    private void registarUtilizador(Utilizador utilizador) {
        users.add(utilizador);
        System.out.println("Utilizador registado com sucesso.");
    }
    private void showUsers() {
        System.out.println("Lista de Utilizadores Registados:");
        for (int i = 0; i<users.size(); i++) {
            users.get(i).showUtilizador();
            System.out.print("\n");
        }
    }
        /*System.out.println("Tamanho: " + users.size());
        System.out.println("Capacidade: " + users.capacity());
        System.out.println(" nome 0: " + users.get(0).getNome());*/
}
    
    //private void MenuComprador()
        
    //
    //public void iniciaSessao(String email, String password) confere na lista dos utilizadores inscritos 
      // o email e a pass, se coincidirem, lança o respetivo menu.
    /**
     * Ler de ficheiro txt para Arraylist os ids de imóveis.
     * Ler para lista de imóveis o resto da informação.
     
      
    private void lerFichs() {
        
        PrintWriter wr = null;
        try {
            wr = new PrintWriter(clientFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int c = 1; c <=10; c++) {
            wr.println("Linha " + c);
        }
        
        wr.close();
    }*/

