import java.time.LocalDateTime;
import java.util.Scanner;
/**
 * Escreva a descrição da classe Ex7 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex7
{
    public static long factorial(long n){
        long f=1;
        while(n>=1 && n!=0){
            f=f*n;
            n--;
        }
        return f;
        
        
}

public static void main(String[] args){
       long i, f;
       LocalDateTime inicio = LocalDateTime.now();
       System.out.println("Informações do sistema "+inicio);
       System.out.println("Insira um número!");
       Scanner ler = new Scanner(System.in);
       i=ler.nextInt();
   
       if(i==0){
           System.out.println("Não existe fatorial deste número ");
           
        }
        else{
      
       f=factorial(i);
       int hour=inicio.getHour();
      
       
       System.out.println("O factorial de "+i+" é "+f+ "e foi realizado ás "+hour);
    }
}
}
