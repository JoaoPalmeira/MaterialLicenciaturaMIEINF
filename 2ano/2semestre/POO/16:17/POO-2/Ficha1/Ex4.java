
/**
 * Escreva a descrição da classe Ex4 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;
import java.lang.Math;
public class Ex4
{
    public static void main(String[] args)
    {
        int n;
        Scanner ler = new Scanner(System.in);
        System.out.println("Diga quantos numeros reais quer");
        n = ler.nextInt();
        System.out.println("Diga "+n+" numeros reais");
        int i;
        double j;
        for (i=0;i<n;i++){
            j = ler.nextFloat();
            if (j>0) {
                j = Math.sqrt(j);
                System.out.println(j);
            }
            else {
                System.out.println("Número imaginário");
            }
        }
    }
}
