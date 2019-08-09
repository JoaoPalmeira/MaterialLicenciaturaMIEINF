import java.util.Scanner;
public class Ex2
{
    public static void main(int N, int M)
    {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite uma sequencia de N idades!");
        int idade=0;
        double media=0.0;
        int contador=0;
        while(contador<N)
        {
            idade=ler.nextInt();
            contador++;
            media+=idade;
            if(idade>M) System.out.println("Idade: "+idade);
        }
        System.out.println("A media da sequencia é "+media/contador);
    }
}