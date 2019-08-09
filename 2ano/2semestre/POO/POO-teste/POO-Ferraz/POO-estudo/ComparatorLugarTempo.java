
/**
 * Comparador de Lugar, tomando em consideração o tempo remanescente 
 * no parque e, em caso de igualdade, o nome do proprietário do 
 * veículo.
 * 
 * @author anr 
 * @version 2017.04.10
 */

import java.util.Comparator;

public class ComparatorLugarTempo implements Comparator<Lugar> {
  
  public int compare(Lugar l1, Lugar l2) {
    if (l1.getMinutos() < l2.getMinutos())
      return -1;
    else 
      if (l1.getMinutos() > l2.getMinutos())
        return 1;
      else
        return l1.getNome().compareTo(l2.getNome());
        
    
    
  }    
}