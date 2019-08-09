import java.util.Scanner;
import java.util.Arrays;
/**
 * Escreva a descrição da classe Ex3 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex3
{
     private static int[] lerArrayInt(int n){
        int[] num= new int[n];
        int i,j, valor, aux;
        Scanner in = new Scanner(System.in);
        System.out.println("Qual o primeiro valor?");
        valor = in.nextInt();
        num[0]=valor;
        for(i=1;i<num.length;i++){
            System.out.println("Qual o valor?");
            valor = in.nextInt();
            num[i]=valor;
            for(j=0;j<=i;j++){
              if(num[j]<num[i]){
                aux=num[j];
                num[j]=num[i];
                num[i]=aux;
        }
    }
    
}
 /**for(i=0;i<num.length;i++){
            System.out.println("Elemento "+i+"= "+num[i]);
        
    }*/
return num;
}
public static void main(String[] args){
        int n, min,i;
        Scanner in = new Scanner(System.in);
        System.out.println("Quantos valores quer inserir?");
        n = in.nextInt();
        int[] arr= new int[n];
        arr=lerArrayInt(n);
        System.out.println(Arrays.toString(arr)); 
}
}