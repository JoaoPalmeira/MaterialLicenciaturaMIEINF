import java.util.Scanner;

/**
 * Escreva a descrição da classe Ex6 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex6
{
    public static int factorial(int n){
        int f=1;
        while(n>=1 && n!=0){
            f=f*n;
            n--;
        }
        return f;
        
        
}

    public static void main(String[] args){
       int i, f;
       
       System.out.println("Insira um número!");
       Scanner ler = new Scanner(System.in);
       i=ler.nextInt();
       if(i==0){
           System.out.println("Não existe fatorial deste número");
        }
        else{
      
       f=factorial(i);
       System.out.println("O factorial de "+i+" é "+f+".");
    }
        
        
    
    }
}