import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Random;
import java.util.Calendar;
import java.util.Arrays;
/**
 * Escreva a descrição da classe Classe2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Classe2
{
    static Scanner sc = new Scanner (System.in);
    //exercicio 2
    public static int[] lerArrayInt(int n) {
        int[] array = new int[n];
        for(int i=0;i<n;i++){
             array[i] = sc.nextInt();
            }
        return array;
    }
    public static int minPos(int[] arr){
        Arrays.sort(arr);
        return arr[0];
    }
    public static void ex2(){
        System.out.println("Insira numero:");
        int n = sc.nextInt();
        System.out.println("Insira"  + " " + n + " " + "nrs:");
        int[] a = lerArrayInt(n);
        int indice = minPos(a);
        System.out.println("Indice:" + indice);
    }
}
