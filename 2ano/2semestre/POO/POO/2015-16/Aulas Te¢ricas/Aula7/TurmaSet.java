
/**
 * Classe que representa um Turma, utilizando Set.
 * 
 * @author José C. Campos 
 * @version 20160405
 */

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.stream.Collectors;

public class TurmaSet {
     
    // Variáveis de instância
    private String designação;
    private Set<Aluno> alunos;
    
    // construtores
    
    /**
     * Construtor Vazio.
     */
    public TurmaSet() {
           this("N/A");
    }
    
    /**
     * @param desig nome da turma.
     */
    public TurmaSet(String desig) {
            designação = desig;
            // alunos = new HashSet<>();            
            alunos = new TreeSet<>();   // Utiliza ordem natural         
            // alunos = new TreeSet<>(new ComparatorAlunoNome());
    }
    
    /**
     * Construtor de cópia.
     * 
     * @param t a turma a ser copiada.
     */
    public TurmaSet(TurmaSet t) {
        designação = t.getDesignação();
        alunos = t.getAlunos();
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
    public Set<Aluno> getAlunos() {   
        //Set<Aluno> copia = new HashSet<>();        
        Set<Aluno> copia = new TreeSet<>();
        
        for(Aluno a: alunos) 
            copia.add(a.clone());
            
        return copia;    
    }
    
    /**
     * @return o número de alunos da turma.
     */
    public int getTamanho() {
        return alunos.size();
    }
    
    /**
     * Adicionar um aluno à turma.
     * 
     * @param a o aluno a adicionar.
     */
    public void addAluno(Aluno a) {
        alunos.add(a.clone());
    }
    
    /**
     * Verificar se um aluno existe na turma.
     * 
     * @param a o aluno a procurar
     * @return true se o aluno existe; false noutro caso.
     */
    public boolean existeAluno(Aluno a) {
        return alunos.contains(a);
    }
    
    /**
     * Obter o aluno da turma com número num.
     * 
     * @param num o número do aluno pretendido
     * @return uma cópia do aluno na posição referida
     */
    public Aluno getAluno(int num) {
        Iterator<Aluno> it = alunos.iterator();
        boolean enc = false;
        Aluno a=null;
        
        while (!enc && it.hasNext()) {
            a = it.next();
            enc = (a.getNumero()==num);
        }
        
        return enc? a.clone():null;
    }
    
    /**
     * Alterar a nota de um aluno.
     * 
     * @param num o numero do aluno
     * @param nota a nova nota
     */
    public void setNota(int num, int nota) {
        Iterator<Aluno> it = alunos.iterator();
        boolean enc = false;
        Aluno a;
        
        while (!enc && it.hasNext()) {
            a = it.next();
            if (enc = (a.getNumero()==num))
                a.setNota(nota);
            }
   }
    
    /**
     * Quantos alunos passam? 
     * 
     * @return um int com nº alunos que passa
     */
    public long quantosPassam() {
        return alunos.stream().filter(Aluno::passa).count();
    }
    
    /**
     * Algum aluno passa?
     * 
     * @return true se algum aluno passa
     */
    public boolean alguemPassa() {
        return alunos.stream().anyMatch(Aluno::passa);
    }
         
    /**
     * Remover notas mais baixas
     * 
     * @param nota a nota limite
     */
    public void removerPorNota(int nota) {
        alunos.removeIf(a -> a.getNota()<nota);
    }
    
    /**
     * Média da turma
     * 
     * @return um double com a média da turma
     */
    public double media() {
        double sum = alunos.stream().mapToDouble(Aluno::getNota).sum();
        
        return sum/alunos.size();
    }
    
    /**
     * Subir a nota a todos os alunos
     * 
     * @param bonus int valor a subir. 
     */
    public void aguaBenta(int bonus) {
        alunos.forEach(a->a.sobeNota(bonus));
    } 
    
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o==null || o.getClass()!=this.getClass())
            return false;
        TurmaSet t = (TurmaSet) o;
        return this.designação.equals(t.getDesignação()) && this.alunos.equals(t.getAlunos());
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder("Turma(");
        sb.append(this.designação);
        sb.append(", ");
        sb.append(this.alunos.toString());
        return sb.toString();
    }
    
    public TurmaSet clone() {
        return new TurmaSet(this);
    }
}
