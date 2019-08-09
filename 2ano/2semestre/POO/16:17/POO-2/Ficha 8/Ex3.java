import java.util.Scanner;
public class Ex3
{
    public static void main(int N)
    {
        int[] v;
        v = new int[N];
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite os elementos do vetor!");
        int i=0;
        while(i<N)
        {
            v[i]=ler.nextInt();
            if(v[i]%2==0) System.out.println("O indice "+i+" do vetor é "+v[i]+" e é par");
            i++;
        }
    }
}
