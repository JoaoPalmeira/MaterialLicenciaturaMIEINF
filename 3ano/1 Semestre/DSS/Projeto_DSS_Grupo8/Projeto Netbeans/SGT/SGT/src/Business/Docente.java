package Business;

import Business.Utilizador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joaopalmeira
 */
public class Docente extends Utilizador {
    
     private final List<String> ucs;
    
    
    public Docente(String nomeUtilizador, String password) {
        super(nomeUtilizador, password);
        
        ucs = new ArrayList<>();
    }
    
    
    public Docente(String nomeUtilizador, String password, List<String> ucs) {
        super(nomeUtilizador, password);
        
        this.ucs = new ArrayList<>(ucs);
    }
    
    
    public Docente(Docente p) {
        super(p);
        
        ucs = p.getUCs();
    }
    
    
    public List<String> getUCs() {
        return new ArrayList<>(ucs);
    }
    
    
    public void addUC(String uc) {
        ucs.add(uc);
    }
    
    
    @Override
    public int hashCode() {
        return super.hashCode() + 
                Arrays.hashCode(new Object[] {
                   ucs 
                });
    }
    
    
    @Override
    public Docente clone() {
        return new Docente(this);
    }
}
