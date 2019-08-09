package Business;

import Business.Utilizador;
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
public class Direcao extends Utilizador{
    
    private List<String> alunos;
    private List<String> ucs;

    public Direcao(List<String> alunos, List<String> ucs, String nome, String pass) {
        super(nome, pass);
        this.alunos = alunos;
        this.ucs = ucs;
    }

    public Direcao(List<String> alunos, List<String> ucs, Utilizador u) {
        super(u);
        this.alunos = alunos;
        this.ucs = ucs;
    }

    public List<String> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<String> alunos) {
        this.alunos = alunos;
    }

    public List<String> getUcs() {
        return ucs;
    }

    public void setUcs(List<String> ucs) {
        this.ucs = ucs;
    }
    
    

    @Override
    public Utilizador clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
