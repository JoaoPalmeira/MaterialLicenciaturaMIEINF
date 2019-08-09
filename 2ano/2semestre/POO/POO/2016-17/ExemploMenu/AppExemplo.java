
/**
 * Exemplo de aplicação com menu em modo texto.

 * 
 * @author José Creissac Campos
 * @version 1.0
 */

import java.io.IOException;
import java.util.Scanner;

public class AppExemplo {

    // A classe MyLog tem a 'lógica de negócio'.
    private MyLog logNegocio;
    // Menus da aplicação
    private Menu menuPrincipal;
    
    /**
     * O método main cria a aplicação e invoca o método run()
     */
    public static void main(String[] args) {
        new AppExemplo().run();
    }
    
    /**
     * Construtor.
     * 
     * Cria os menus e a camada de negócio.
     */
    
    private AppExemplo() {
        // Criar o menu 
        String[] opcoes = {"Registar valor",
                         "Consultar último valor",
                         "Listar Log de Valores"};
        this.menuPrincipal = new Menu(opcoes);
        // Criar a lógica de negócio
        logNegocio = new MyLog();  // Aqui poder-se-iam ler os dados de ficheiro.
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    private void run() {
        do {
            menuPrincipal.executa();
            switch (menuPrincipal.getOpcao()) {
                case 1: trataRegistarValor();
                        break;
                case 2: trataConsultarUltValor();
                        break;
                case 3: trataListarLog();
            }
        } while (menuPrincipal.getOpcao()!=0); // A opção 0 é usada para sair do menu.
        // Aqui poder-se-iam gravar os dados em ficheiro...
        System.out.println("Até breve!...");     
    }
    
    // Métodos auxiliares
    
    /**
     * Exemplo de método com input
     */
    private void trataRegistarValor() {
        int i;
        Scanner scin = new Scanner(System.in);
        
        System.out.print("Valor: ");
        i = scin.nextInt();
        logNegocio.registaValor(i);
        scin.close();
    }
    
    /**
     * Exemplo de método com output
     */
    private void trataConsultarUltValor() {
        System.out.println("Último valor: "+logNegocio.getUltValor());
    }
    
    private void trataListarLog() {
        System.out.println(logNegocio.getValores());
    }
    
}


