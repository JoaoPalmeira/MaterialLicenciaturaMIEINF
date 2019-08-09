
import Business.Utilizador;
import java.util.ArrayList;
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
public class DocenteR extends Utilizador {
    
    private List<String> ucs;
    private String ucR;


    public DocenteR(List<String> ucs, String ucR, String nome, String pass) {
        super(nome, pass);
        this.ucs = ucs;
        this.ucR = ucR;
    }

    public DocenteR(List<String> ucs, String ucR, Utilizador u) {
        super(u);
        this.ucs = ucs;
        this.ucR = ucR;
    }

    public List<String> getUcs() {
        return ucs;
    }

    public void setUcs(List<String> ucs) {
        this.ucs = ucs;
    }

    public String getUcR() {
        return ucR;
    }

    public void setUcR(String ucR) {
        this.ucR = ucR;
    }

    
    @Override
    public Utilizador clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
