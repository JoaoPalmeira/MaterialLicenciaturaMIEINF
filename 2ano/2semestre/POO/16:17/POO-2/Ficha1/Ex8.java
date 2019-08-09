
/**
 * Escreva a descrição da classe Ex8 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;
public class Ex8
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
        int ano2;
        ano2 = ano - 1900;
        ano2 = ano2 * 365;
        ano2 = ano2 + ((ano-1900)/4);
        if (((ano%4)==0) &&(mes==1||mes==2))  ano2 = ano2-1;
        if (mes==1) ano2=ano2+dia;
        if (mes==2) ano2=ano2+31+dia;
        if (mes==3) ano2=ano2+31+28+dia;
        if (mes==4) ano2=ano2+31+28+31+dia;
        if (mes==5) ano2=ano2+31+28+31+30+dia;
        if (mes==6) ano2=ano2+31+28+31+30+31+dia;
        if (mes==7) ano2=ano2+31+28+31+30+31+30+dia;
        if (mes==8) ano2=ano2+31+28+31+30+31+30+31+dia;
        if (mes==9) ano2=ano2+31+28+31+30+31+30+31+31+dia;
        if (mes==10) ano2=ano2+31+28+31+30+31+30+31+31+30+dia;
        if (mes==11) ano2=ano2+31+28+31+30+31+30+31+31+30+31+dia;
        if (mes==12) ano2=ano2+31+28+31+30+31+30+31+31+30+31+30+dia;
        ano2 = ano2%7;
        if (ano2==0)  System.out.println("Hoje é Domingo!");
        if (ano2==1)  System.out.println("Hoje é Segunda!");
        if (ano2==2)  System.out.println("Hoje é Terça!");
        if (ano2==3)  System.out.println("Hoje é Quarta!");
        if (ano2==4)  System.out.println("Hoje é Quinta!");
        if (ano2==5)  System.out.println("Hoje é Sexta!");
        if (ano2==6)  System.out.println("Hoje é Sabado!");
   }
}
