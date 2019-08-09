package main.business;

import java.util.Vector;
import main.business.Aluno;

public class Turma {
    private String _descr;
    public Vector<Aluno> _alunos = new Vector<Aluno>();

    public Turma(String _descr) {
        this._descr = _descr;
    }

    public void addAluno(Aluno a) {
            _alunos.add(a);
    }

}