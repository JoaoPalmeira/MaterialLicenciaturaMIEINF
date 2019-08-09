
/**
 * Escreva a descrição da classe Ex3 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;
public class Ex3
{
    public static void main (String[] args) 
    {
        int j;
        int conta = 0;
        Scanner ler = new Scanner(System.in);
        System.out.println("Introduza 10 numeros");
        for (int i=0; i<10; i++) {
            j = ler.nextInt();
            if (j>5) conta++;
        }
        System.out.println("Foram introduzidos "+conta+" numeros superiores a 5");
    }
}
