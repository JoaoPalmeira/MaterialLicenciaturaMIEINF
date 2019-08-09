
/**
 * Escreva a descrição da classe Ex1 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;
public class Ex1
{
    public static void dizSaldo (int saldo, String nome)
    {
      System.out.println(nome + "o seu saldo é "+ saldo);
    }
    public static void main(String[] args)
    {
        int saldo;
        String nome;
        Scanner ler = new Scanner (System.in);
        
        System.out.print("Indique o seu nome e o seu saldo: ");
        nome = ler.next();
        saldo = ler.nextInt();
        Ex1.dizSaldo (saldo,nome);
        ler.close();
    }
}
