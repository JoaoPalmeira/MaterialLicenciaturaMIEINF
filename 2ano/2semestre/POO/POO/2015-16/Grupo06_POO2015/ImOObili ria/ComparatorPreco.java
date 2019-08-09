import java.util.Comparator;
import java.io.Serializable;
/**
 * Escreva a descrição da classe ComparatorPreco aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

public class ComparatorPreco implements Comparator<Imovel>, Serializable
{
    
    /**
     * COnstrutor para objetos da classe ComparatorPreco
     */
    public int compare(Imovel i1, Imovel i2){
       if(i1.getPrecoPedido() > i2.getPrecoPedido())
          return 1; 
       if(i1.getPrecoPedido() < i2.getPrecoPedido())
          return -1;
       else return 0;     
    }
}
