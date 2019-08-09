import java.util.Scanner;
import java.text.DecimalFormat;
/**
 * Escreva a descrição da classe Ex13 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex13
{
    public static void main(String[] args){
        double p,q=1;
        double stock = 0;
        double valor=0;
        DecimalFormat df = new DecimalFormat("#.0000");
        System.out.println("Qual o preço do produto?");
        Scanner in= new Scanner (System.in);
        p=in.nextDouble();
        
        while(q!=0){
        System.out.println("Introduza o valor do stock!");
        q=in.nextDouble();
        stock= stock+q;
    }
    valor= p*stock;
    System.out.println("O total em stock é "+df.format(stock)+" e o seu valor é "+df.format(valor));
}
}