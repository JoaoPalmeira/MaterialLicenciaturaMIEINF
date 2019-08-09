import java.util.Collections;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Random;
import java.util.Calendar;
import java.util.Arrays;
/**
 * Escreva a descrição da classe ficha2ex3 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class ficha2ex3
{
    static Scanner sc = new Scanner (System.in);
    //exercicio 3
    private static int[] lerArrayInt(int n) {
        int[] array = new int[n];
        for(int i=0;i<n;i++){
             array[i] = sc.nextInt();
            }
        Arrays.sort(array, Collections.reverseOrder());
        System.out.println("Arrray:");
        for(int i: array)
            System.out.println(i);
        return array;
    }
    public static int minPos(int[] arr){
        Arrays.sort(arr);
        return arr[0];
    }
    public static void ex3(){
        System.out.println("Insira um numero:");
        int n = sc.nextInt();
        System.out.println("Insira"  + " " + n + " " + "números:");
        int[] a = lerArrayInt(n);
        int indice = minPos(a);
        System.out.println("Menor valor introduzido é:" + indice);
    }
}
