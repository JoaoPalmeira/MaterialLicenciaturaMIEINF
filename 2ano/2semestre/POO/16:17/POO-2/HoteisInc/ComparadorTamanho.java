import java.util.Comparator;
import java.io.Serializable;
/**
 * Escreva a descrição da classe Comparator aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class ComparadorTamanho implements Comparator<Hotel>, Serializable
{
    public int compare(Hotel h1, Hotel h2)
    {
        if(h1.getNumeroQuartos() == h2.getNumeroQuartos())
        return h1.getNome().compareTo(h2.getNome());
        else
        {
            if (h1.getNumeroQuartos() > h2.getNumeroQuartos())
            return -1;
            else return 1;
        }
    }
}
