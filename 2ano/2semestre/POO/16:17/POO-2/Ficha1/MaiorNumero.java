
/**
 * Escreva a descrição da classe MaiorNumero aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;   
public class MaiorNumero
{
   public static void dizMaior (int i1, int i2)
   {
       if (i1>i2)
       System.out.println ("O maior é  "+ i1);
       else
       System.out.println ("O maior é " + i2);
    }
    public static void main(String[] args)
    {
        int a, b;
        Scanner ler = new Scanner (System.in);
        
        System.out.print("Indique dois inteiros: ");
        a = ler.nextInt();
        b = ler.nextInt();
        MaiorNumero.dizMaior (a,b);
        ler.close();
   }
}
