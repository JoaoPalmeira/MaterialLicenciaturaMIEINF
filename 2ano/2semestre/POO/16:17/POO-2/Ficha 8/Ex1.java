import java.util.Scanner;
public class Ex1
{
    public static void main(String[] args)
    {
        Scanner ler = new Scanner(System.in);
        System.out.print("Digite uma sequencia de numeros terminada por zero!\n");
        int numero=1;
        int par=0;
        int impar=0;
        double media=0.0;
        int contador=0;
        while(numero!=0)
        {
            numero=ler.nextInt();
            if(numero!=0) contador++;
            if(numero%2==0 && numero!=0) par++;
            if(numero%2!=0) impar++;
            if(numero!=0) media+=numero;
        }
        System.out.print("Foram inseridos "+par+" numeros pares!\n");
        System.out.print("Foram inseridos "+impar+" numeros impares!\n");
        System.out.print("A media da sequencia é "+media/contador+"\n");
    }
}
