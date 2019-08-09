
/**
 * Escreva a descrição da classe Array aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;
import java.util.Arrays;
public class Array
{
   public static void Ex1 (int [] arr)
   {
       System . out . println ( " --- ELEMENTOS DO ARRAY ---" );
       for ( int i = 0; i < arr . length ; i ++) {
           System . out . println ( " Elemento " + i + " = " + arr [ i ]) ;
        }
       System . out . println ( " - - - - - - - - - - - - - - - - - - - - - - - - - - " ) ; 
    }
   private static int[] lerArrayInt(int n)
   {
       int [] arr;
       arr = new int[n];
       int j;
       Scanner ler = new Scanner(System.in);
       System.out.println("Introduza "+n+" numeros");
       for (int i=0;i<n;i++)
       {
           j = ler.nextInt();
           arr[i]=j;
       }
       return arr;
    }
   private static int minPos(int[] arr)
   {
       int menor = arr[0];
       int pos = 0;
       int i;
       for (i=1;i<arr . length;i++){
       if (arr[i]<menor) {menor=arr[i]; pos=i;};
       }
       System.out.println("O menor elemento é "+menor+"!");
       return pos;
   }
   public static void Ex2 (int n)
   {
       int [] arrLido = lerArrayInt (n);
       int numero = minPos (arrLido);
       System.out.println("E a sua posição no array é "+numero+"!");
   }
   private static int[] ordena (int[] arr,int n)
   {
       int menor = arr[0];
       int aux,i,pos=0;
       for (i=1;i<n;i++)
       {
           if(arr[i]<menor) {menor = arr[i]; pos=i;}
       }
       aux=arr[n-1];
       arr[pos]=aux;
       arr[n-1]=menor;
       if (n==1);
       else arr = ordena (arr,n-1);
       return arr;
   }
   public static int[] Ex3 (int n)
   {
       int [] arr;
       arr = new int[n];
       int j;
       System.out.println("Introduza "+n+" numeros");
       Scanner ler = new Scanner(System.in);
       j = ler.nextInt();
       arr[0]=j;
       for (int i=1;i<n;i++)
       {
           j = ler.nextInt();
           arr[i]=j;
       }
       arr = ordena (arr,n);
       return arr;
   }
   public static int[] Ex4 (int[] arr,int menor, int maior)
   {
       int [] arrFinal;
       arrFinal = new int[arr.length];
       int pos = 0;
       int i;
       for (i=0;i<arr.length;i++)
       {
           if (arr[i]>=menor&&arr[i]<=maior) arrFinal[i]=arr[i];
           else arrFinal[i] = 0;
       }
       for (i=0;i<arrFinal.length;i++) 
       {
           if (arr[i]==0) ;
       }
       return arrFinal;
   }
}
