import java.util.Scanner;
import java.util.Arrays;
/**
 * Escreva a descrição da classe Ex2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex2
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
    private static int minPos(int[] arr){
        int i;
        int min = arr[0];
        for(i=1; i<arr.length;i++){
            if(min>arr[i]){
                min=arr[i];}
            }
            //System.out.println("O menor elemento é "+min);
            return min;
        }

    public static void main(String[] args){
        int n, min,i;
        Scanner in = new Scanner(System.in);
        System.out.println("Quantos valores quer inserir?");
        n = in.nextInt();
        int[] arr= new int[n];
        arr=lerArrayInt(n);
        min = minPos(arr);
        System.out.println(Arrays.toString(arr)); 
        System.out.println("O menor elemento é "+min);
}

}
