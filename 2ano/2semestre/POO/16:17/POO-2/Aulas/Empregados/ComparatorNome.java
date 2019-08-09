
/**
 * Write a description of class ComparatorSalario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Comparator;
import java.io.Serializable;

public class ComparatorNome implements Comparator<Empregado>, Serializable {
  
   public int compare(Empregado e1, Empregado e2) {
     return e1.getNome().compareTo(e2.getNome());
     
   }
    
}
