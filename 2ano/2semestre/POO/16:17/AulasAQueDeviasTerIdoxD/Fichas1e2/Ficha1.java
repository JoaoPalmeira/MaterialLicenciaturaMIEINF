import java.util.Scanner;
/**
 * Escreva a descrição da classe Ficha1 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ficha1
{
     
    
     public static void ex1(String args[]){
         Scanner input = new Scanner(System.in);
         String nome;
         double saldo;
         
         System.out.println("Insira nome e saldo");
        
         nome = input.nextLine();
         saldo = input.nextDouble();
         
         System.out.println("Nome: " + nome + " " + "Saldo: " + saldo);
                
        
     }
     
     public static int ex3(){
         Scanner input = new Scanner(System.in);
         int n1;
         
         int conta = 0;
         
         for(int i=0; i<10; i++){
                 System.out.println("Insira um inteiro");
                 n1 = input.nextInt();
                 if(n1>5) conta++;
         }
         
         return conta;
         
     }
     
     public static void ex5(){
         Scanner input = new Scanner(System.in);
         int n1 = 1;
         
         int soma = 0;
         int maior = 0;
         int menor = 0;
         
         while (n1!=0){
                 System.out.println("Insira um inteiro");
                 n1 = input.nextInt();
                 soma += n1;
                 if (n1>maior) maior = n1;
                 if (n1<menor) menor = n1;
         }
         
         System.out.println("Soma: " + soma + " " + "Maior: " + maior + " " + "Menor: " + menor);
         
     }
    
     public static int ex6(int n){
         int fatorial;
         if(n==0 || n==1) fatorial = 1;
         else fatorial = n*ex6(n-1);
         
         return fatorial;
     }
     
     public static void main(String args[]){
        String s = args[0];
        int v = Integer.parseInt(s);
        int r = ex6(v);
        System.out.println("Factorial: " + r);
     }
}
