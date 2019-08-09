import java.util.Scanner;

/**
 * Escreva a descrição da classe Ex5 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex5
{
    public static void main(String[] args){
        Scanner ler= new Scanner(System.in);
        int soma=0;
        int maior=Integer.MIN_VALUE;
        int menor=Integer.MAX_VALUE;
        int n=1000;
       
        while(n!=0){
            System.out.println("Introduza um número");
            n=ler.nextInt();
            if (n>maior && n!=0){
                maior=n;
            }
            if(n<menor && n!=0){
                menor=n;
            }
            soma= soma+n;
        }
        
        System.out.println("A soma é "+soma+" o maior número é "+maior+" e o menor número é "+menor+".");
        
        
        
        
        
        
        
        
        
        
        
        
        
}
}
