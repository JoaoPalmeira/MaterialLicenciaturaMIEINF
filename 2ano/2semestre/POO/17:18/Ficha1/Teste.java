
/**
 * Classe de teste.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;

public class Teste
{
    public static void main(String[] args){
        Ficha1 f = new Ficha1();
        Scanner sc = new Scanner(System.in);
        
        //Celsius -> Farenheit
        System.out.print("Graus Celsius = :");
        double grCs = sc.nextDouble();
        double grFr = f.celsiusParaFarenheit(grCs);
        System.out.println("Graus Farenheit = :" + grFr);
        
    }
}
