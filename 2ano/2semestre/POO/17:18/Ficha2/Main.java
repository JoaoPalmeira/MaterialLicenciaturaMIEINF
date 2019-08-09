import java.util.*;
/**
 * Escreva a descrição da classe Main aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Main
{
    public static void main(String[] args){
        /*Scanner sc = new Scanner (System.in);
        //int valor = 0;
        System.out.print (" Número de inteiros a ler? ");
        int ler = sc.nextInt () ;
        int [] array = new int[ler];
        System.out.print ("Insira " + ler + "numeros:");
        for(int i = 0; i < ler ; i ++) {
            array[i]= sc.nextInt();
        }
        System.out.print (" todos: "+array);
        Ex1 ex = new Ex1();
        int min =ex.minimo(array);*/
        Scanner sc = new Scanner (System.in);
        System.out.print (" Número de inteiros a ler? ");
        int ler = sc.nextInt();
        int[] array=lerArray(ler);
        Ex1 ex = new Ex1();
        int min =ex.minimo(array);
        System.out.print (" Número Minimo "+ min);
        
    }
    
    public static int[] lerArray(int tam){
        int[] numeros = new int[tam];
        Scanner sc = new Scanner(System.in);
        System.out.print ("Insira " + tam + " numeros:");
        for(int i = 0; i < tam ; i ++) {
            numeros[i]= sc.nextInt();
        }
        return numeros;
    }
}
