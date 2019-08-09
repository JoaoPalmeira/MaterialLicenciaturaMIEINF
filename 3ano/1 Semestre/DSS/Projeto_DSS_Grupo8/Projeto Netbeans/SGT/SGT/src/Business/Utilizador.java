package Business;

import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joaopalmeira
 */
public abstract class Utilizador {
    
    private final String nomeUtilizador;
    private final String password;
    
    
    public Utilizador(String nomeUtilizador, String password) {
        this.nomeUtilizador = nomeUtilizador;
        this.password = password;
    }
    
    
    public Utilizador(Utilizador u) {
        nomeUtilizador = u.getNomeUtilizador();
        password = u.getPassword();
    }
    
    
    public String getNomeUtilizador() {
        return nomeUtilizador;
    }
    
    
    public String getPassword() {
        return password;
    }
    
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {
            nomeUtilizador,
            password
        });
    }
    
    
    @Override
    public abstract Utilizador clone();
    
}
