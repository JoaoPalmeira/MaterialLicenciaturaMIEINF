
/**
 * Escreva a descrição da classe Ex5 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;
public class Ex5
{
    public static void main(String[] args)
    {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite uma sequencia de numeros terminada em 0");
        int n=1;
        int m=0;
        int maior=0;
        int menor=1;
        while (n!=0) {
        n = ler.nextInt();
        m = m+n;
        if (n>maior) maior = n;
        if (n<menor && n!=0) menor = n;
        }
        System.out.println("A soma é "+m+", o maior elemento é "+maior+" e o menor é "+menor);
    }
}
