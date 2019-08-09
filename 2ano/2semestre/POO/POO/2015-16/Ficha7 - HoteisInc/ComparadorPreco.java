import java.util.Comparator;

/**
 * Escreva a descrição da classe ComparadorPreco aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class ComparadorPreco implements Comparator<Hotel>
{
   public int compare(Hotel h1, Hotel h2){
       double p1 = h1.precoQuarto();
       double p2 = h2.precoQuarto();
       if(p1 < p2){
           return -1;
       }
       if(p1 == p2){
           return 0;
       }
       return 1;
   }
}

