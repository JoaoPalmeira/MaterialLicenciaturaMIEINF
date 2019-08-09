
/**
 * Escreva a descrição da classe Ex17 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.lang.Math;
public class Ex17
{
    public static void main ()
    {
         System.out.printf("Tente adivinhar um dos meus dois numeros.\nCuidado só tem 5 tentativas!!!\n");
         long fact=1,x=10000000;
         GregorianCalendar antes = new GregorianCalendar();
         while(x!=0) {
             fact*=x;
             x--;
         }
         GregorianCalendar depois = new GregorianCalendar();
         long dif = depois.getTimeInMillis()-antes.getTimeInMillis();
         long numero1= dif/4;
         long numero2 = (dif*dif)/45;
         int i;
         Scanner ler = new Scanner(System.in);
         for (i=1;i<6;i++)
         {
             System.out.println("Tentativa "+i+"!");
             long numero = ler.nextLong();
             if(numero==numero1||numero==numero2) 
             {
                 System.out.println("Acertou!!!");
                 i=7;
             }
             else 
             {
                 long diferença1=Math.abs(numero1-numero);
                 long diferença2=Math.abs(numero2-numero);
                 if (diferença1>=diferença2) System.out.println("O Modulo do seu numero e um dos meus é "+diferença2+"!");
                 if (diferença1<diferença2) System.out.println("O Modulo do seu numero e um dos meus é "+diferença1+"!");
             }
         }
         System.out.println("Terminou o jogo!");
    }
}
