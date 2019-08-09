import java.util.Scanner;
import java.util.Arrays;
/**
 * Escreva a descrição da classe Ficha2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ficha2
{
   static Scanner sc = new Scanner(System.in);
   
   public static void ex2(){
       System.out.println("Insira números:");
       int n = sc.nextInt();
       System.out.println("Insira " + n + " nrs:");
       int[] a = lerArrayInt(n);
       int indice = minPos(a);
       System.out.println("Indice: " + indice);
   }
   /**
    * Método que lê N inteiros
    * @author (seu nome)
    * @return 0 array lido
    */
   private static int [] lerArrayInt (int n) {
       int[] array = new int[n];
       for(int i=0; i<n; i++) {
           array[i]=sc.nextInt(); 
       }   
       return array;
   } 
   public static int minPos(int[] arr){
        Arrays.sort(arr);
        return arr[0];
   }
}