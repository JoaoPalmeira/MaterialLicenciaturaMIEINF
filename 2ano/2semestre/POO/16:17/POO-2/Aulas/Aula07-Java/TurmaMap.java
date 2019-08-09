
/**
 * Classe que representa um Turma, utilizando Map.
 * 
 * @author José C. Campos 
 * @version 20160403
 */

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;

public class TurmaMap {
     
    // Variáveis de instância
    private String designação;
    private Map<Integer, Aluno> alunos;
    
    // construtores
    
    /**
     * Construtor Vazio.
     */
    public TurmaMap() {
           this("N/A");
    }
    
    /**
     * @param desig nome da turma.
     */
    public TurmaMap(String desig) {
            designação = desig;
            alunos = new HashMap<>();
    }
    
    /**
     * Construtor de cópia.
     * 
     * @param t a turma a ser copiada.
     */
    public TurmaMap(TurmaMap t) {
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
    public Map<Integer,Aluno> getAlunos() {
        Map<Integer,Aluno> copia= new HashMap<>();
        
        /*for(Integer i: alunos.keySet())
            copia.put(i, alunos.get(i).clone());
            
        for(Aluno a: alunos.values())
            copia.put(a.getNumero(), a.clone());*/
        
        for(Map.Entry<Integer,Aluno> e: alunos.entrySet())
            copia.put(e.getKey(), e.getValue().clone());
            
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
        alunos.put(a.getNumero(), a.clone());
    }
    
    /**
     * Verificar se um aluno existe na turma.
     * 
     * @param a o aluno a procurar
     * @return true se o aluno existe; false noutro caso.
     */
    public boolean existeAluno(Aluno a) {
        return alunos.containsValue(a);
    }
    
    /**
     * Obter o aluno da turma com número num.
     * Pré-condição: aluno tem que existir
     * 
     * @param num o número do aluno pretendido
     * @return uma cópia do aluno na posição referida
     */
    public Aluno getAluno(int num) {
        // Aluno a = alunos.get(num);
        // return a==null? null: a.clone();
        
        return alunos.get(num).clone();
        
    }
    
    /**
     * Alterar a nota de um aluno.
     * Pré-condição: aluno tem que existir.    
     * 
     * @param num o numero do aluno
     * @param nota a nova nota
     */
    public void setNota(int num, double nota) {
         alunos.get(num).setNota(nota);
   }
    
    /**
     * Quantos alunos passam? 
     * 
     * @return um int com nº alunos que passa
     */
    public long quantosPassam() {
        return alunos.values().stream().filter(Aluno::passa).count();
    }
    
    /**
     * Algum aluno passa?
     * 
     * @return true se algum aluno passa
     */
    public boolean alguemPassa() {
        return alunos.values().stream().anyMatch(Aluno::passa);
    }
         
    /**
     * Remover notas mais baixas
     * 
     * @param nota a nota limite
     */
    public void removerPorNota(int nota) {
        alunos.values().removeIf(a -> a.getNota()<nota);

    }
    
    /**
     * Média da turma
     * Pré-condição: a turma não pode estar vazia!
     * 
     * @return um double com a média da turma
     */
    public double media() {
        double sum = alunos.values().stream().mapToDouble(Aluno::getNota).sum();
        
        return sum/alunos.size();
    }
    
    /**
     * Subir a nota a todos os alunos
     * 
     * @param bonus int valor a subir. 
     */
    public void aguaBenta(int bonus) {
         alunos.values().forEach(a->a.sobeNota(bonus));
    } 
    
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o==null || o.getClass()!=this.getClass())
            return false;
        TurmaMap t = (TurmaMap) o;
        return this.designação.equals(t.getDesignação()) && this.alunos.equals(t.getAlunos());
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder("Turma(");
        sb.append(this.designação);
        sb.append(", ");
        sb.append(this.alunos.toString());
        return sb.toString();
    }
    
    public TurmaMap clone() {
        return new TurmaMap(this);
    }
}
