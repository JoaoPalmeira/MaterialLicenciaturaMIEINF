
/**
 * Classe que representa um Turma, utilizando Set.
 * 
 * @author José C. Campos 
 * @version 20160314
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class TurmaList {
     
    // Variáveis de instância
    private String designação;
    private List<Aluno> lstAlunos;
    
    // construtores
    
    /**
     * Construtor Vazio.
     */
    public TurmaList() {
           this("N/A");
    }
    
    /**
     * @param desig nome da turma.
     */
    public TurmaList(String desig) {
            designação = desig;
            lstAlunos = new ArrayList<>();
    }
    
    /**
     * Construtor de cópia.
     * 
     * @param t a turma a ser copiada.
     */
    public TurmaList(TurmaList t) {
        designação = t.getDesignação();
        lstAlunos = t.getLstAlunos();
    }
    
    // Métodos de instãncia
    
    /**
     * @return o nome da turma.
     */
    public String getDesignação() {
        return designação;
    }
    
    /**
     * Método auxiliar que devolve uma 'deep copy' da lista de alunos.
     * Declarado privado: outras classes não poderão obter a lista.
     * 
     * @return um List com os alunos da turma (faz uma 'deep copy')
     */
    public List<Aluno> getLstAlunos() {   
        // Versão com índices:
        /* List<Aluno> res = new ArrayList<>();
         * for(int i=0; i<lstAlunos.size(); i++)
         *    res.add(lstAlunos.get(i).clone());
         * return res; 
         */
        // Versão com for(each)
        /* List<Aluno> res = new ArrayList<>();
         * for(Aluno a: lstAlunos)
         *    res.add(a.clone());
         * return res; 
         */
        // Versão com Streams
        return lstAlunos.stream()
                        .map(Aluno::clone)
                        .collect(Collectors.toList());       
    }
    
    /**
     * @return o número de alunos da turma.
     */
    public int getTamanho() {
        return lstAlunos.size();
    }
    
    /**
     * Adicionar um aluno à turma.
     * 
     * @param a o aluno a adicionar.
     */
    public void addAluno(Aluno a) {
        lstAlunos.add(a.clone());
    }
    
    /**
     * Verificar se um aluno existe na turma.
     * 
     * @param a o aluno a procurar
     * @return true se o aluno existe; false noutro caso.
     */
    public boolean existeAluno(Aluno a) {
        return lstAlunos.contains(a);
    }
    
    /**
     * Obter o i-ésimo aluno da turma.
     * 
     * @param pos a posição do aluno pretendido
     * @return uma cópia do aluno na posição referida
     */
    public Aluno getAluno(int pos) {
        if (pos < lstAlunos.size() && pos >= 0)
            return lstAlunos.get(pos).clone();
        else
            return null;
    }
    
    /**
     * Alterar a nota de um aluno.
     * 
     * @param pos a posição do aluno
     * @param nota a nova nota
     */
    public void setNota(int pos, int nota) {
        if (pos<lstAlunos.size() && pos >= 0)
                lstAlunos.get(pos).setNota(nota);
    }
    
    /**
     * Quantos alunos passam? 
     * 
     * @return um int com nº alunos que passa
     */
    public long quantosPassam() {
        return lstAlunos.stream().filter(Aluno::passa).count();
    }
    
    /**
     * Algum aluno passa?
     * 
     * @return true se algum aluno passa
     */
    public boolean alguemPassa() {
        return lstAlunos.stream().anyMatch(Aluno::passa);
    }
         
    /**
     * Remover notas mais baixas
     * 
     * @param nota a nota limite
     */
    public void removerPorNota(int nota) {
        // Versão com iteradores externos (Iterator<>)
        /* Iterator<Aluno> it = lstAlunos.iterator();
         * Aluno a;
         * 
         * while(it.hasNext()) {
         *    a = it.next();
         *    if (a.getNota()<nota)
         *        it.remove();
         * }
         */
        // Versão com iteradores internos (stream)
        /* lstAlunos = lstAlunos.stream()
         *                      .filter(a -> a.getNota()>=nota)
         *                      .collect(Collectors.toList());
         */
        // Versão final (removeIf das colecções)
        lstAlunos.removeIf(a -> a.getNota()<nota);
    }
    
    /**
     * Média da turma
     * 
     * @return um double com a média da turma
     */
    public double media() {
        // Versão com for(each)
        /* double sum = 0.0;
         * 
         * for(Aluno a: lstAlunos)
         *     sum += a.getNota();
         */
        // Versão com iteradores internos
        double sum = lstAlunos.stream().mapToDouble(Aluno::getNota).sum();
        
        return sum/lstAlunos.size();
        
    }
    
    /**
     * Subir a nota a todos os alunos
     * 
     * @param bonus int valor a subir. 
     */
    public void aguaBenta(int bonus) {
        // Versão com for(each)
        /* for(Aluno a: lstAlunos)
         *    a.sobeNota(bonus);
         */
        // Versão com iteradores internos
        lstAlunos.forEach(a->a.sobeNota(bonus));
    } 
    
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o==null || o.getClass()!=this.getClass())
            return false;
        TurmaList t = (TurmaList) o;
        return true;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder("Turma(");
        sb.append(this.designação);
        sb.append(", ");
        sb.append(this.lstAlunos.toString());
        return sb.toString();
    }
    
    public TurmaList clone() {
        return new TurmaList(this);
    }
}
