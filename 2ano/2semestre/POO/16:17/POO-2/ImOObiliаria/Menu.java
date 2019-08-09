import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu
{
    private List<String> opcoes;
    private int opcao;
    
    public Menu(String[] opcoes)
    {
        this.opcoes = new ArrayList<String>();
        for (String op : opcoes) 
            this.opcoes.add(op);
        this.opcao = 0;
    }
    
    public void executar()
    {
        do{
            mostrarMenu();
            this.opcao = lerOpcao();
        } while (this.opcao == -1);
    }
    
    private void mostrarMenu() 
    {
        System.out.println("\n ------------ Menu ------------ ");
        for (int i=0; i<this.opcoes.size(); i++)
        {
            System.out.print(i+1);
            System.out.print(" -> ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("0 -> Sair");
    }
    
    private int lerOpcao() 
    {
        int op;
        Scanner escolha = new Scanner(System.in);
        System.out.print("Opção: ");
        try 
        {
            op = escolha.nextInt();
        }
        catch (InputMismatchException e) 
        {
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) 
        {
            System.out.println("Opção Inválida!");
            op = -1;
        }
        return op;
    }
    
    public int getOpcao() 
    {
        return this.opcao;
    }
}