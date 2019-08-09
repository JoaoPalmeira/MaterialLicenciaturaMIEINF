package main.business;

import java.util.Collection;
import java.util.Scanner;
import main.data.DocenteDAO;
import main.data.RegistosDAO;
import main.data.AlunoDAO;
import main.data.CursoDAO;

/**
 * Facade para a camada de business.
 * Contém os métodos identificados nos diagramas de sequencia de implementação
 * @author ruicouto
 */
public class SGT {
    //O docente com login actualmente
    private Docente sessao;

    private DocenteDAO _docentesDAO;
    private CursoDAO _cursoDAO;
    private AlunoDAO _alunosDAO;
    private RegistosDAO _registosDAO;

    public SGT() {
        _alunosDAO = new AlunoDAO();
        sessao = new Docente(); //deveria ser carregado da base de dados
    }

    public Docente getSessao() {
        return sessao;
    }
    
    /**
     * Método especificado no DSS
     * @param aCampo
     * @param aTurma
     * @return
     * @throws Exception 
     */
    public Aluno novoRegisto(Object aCampo, String aTurma) throws Exception {
        int n;
        if (aCampo instanceof Integer) {
            n = (Integer) aCampo;
        } else {
            n = IServicosAcademicos.getNumero(aCampo.toString());
        }
        boolean b = verifica(n);
        if (!b) {
            throw new Exception("Número inválido");
        }
        Aluno a = IServicosAcademicos.getAluno(n);
        if (a == null) {
            throw new Exception("Aluno inválido");
        }
        //UI é responsável por ler aluno...
        return a;
    }

    public void confirma(Docente aD, Aluno aA, String aT) {
        Turma turma = new Turma(aT);
        _alunosDAO.put(aA.getId(), aA);
        aD.addAluno(aA, turma);
    }

    public boolean verifica(int aN) {
        if (aN > 40000 && aN < 100000) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public void adicinarAluno(int numero, String turma) throws Exception {
        Aluno a = novoRegisto(numero, turma);
        Docente d = new Docente();
        confirma(d, a, turma);
    }

    public Collection<Aluno> listarAlunos() {
        return _alunosDAO.values();
    }
    
}
