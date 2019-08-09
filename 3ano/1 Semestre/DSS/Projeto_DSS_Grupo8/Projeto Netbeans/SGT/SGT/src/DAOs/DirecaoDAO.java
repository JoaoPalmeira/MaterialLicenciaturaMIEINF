package DAOs;

import Business.Direcao;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author rafae
 */
public abstract class DirecaoDAO  
        implements Map<String, Direcao> {
    
    @Override
    public Set<Map.Entry<String, Direcao>> entrySet() {
        throw new NullPointerException();
    }
    
    
    @Override
    public Set<String> keySet() {
        throw new NullPointerException();
    }
    
    
    
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
}