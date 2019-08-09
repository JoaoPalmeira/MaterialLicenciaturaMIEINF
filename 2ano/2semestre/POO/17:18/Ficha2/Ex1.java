import java.util.*;
/**
 * Escreva a descrição da classe Ex1 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex1
{   
    
    public int minimo(int[] a){
        int min=Integer.MAX_VALUE;
        for(int i=0; i<a.length; i++){
            if(a[i]<min) min=a[i];
        }
        return min;
    }
    
    public int[] arrayEntre(int[] a, int i, int f){
        int aux[] = new int[a.length];
        int n=0;
        while(i<=f){
            aux[n]=a[i];
            n++;
            i++;
        }
        return aux;
    }
    
    public int[] comuns(int[] a, int[] b){
        int[] res = new int[a.length];
        int ind=0;
        for(int i=0; i<a.length;i++){
            for(int j=0; j<b.length;j++){
                if(a[i] == b[j]){
                    res[ind] = b[j];
                    ind++;
                }
            }
        }
        return res;
    }
}
