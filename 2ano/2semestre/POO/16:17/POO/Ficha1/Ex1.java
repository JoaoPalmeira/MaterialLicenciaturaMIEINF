import java.util.Scanner;
import java.lang.Integer;


/**
 * Escreva a descrição da classe Ex1 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex1
{
    
    public static void main(String[] args){
        String nome;
        double saldo;
        Scanner ler = new Scanner(System.in);
        
        System.out.println("Introduza o nome!");
        nome=ler.next();
        System.out.println("Introduza o saldo!");
        saldo=ler.nextDouble();
        
        System.out.println("O nome corresponde a "+nome+" e o seu saldo é "+saldo+".");
    
    
    }
}
