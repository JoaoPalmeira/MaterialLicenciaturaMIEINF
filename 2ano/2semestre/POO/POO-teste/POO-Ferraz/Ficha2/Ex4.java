import java.util.Scanner;
import java.util.Arrays;
/**
 * Escreva a descrição da classe Ex4 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex4
{
     private static int[] lerArrayInt(int n){
        int[] num= new int[n];
        int i, valor;
        Scanner in = new Scanner(System.in);
        
        for(i=0;i<num.length;i++){
            System.out.println("Qual o valor?");
            valor = in.nextInt();
            num[i]=valor;
        }
      /**  for(i=0;i<num.length;i++){
            System.out.println("Elemento "+i+"= "+num[i]);
        
    }*/
    return num;
}
private static int[] subArray(int[] arr, int i, int f){
     int min;

     int[] sub=new int[f-i+1];
     for(min=0; min<sub.length && i<=f;min++,i++){
         sub[min]=arr[i];
        }
    
         return sub;
}
public static void main(String[] args){
        int n, min,i,f;
        Scanner in = new Scanner(System.in);
        System.out.println("Quantos valores quer inserir?");
        n = in.nextInt();
        System.out.println("Introduza o indice mais baixo");
        i= in.nextInt();
        System.out.println("Introduza o indice mais alto");
        f=in.nextInt();
        
        if(i>f){
            System.out.println("ERRO");
        System.exit(0);
       }
        int[] arr= new int[n];
        arr=lerArrayInt(n);
        System.out.println(Arrays.toString(subArray(arr,i,f))); 
}
}