
/**
 * Escreva a descrição da classe Ex2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;
public class Ex2
{
    public static void ordena (int i1, int i2)
   {
       int media = (i1+i2)/2;
       if (i1>i2)
       System.out.println (i2 + " "+ i1 +" e a média é: " + media);
       else
       System.out.println (i1 + " " + i2 +" e a média é: " + media);
    }
    public static void main(String[] args)
    {
        int a, b;
        Scanner ler = new Scanner (System.in);
        
        System.out.print("Indique dois inteiros: ");
        a = ler.nextInt();
        b = ler.nextInt();
        Ex2.ordena (a,b);
        ler.close();
   }
}
