import java.util.Scanner;
/**
 * Escreva a descrição da classe Ex10 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex10
{
      
public static void main(String[] args){
        int n; 
        Scanner in = new Scanner(System.in);
         System.out.println("Quantas classificações quer inserir?");
        n=in.nextInt();
        int c;
        int c1=0;
        int c2=0;
        int c3=0;
        int c4=0;
        while(n>0){
           System.out.println("Qual a classificação?");
           c=in.nextInt(); 
            if(c>=0 && c<5){
                
                c1++;
                
            }
            if(c>=5 && c<10){
                
                c2++;
                
            }
            if(c>=10 && c<15){
                
                c3++;
                
            }
            if(c>=15 && c<=20){
                
                c4++;
                
            }
            if(c<0 || c>20){
                System.out.println("Classificação introduzida inválida");
                break;
            }
            n--;
        }
        System.out.println("No intervalo [0,5[ existem "+c1+" classificações");
        System.out.println("No intervalo [5,10[ existem "+c2+" classificações");
        System.out.println("No intervalo [10,15[ existem "+c3+" classificações");
        System.out.println("No intervalo [15,20] existem "+c4+" classificações");
    }
}
