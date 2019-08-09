import java.util.Scanner;
import java.util.Arrays;

/**
 * Escreva a descrição da classe ex4 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class ex4
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
        int x, n = f-i + 1, k=0;
        int tam = arr.length;
        int[] num= new int[n];
        
        if(i<0 && f<0 && i>tam && f>tam) {
            
            System.out.println("Indices invalidos");
        }
        
        while(i<=f) {
            
            num[k] = arr[i];
            i++;
            k++;
            
        }
        
        return num;
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
