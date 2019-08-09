import java.util.Scanner;
import java.lang.Integer;
/**
 * Escreva a descrição da classe Ex2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex2
{
   public static void main(String[] args){
       int a,b;
       double m;
       Scanner ler = new Scanner(System.in);
       
       System.out.println("Introduza um numero!");
       a=ler.nextInt();
       System.out.println("Introduza outro numero!");
       
       b=ler.nextInt();
       
       m=((double) a+ b)/2;
       if(a>b){
           System.out.println(+b+" é menor que "+a+" e a média entre os dois números é "+m+".");} 
           else{
               System.out.println(+a+" é menor que "+b+" e a média entre os dois números é "+m+".");}
            }
}
