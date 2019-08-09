
/**
 * Classe de teste interactiva em modo Texto.
 * 
 * @author José Creissac Campos 
 * @version 05/2002 (act. 09/2016)
 */

package jturma;

public class ConsoleTurma {

    // Variáveis de classe
    private final Turma t = new Turma();
    private final String[] opcoes = {"Inserir Aluno","Remover Aluno","Consultar Aluno"};


    // Método principal
    public static void main(String[] args)  {
        (new ConsoleTurma()).run();
    }
    
    // Arraque da app
    private void run() {
        int op;
            
        do {
            op = this.doMenu();
            switch (op) {
                case 1: inserir(); 
                        break;
                case 2: remover();
                        break;
                case 3: consultar();
            }
        } while (op!=0);
        System.out.println("Volte sempre!");
    }
    
    // M�todos auxiliares
    
    /** Inserir um aluno */
    private void inserir() {
        Aluno a;
        String nome, num;
        int nt,np;
        
        nome = Console.readString("Nome: ");
        num = Console.readString("Numero: ");
        nt = Console.readInt("Nota Teórica");
        np = Console.readInt("Nota Prática");
        
        a = new Aluno(num, nome, nt, np);
        
        t.addAluno(a);
    }
    
    /** Consultar um aluno */
    private void consultar() {
        Aluno a;
        String num;
        
        num = Console.readString("Numero: ");
        
        try {
            a = t.getAluno(num);
            System.out.println(a.toString());
            System.out.println("Média: "+a.getMedia());
        }
        catch (TurmaException e) {
            System.out.println("Aluno inexistente!");
        }
    }
    
    /** Remover um aluno */
    private void remover() {
        Aluno a;
        String num;
        
        num = Console.readString("Número: ");
        
        try {
            t.delAluno(num);
            System.out.println("Aluno removido.");
        }
        catch (TurmaException e) {
            System.out.println("Aluno inexistente!");
        }
    }
    
    /** Apresentar Menu e ler opcção */
    private int doMenu() {
        int op;
        
        do {
            // Apresentar o menu
            System.out.println(" *** Menu *** ");
            for (int i=0; i<opcoes.length; i++) {
                System.out.print(i+1);
                System.out.print(" - ");
                System.out.println(opcoes[i]);
            }
            System.out.println("0 - Sair");
        
            // ler opção
            op = Console.readInt("Opção: ");
            if (op<0 || op>opcoes.length)
                System.out.println("Opção Inválida!!!");
        } while (op<0 || op>opcoes.length);
        
        return op;
    }
}
