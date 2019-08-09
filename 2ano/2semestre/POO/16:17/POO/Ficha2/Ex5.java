import java.util.Arrays;
/**
 * Escreva a descrição da classe Ex5 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex5
{
    private static int[] ordena(int[] num){
        int i, j,aux;
          for(i=1;i<num.length;i++){
            for(j=0;j<=i;j++){
              if(num[j]>num[i]){
                aux=num[j];
                num[j]=num[i];
                num[i]=aux;
              }
            }
          }
          return num;
}        
        
        private static int[] arrayConcat(int[] arr1,int[] arr2){
       
       int i,j,MAXDIM;
       int [] res= new int[arr1.length+arr2.length];
       for(i=0;i<arr1.length;i++){
           res[i]=arr1[i];
       }
       for(i=arr1.length,j=0;j<arr2.length;i++,j++){
           res[i]=arr2[j];
        }
       
       return res;
}
public static void main(String[] args){
        int[] arr1={23,45,67,11,2,1,3,1,2,65};
        int arr2[]={9,8,7,6,5};
        
        
        System.out.println(Arrays.toString(ordena(arrayConcat(arr1,arr2)))); 
}
}



