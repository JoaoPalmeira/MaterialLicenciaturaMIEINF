
/**
 * Escreva a descrição da classe Ex18 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;
import java.util.GregorianCalendar;
public class Ex18
{
    public static long main ()
    {
      Scanner ler = new Scanner(System.in);
      System.out.println("Digite o seu ano de nascimento!");
      int ano;
      ano = ler.nextInt();
      System.out.println("Digite o mês que nasceu!");
      int mes;
      mes = ler.nextInt();
      System.out.println("E Digite o dia!");
      int dia;
      dia = ler.nextInt();
      GregorianCalendar data = new GregorianCalendar(ano,mes-1,dia);
      GregorianCalendar calc = new GregorianCalendar();
      System.out.println(calc.get(calc.DAY_OF_MONTH)+"/"+(calc.get(calc.MONTH)+1)+"/"+calc.get(calc.YEAR)+" "+calc.get(calc.HOUR_OF_DAY));
      long dif = calc.getTimeInMillis()-data.getTimeInMillis();
      return (((dif/1000)/60)/60);
    }
}
