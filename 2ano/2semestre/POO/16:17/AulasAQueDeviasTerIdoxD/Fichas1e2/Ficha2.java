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
  
  public static void ex1 (){ 
    // declarar , inicializar e imprimir os elementos de um array
    int [] lista = {12 , 2, 45, 66, 7, 23, 99};
    System . out . println (" --- ELEMENTOS DO ARRAY ---");
    for ( int i = 0; i < lista . length ; i ++) {
        System . out . println (" Elemento "+ i + "= " + lista [i]);
    }
    System . out . println (" --------------------------");
    
    // solução alternativa usando método da classe Arrays
    //int [] lista = {12 , 2, 45, 66, 7, 23, 99};
    //System . out . println ( Arrays . toString ( lista ));
  }
  
  private static int[] lerArrayInt(int n){
    Scanner input = new Scanner(System.in);
    int num = 0;
    int [] array = new int[n];  
    for (int i=0; i<n; i++){
      num = input.nextInt();
      array[i] = num;
    }
    return array;
  }
  
  private static int minPos(int[] arr){
    int minP = 0;
    int min = arr[1];
    for (int i=0; i<arr.length; i++){
        if(arr[i]<min) {min = arr[i]; minP = i;} 
    }
    return minP;
  }
  
  public static void ex2(){
      Scanner sc = new Scanner(System.in);
      System.out.println("Insira tamanho do array:");
      int n = sc.nextInt();
      System.out.println("Insira " + n + " números:");
      int[] a = lerArrayInt(n);
      int indice = minPos(a);
      System.out.println("Posição: " + indice + " Mínimo: " + a[indice]);
  }
  
  private static int[] lerArrayIntOrdDesc(int n){
   Scanner s = new Scanner(System.in);
   int v;
   int [] a = new int[n];  
   for (int i=0; i<n; i++){
      v = s.nextInt();
      if(i==0){ a[i] = v;}
      else{
        int j;
        boolean f = false;
        for(j=0; j<i && !f; j++){
           if(v>a[j]){
               System.arraycopy(a,j,a,j+1,i-j);
               a[j] = v;
               f = true;
           }
        }
      }
   }
   return a;
 }
  
  public static void ex3(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira tamanho do array:");
    int n = sc.nextInt();
    System.out.println("Insira " + n + " números:");
    int[] a = lerArrayIntOrdDesc(n);
    System.out.println(Arrays.toString(a)); 
  }
}
