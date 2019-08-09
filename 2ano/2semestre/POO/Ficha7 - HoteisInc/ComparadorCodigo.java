import java.util.Comparator;

/**
 * Escreva a descrição da classe ComparadorCodigo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class ComparadorCodigo implements Comparator<Hotel>
{
  public int compare(Hotel h1, Hotel h2){
      String c1 = h1.getCodigo();
      String c2 = h2.getCodigo();
      return c1.compareTo(c2);
  }
}
