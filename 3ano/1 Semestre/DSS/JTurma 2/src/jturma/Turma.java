package jturma;

/**
 * Classe turma.
 * * 
 * @author José Creissac Campos
 * @version 080501 (act. 20160917)
 */

import java.util.*;
public class Turma extends Observable {
    // variáveis de instância

    /**
     * @associates <{Aluno}>
     * @link aggregation*/
    private Map<String,Aluno> turma;

    // Construtor

    public Turma() {
        this.turma = new Hashtable<String,Aluno>();
    }

    // Métodos de instância

    /**
     * Adicionar um aluno.
     * Se o Aluno já existe, é substituido.
     */
    public void addAluno(Aluno a) {
        Aluno copia = (Aluno)a.clone();
        String num= a.getNumero();
        boolean update = this.turma.containsKey(num);
        this.turma.put(num,copia);
        if (!update) {
            this.setChanged();
            this.notifyObservers();
        }
    }

    /**
     * Consultar um aluno
     */
    public Aluno getAluno(String num) throws TurmaException {
        
        try {
            Aluno a = this.turma.get(num);
            return (Aluno)a.clone();
        } 
        catch (NullPointerException e) {
            StringBuffer sb = new StringBuffer("Aluno ");
            sb.append(num);
            sb.append(" inexistente!");
            throw new TurmaException(sb.toString());
        }
    }

    /**
     * Remover um aluno
     */
    public void delAluno(String num) throws TurmaException {
        if (!this.turma.containsKey(num)) {
            StringBuffer sb = new StringBuffer("Aluno ");
            sb.append(num);
            sb.append(" inexistente!");
            throw new TurmaException(sb.toString());
        }        
        this.turma.remove(num);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Quantos alunos passam
     * @return quantos alunos passam
     */
    public int quantosPassam() {
        return (int) this.turma.values().stream().filter(Aluno::passa).count();
    }
         
}
