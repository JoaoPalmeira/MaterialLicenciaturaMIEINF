
/**
 * Escreva a descrição da classe Ex11 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Scanner;
public class Ex11
{
    public static void main()
    {
        System.out.println("Digite as notas, quando terminar digite -1!");
        int k=0;
        int x=0;
        int y=0;
        int z=0;
        int w=0;
        while (k!=-1)
        {
            Scanner ler = new Scanner(System.in);
            k=ler.nextInt();
            if (k>=0 && k<5) x++;
            if (k>=5 && k<10) y++;
            if (k>=10 && k<15) z++;
            if (k>=15 && k<=20) w++;
        }
        System.out.println("Houve "+x+" notas de 0 ate 5, "+y+" notas de 5 ate 10, "+z+" notas de 10 até 15 e "+w+" notas de 15 a 20");
    }
}
