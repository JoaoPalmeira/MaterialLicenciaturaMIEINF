import java.util.Scanner;
/**
 * Escreva a descrição da classe Ex3 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex3
{
   public static void main(String[] args){
       Scanner ler= new Scanner(System.in);
       int i;
       int s=0;
       int n;
      /* System.out.println("Introduza um número");
       n=ler.nextInt();
       if (n>5){
           s++;
       }*/
       for(i=1;i<10;i++){
           System.out.println("Introduza um número");
           n=ler.nextInt();
           if (n>5){
               s++;
            }
   
        }
        System.out.println("Existem "+s+"números superiores a 5");
    }
               
       
       
       
       
       
       
}
