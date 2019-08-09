import java.util.Scanner;
/**
 * Escreva a descrição da classe Ex14 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex14
{
 /**  public static void primo(int n){
       
       int d=2;
      
       while((n/d)> d &&n %d!=0 && n!=d){
           d++;
           
        
        
      if(n%d==0 && n!=d){
          System.out.println("O número não é primo!");
        }
        else{
            System.out.println("O número é primo");
        }
        n--;
    }
   
}*/
    public static void main(String[] args){
       int n;
       int p;
       Scanner in= new Scanner(System.in);
       System.out.println("Introduza um número");
       n=in.nextInt();
       int d=2;
       int r=n/d;
       while(r> d &&n %d!=0 && n!=d){
           r=(n/d);
           d++;
           
        
        
      if(n%d==0 && n!=d){
          System.out.println("O número não é primo!");
        }
        else{
            System.out.println("O número é primo");
        }
        n--;
    }
   
       
    
    
    
    
}
}