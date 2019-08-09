
/**
 * Escreva a descrição da classe Ex13 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.lang.Math;
import java.util.Scanner;
import java.lang.String;
public class Ex13
{
   public static void main ()
   {
       Scanner ler = new Scanner(System.in);
       float a=0;
       float b=1;
       while (b!=0)
       {
           System.out.printf("Digite a base!\n");
           b=ler.nextFloat();
           if (b!=0)
           {
               System.out.printf("Digite a altura!\n");
               a=ler.nextFloat();
               float area = (b*a)/2;
               double hipotenusa = Math.sqrt((b*b)+(a*a));
               double perimetro = b+a+hipotenusa;
               System.out.printf("A sua Área é "+area+"e o seu perimetro é"+perimetro+"!\n");
           }
        }
    }
}
