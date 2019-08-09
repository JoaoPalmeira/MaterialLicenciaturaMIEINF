
/**
 * Classe Imoobiliaria: início e controlo de todas as operações 
 */
import java.lang.*;
import java.util.*;
import java.io.*;

public class Imoobiliaria extends Imovel
{
    //private String clientFile = "Clientes.txt";
    public Vector<Utilizador> users = new Vector<Utilizador>();
    public ArrayList<Imovel> imoveisGeral = new ArrayList<Imovel>();
    public Imoobiliaria () { initApp(); }
    private void initApp() { MenuInicial(); }
    private void MenuInicial() {
        System.out.println("______________________");
        System.out.println(" --- Imoobiliaria --- ");
        System.out.println("______________________");
        System.out.println("'R'egistar   'E'ntrar"); 
        System.out.println("'L'istar Utilizadores");
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
        System.out.print("Nome: "); 
        n = input.nextLine();
        System.out.print("Password: ");
        pw = input.nextLine();
        System.out.print("Morada: ");
        mo = input.nextLine();
        System.out.print("Data de Nascimento (DDMMAAA): ");
        d = input.nextInt();
        System.out.print("'V'endedor / 'C'omprador: ");
        tipo = input.next();
        
        while (!tipo.equals("V") && !tipo.equals("C")) {
            System.out.println("\nTipo de Utilizador não reconhecido. Tente novamente.");
            tipo = input.next();
        }
        Utilizador novo;
        if (tipo.equals("V")) {
            novo = new Vendedor(m,n,pw,mo,d);
        } else {
            novo = new Comprador(m,n,pw,mo,d);
        }
        try {
            registarUtilizador(novo);
        }
        catch (UtilizadorExistenteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
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
    
    public class UtilizadorExistenteException extends Exception {
        public UtilizadorExistenteException(String m) {
            super(m); // Não funciona corretamente.
        }
    }
    
    private void registarUtilizador(Utilizador utilizador) throws UtilizadorExistenteException {
        
        for (int i = 0; i < users.size(); i++) {
            if (utilizador.getMail().equals(users.get(i).getMail())) {
                throw new UtilizadorExistenteException("Mail já existente!");
            }
        }
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
    
    public class ImovelInexistenteException extends Exception {
        public ImovelInexistenteException(String m) {
            super(m); // Não funciona corretamente.
        }
    }
    public class SemAutorizacaoException extends Exception {
        public SemAutorizacaoException(String m) {
            super(m); // Não funciona corretamente.
        }
    }
    
    //isto
    public void setEstado ( String idImovel , String estado ) throws ImovelInexistenteException, SemAutorizacaoException ,EstadoInvalidoException {

        if (this.getClass().getSimpleName().equals("Comprador")) throw new SemAutorizacaoException("Permissão Negada");
        
        for (int i = 0; i < imoveisGeral.size(); i++) {
            if (imoveisGeral.get(i).getId().equals(idImovel)){
                switch (estado.toLowerCase()) {
                    case "venda":
                        Estado v = Estado.VENDA;
                        imoveisGeral.get(i).setEstadoAux(v);
                    case "aluguer":
                        Estado a = Estado.ALUGUER;
                        imoveisGeral.get(i).setEstadoAux(a);
                    case "trespasse":
                        Estado t = Estado.TRESPASSE;
                        imoveisGeral.get(i).setEstadoAux(t);
                    case "ocupado":
                        Estado o = Estado.OCUPADO;
                        imoveisGeral.get(i).setEstadoAux(o);
                    default: throw new EstadoInvalidoException("Estado inválido.");
                }
            }
            else if (i == imoveisGeral.size()-1) throw new ImovelInexistenteException ("O imóvel não existe.");
        }
         
    }
}

