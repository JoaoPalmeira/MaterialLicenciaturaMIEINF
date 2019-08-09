
/**
 * Escreva a descrição da classe Ex6 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.lang.Integer;
public class Ex6
{
    public static long factorial (int x)
    {
       long y=1;
       while(x!=0) {
           y*=x;
           x--;
       }
       return y;
    }
    public static long main(String args)
    {
        String s = args;
        int x = Integer.parseInt(s);
        return factorial (x);
    }
}
