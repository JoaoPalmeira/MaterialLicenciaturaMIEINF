/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Business.Utilizador;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author rafae
 */
public abstract class UtilizadorDAO 
        implements Map<String, Utilizador> {
    
    @Override
    public Set<Map.Entry<String, Utilizador>> entrySet() {
        throw new NullPointerException();
    }
    
    
    @Override
    public Set<String> keySet() {
        throw new NullPointerException();
    }
    
    
    @Override
    public void putAll(Map<? extends String, ? extends Utilizador> map) {
        map.values().forEach((u) -> {
            this.put(u.getNomeUtilizador(), u);
        });
    }
    
    
    @Override
    public boolean containsValue(Object value) {
        return this.containsKey(((Utilizador)value).getNomeUtilizador());
    }
    
    
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
}
