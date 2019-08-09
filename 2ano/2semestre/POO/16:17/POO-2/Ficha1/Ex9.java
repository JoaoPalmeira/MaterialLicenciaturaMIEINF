
/**
 * Escreva a descrição da classe Ex9 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;
import java.util.GregorianCalendar;
public class Ex9
{
  public static void main(String[] args)
  {
      Scanner ler = new Scanner(System.in);
      System.out.println("Digite um ano!");
      int ano;
      ano = ler.nextInt();
      System.out.println("Digite um mes!");
      int mes;
      mes = ler.nextInt();
      System.out.println("Digite um dia!");
      int dia;
      dia = ler.nextInt();
      GregorianCalendar data = new GregorianCalendar(ano,mes-1,dia);
      int diaDaSemana = data.get(data.DAY_OF_WEEK);
      if (diaDaSemana==1) System.out.println("Domingo!");
      if (diaDaSemana==2) System.out.println("Segunda!");
      if (diaDaSemana==3) System.out.println("Terça!");
      if (diaDaSemana==4) System.out.println("Quarta!");
      if (diaDaSemana==5) System.out.println("Quinta!");
      if (diaDaSemana==6) System.out.println("Sexta!");
      if (diaDaSemana==7) System.out.println("Sabado!");
  }
}

