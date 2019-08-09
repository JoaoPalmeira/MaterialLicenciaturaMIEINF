import java.util.Scanner;
import java.util.GregorianCalendar;
import java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Calendar;
/**
 * Escreva a descrição da classe Ficha1 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ficha1
{
    static Scanner sc = new Scanner(System.in);
    
    public static void ex1(String[] args){
        System.out.println("Insira nome e saldo");
        String nome;
        double saldo;
        nome = sc.nextLine();
        saldo = sc.nextDouble();
        System.out.println("Nome " + nome + " saldo " + saldo);
    }
     public static void ex2() {
        System.out.println("Insira os numeros");
        double n1;
        double n2;
        n1 = sc.nextDouble();
        n2 = sc.nextDouble();
        if (n1>n2) { System.out.println("Numero maior:" + " " + n1 + " " + "Numero menor:" + " " + n2 + " " + "Media:" + " " + ((n1+n2)/2));}
        else {System.out.println("Numero maior:" + " " + n2 + " " + "Numero menor:" + " " + n1 + "Media" + " " + ((n1+n2)/2));}
    }
    public static void ex3(){
        int c = 0;
        System.out.println("Insira 10 números");
        for (int i=0; i<10; i++){
            int v = sc.nextInt();
            if (v>5){
                c++;
            }    
        }
        System.out.println("Resultados: " + c);
    }
    public static void ex5() {
        int soma=0;
        int min=1024;
        int max=-1024;
        int c;
        System.out.println("Insira numeros:");
        c = sc.nextInt();  
        while (c!=0){
        soma += c;
        if (c<min) {
            min = c;
            c = sc.nextInt();
        }
        else {
            max=c;
            c = sc.nextInt();
        }
      }
      System.out.println("Resultado:" + soma + " " + "Maximo:" + max + " " + "Minimo:" + min);
    }
    static double factorial (double valor) {
        int v = 1;
        while (valor>1){
            v*=valor;
            valor --;
        }
        return v;
    }
    public static void ex6 (String[] args){
        String s = args[0];
        double v = Integer.parseInt(s);
        double r = Ficha1.factorial(v);
        System.out.println("Factorial: " + r);
    }
    public static void ex7() {
        GregorianCalendar i = new GregorianCalendar();
        Ficha1.factorial(1000000);
        GregorianCalendar f = new GregorianCalendar();
        double dif = f.getTimeInMillis() - i.getTimeInMillis();
        System.out.println("Tempo decorrido (millis): " + dif);
    }
    public static String ex10 (int d1, int h1, int m1, int s1, int d2, int h2, int m2, int s2) {
        GregorianCalendar data = new GregorianCalendar(2016,02,d1,h1,m1,s1);
        data.add(Calendar.DAY_OF_MONTH, d2);
        data.add(Calendar.HOUR_OF_DAY, h2);
        data.add(Calendar.MINUTE, m2);
        data.add(Calendar.SECOND, s2);
        
        data.get(Calendar.SECOND);
        String r = data.get(Calendar.DAY_OF_MONTH) + "D " + data.get(Calendar.HOUR_OF_DAY) + "H " + data.get(Calendar.MINUTE) + "M " + data.get(Calendar.SECOND) + "S ";
        return r;
    }  
    public static void ex17() {
        
    }
}    