
/**
 * Write a description of class ComparatorSalario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Comparator;
import java.io.Serializable;

public class ComparatorSalario implements Comparator<Empregado>, Serializable {
  
   public int compare(Empregado e1, Empregado e2) {
     return (int)(e1.salario() - e2.salario());
     /* 
     Atenção à possível perca de informação, por estarmos 
     a converter um double para um int.
     Uma outra hipótese seria ter:
     
     if(e1.salario() > e2.salario())
       return 1; 
     if(e1.salario() < e2.salario())
       return -1;
     else return 0;     
      */
     
   }
    
}
