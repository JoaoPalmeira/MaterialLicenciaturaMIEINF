package Business;

import Business.UC;
import java.util.Comparator;

/**
 *
 * @author joaopalmeira
 */
public class UCComparator implements Comparator<UC> {
    
    @Override
    public int compare(UC u1, UC u2) {
        if (u1.getAno() > u2.getAno()) {
            return -1;
        }
        
        if (u1.getAno() < u2.getAno()) {
            return 1;
        }
        
        if (u1.getTurnosPraticos().size() > u2.getTurnosPraticos().size()) {
            return 1;
        }
        
        if (u1.getTurnosPraticos().size() < u2.getTurnosPraticos().size()) {
            return -1;
        }
        
        return 0;
    }
}
