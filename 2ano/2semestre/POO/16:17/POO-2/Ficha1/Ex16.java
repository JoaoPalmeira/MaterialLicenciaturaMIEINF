
/**
 * Escreva a descrição da classe Ex16 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;
public class Ex16
{
   public static void main ()
   {
       System.out.printf("1.- Login\n2.- Registo\n3.- Informações\n0.- Sair\n");
       Scanner ler = new Scanner(System.in);
       int numero = 1;
       while (numero!=0)
       {
           numero = ler.nextInt();
           if (numero==1) login();
           if (numero==2) registo();
           if (numero==3) informacoes();
           if (numero==0) numero=0;
           if (numero>3 || numero<0) System.out.println("Opção Inválida!");
       }
       System.out.println("Saiu!");
    }
   public static void login()
   {
       System.out.println("Login!");
   }
   public static void registo()
   {
       System.out.println("Registo!");
   }
   public static void informacoes()
   {
       System.out.println("Informações!");
   }  
}
