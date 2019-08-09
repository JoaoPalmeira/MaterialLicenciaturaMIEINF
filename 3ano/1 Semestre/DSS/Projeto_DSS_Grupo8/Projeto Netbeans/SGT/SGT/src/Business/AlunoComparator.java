
package Business;

import java.util.Comparator;


/**
 *
 * @author joaopalmeira
 */
public class AlunoComparator implements Comparator<String> {
    
    @Override
    public int compare(String a1, String a2) {
        if (a1.compareTo(a2) < 0) {
            return 1;
        }
        
        if (a1.compareTo(a2) > 0) {
            return -1;
        }
        
        return 0;
    }
}
