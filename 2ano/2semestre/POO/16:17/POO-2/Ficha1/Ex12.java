
/**
 * Escreva a descrição da classe Ex12 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;
import java.lang.Math;
public class Ex12
{
   public static void main (int x, int y)
   {
       int acomulador = x+y;
       int contador = 2;
       int j=0;
       int diferença=Math.abs(y-x);
       int anterior=y;
       int dia1=1;
       int dia2=2;
       Scanner ler = new Scanner(System.in);
       System.out.println("Digite mais temperaturas");
       while (j>-90 && j<57)
       {
           j=ler.nextInt();
           if (j>-90 && j<57){
               contador++;
               acomulador += j;
               int nDif = Math.abs(anterior-j);
               if (diferença<nDif) 
               {
                   diferença=nDif;
                   dia1=contador-1;
                   dia2=contador;
               }
               anterior = j;
           }
        }
       int media = acomulador/contador;
       System.out.println("A média das "+contador+" temperaturas foi de "+media+" graus.");
       System.out.println("A maior variação registou-se entre os dias "+dia1+" e "+dia2+", tendo a temperatura subido/descido "+diferença+" graus.");
   }
}
