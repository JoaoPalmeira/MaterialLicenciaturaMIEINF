import java.io.IOException;
import java.util.Scanner;

/**
 * Escreva a descrição da classe ImoobiliariaApp aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class ImoobiliariaApp
{
    // Construtor privado
    private ImoobiliariaApp() {}
    
    private static Imoobiliaria estado;
    //Menus da aplicação
    private static Menu menumain, menuadC, menuadV;
    
    
    //Método principal
    public static void main(String[] args){
        carregarMenus();
        carregarDados();
        do {
            menumain.executa();
            switch(menumain.getOpcao()) {
                case 1: registaU();
                       break;
                case 2: iniciaS();
                       break;
                case 3: setFav();
                       break;
                case 4: getFav();
                       break;
                case 5: registaImov();
                       break;
                case 6: setEst();
                       break;
                case 7: getTopImov();
                       break;
                case 8: getImov();
                       break;
                case 9: getHab();
                       break;
                case 10: getMap();
                       break;
                case 11: fechaS();
                       break;
            }
        } while (menumain.getOpcao()!=0);
        try{
            estado.gravaObj("estado.imob");
            estado.log("log.txt", true);
        }
        catch (IOException e) {
            System.out.println("Não foi possível gravar os dados!");
        }
        System.out.println("Até à próxima!...");
    }
    
    //Métodos auxiliares
    
    private static void carregarMenus(){
        String[] ops = {"Registar Utilizador",
                        "Iniciar Sessão",
                        "Marcar um imóvel como favorito",
                        "Consultar os imóveis favoritos ordenados por preço",
                        "Colocar um imóvel à venda",
                        "Alterar o estado de um imóvel",
                        "Obter um conjunto com os códigos dos imóveis mais consultados",
                        "Consultar a lista dos imóveis de um tipo e até um certo preço",
                        "Consultar a lista dos imóveis habitáveis até um certo preço",
                        "Obter um mapeamento entre todos os imóveis e respetivos vendedores",
                        "Fechar Sessão"
                       };
        
        menumain = new Menu(ops);
    }
    
    private static void carregarDados(){
        try {
            estado = Imoobiliaria.leObj("estado.imob");
        }
        catch (IOException e) {
            estado = new Imoobiliaria();
            System.out.println("Não consegui ler os dados!\nErro de leitura.");
        } 
        catch (ClassNotFoundException e) {
            estado = new Imoobiliaria();
            System.out.println("Não consegui ler os dados!\nFicheiro com formato desconhecido.");
        }
        catch (ClassCastException e) {
            estado = new Imoobiliaria();
            System.out.println("Não consegui ler os dados!\nErro de formato.");        
        }
    }
    
    private static void registaU() {
        Utilizador ut;
        Scanner scin = new Scanner(System.in);
 
        String email, nome, password, morada, dataNascimento, tipoUtilizador;
            
        System.out.print("E-mail: ");
        email = scin.nextLine();
        System.out.print("Nome: ");
        nome = scin.nextLine();
        System.out.print("Password: ");
        password = scin.nextLine();
        System.out.print("Morada: ");
        morada = scin.nextLine();
        System.out.print("Data de Nascimento: ");
        dataNascimento = scin.nextLine();
        System.out.print("Tipo de Utilizador: ");
        tipoUtilizador = scin.nextLine();
            
        ut = new Utilizador(email, nome, password, morada, dataNascimento, tipoUtilizador);
        try {
            estado.registarUtilizador(ut);
        } 
        catch (UtilizadorExistenteException e) {
            System.out.println(e.getMessage());
            ut = new Utilizador();
        }
        scin.close();
    }
    
    private static void iniciaS() {

        Scanner scin = new Scanner(System.in);
            
        String email, password;
            
        System.out.print("E-mail: ");
        email = scin.nextLine();
        System.out.print("Password: ");
        password = scin.nextLine();
            
        try {
             estado.iniciaSessao(email, password);
        } catch (SemAutorizacaoException e){
            System.out.println(e.getMessage());
        }
        scin.close();
    }
            
    private static void setFav(){
        Scanner scin = new Scanner(System.in);
        
        String idIm;
        System.out.print("Código do imóvel a marcar como favorito: ");
        idIm = scin.nextLine();
        
        try {
            estado.setFavorito(idIm);
        } 
        catch (ImovelInexistenteException e) {
            System.out.println(e.getMessage());
        }
        catch (SemAutorizacaoException f){
            System.out.println(f.getMessage());
        }
        scin.close();
    }
      
    private static void getFav(){
       try{
           estado.getFavoritos();
       }
       catch (SemAutorizacaoException e){
            System.out.println(e.getMessage());
       }
    }
    
    private static void registaImov(){
        Imovel im;
        Scanner scin = new Scanner(System.in);
 
        String codigo, tipoImovel, rua, estado, mail;
        double precoPedido, precoMinimo;
        int visualizacoes;
            
        System.out.print("Código do imóvel: ");
        codigo = scin.nextLine();
        System.out.print("Tipo do imóvel: ");
        tipoImovel = scin.nextLine();
        System.out.print("Rua: ");
        rua = scin.nextLine();
        System.out.print("Estado do imóvel: ");
        estado = scin.nextLine();
        System.out.print("Preço pedido: ");
        precoPedido = scin.nextDouble();
        System.out.print("Preço mínimo: ");
        precoMinimo = scin.nextDouble();
        System.out.print("Número de visualizações: ");
        visualizacoes = scin.nextInt();
        System.out.print("Mail: ");
        mail = scin.nextLine();
            
        im = new Imovel(codigo, tipoImovel, rua, estado, precoPedido, precoMinimo, visualizacoes, mail);
        /*
        try {
            estado.registaImovel(im);
        } 
        catch (ImovelExistenteException e) {
            System.out.println(e.getMessage());
            im = new Imovel();
        }
        catch (SemAutorizacaoException f){
            System.out.println(f.getMessage());
        }
        */
        scin.close();
    }
    
    private static void getTopImov(){
        Scanner scin = new Scanner (System.in);
        
        int n;
        System.out.print("Número de consultas mínimo: ");
        n = scin.nextInt();
        
        estado.getTopImoveis(n);
        scin.close();
    }
    
    private static void setEst(){
        Scanner scin = new Scanner(System.in);
        
        String idIm, est;
        System.out.print("Código do imóvel a alterar o estado: ");
        idIm = scin.nextLine();
        System.out.print("Estado final do imóvel com código "+idIm+": ");
        est = scin.nextLine();
        
        try {
            estado.setEstado(idIm, est);
        } 
        catch (ImovelInexistenteException e) {
            System.out.println(e.getMessage());
        }
        catch (SemAutorizacaoException f){
            System.out.println(f.getMessage());
        }
        catch (EstadoInvalidoException g){
            System.out.println(g.getMessage());
        }
        scin.close();
    }
    
    private static void getImov(){
        Scanner scin = new Scanner(System.in);
        
        String tipo;
        int preco;
        System.out.print("Tipo do imóvel a consultar: ");
        tipo = scin.nextLine();
        System.out.print("Preço máximo dos imóveis de tipo"+tipo+": ");
        preco = scin.nextInt();
        
        estado.getImovel(tipo, preco);
        scin.close();
    }
    
    private static void getHab(){
        Scanner scin = new Scanner(System.in);
        int preco;
        System.out.print("Preço máximo dos imóveis habitáveis: ");
        preco = scin.nextInt();
        
        estado.getHabitaveis(preco);
        scin.close();
    }
   
    private static void getMap(){
        estado.getMapeamentoImoveis();
    }
    
    private static void fechaS() {
        estado.fechaSessao();
        menumain.executa();
    }
}