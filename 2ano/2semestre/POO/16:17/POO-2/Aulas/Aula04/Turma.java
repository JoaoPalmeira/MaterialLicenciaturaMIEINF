
/**
 * Classe que representa um Turma.
 * Com base no exemplo utilizado na aula de 29/02/2016 de POO.
 * 
 * @author José C. Campos 
 * @version 20160229
 */
public class Turma {
    // Variáveis de instância
    private String designação;
    private Aluno[] lstAlunos;
    private int tamanho;
    
    // construtores
    
    /**
     * Construtor Vazio.
     */
    public Turma() {
        this("N/A");
    }
    
    /**
     * @param desig nome da turma.
     */
    public Turma(String desig) {
        designação = desig;
        lstAlunos = new Aluno[25];
        tamanho = 0;
    }
    
    /**
     * Construtor de cópia.
     * 
     * @param t a turma a ser copiada.
     */
    public Turma(Turma t) {
        designação = t.getDesignação();
        lstAlunos = t.getLstAlunos();
        tamanho = t.getTamanho();
    }
    
    // Métodos de instãncia
    
    /**
     * @return o nome da turma.
     */
    public String getDesignação() {
        return designação;
    }
    
    /**
     * Método auxiliar que devolve uma 'deep copy' do array com os alunos.
     * Declarado privado: outras classes não poderão obter o array.
     * 
     * @return um array com os alunos da turma (faz uma 'deep copy')
     */
    private Aluno[] getLstAlunos() {
        Aluno[] res = new Aluno[25];
        
        for(int i=0; i<tamanho; i++)
            res[i] = lstAlunos[i].clone();
        
        return res;
    }
    
    /**
     * @return o número de alunos da turma.
     */
    public int getTamanho() {
        return tamanho;
    }
    
    /**
     * Adicionar um aluno à turma.
     * 
     * NOTA: Verifica-se se o aluno ainda acabe e se ainda não existe!
     * 
     * @param a o aluno a adicionar.
     */
    public void addAluno(Aluno a) {
        if (tamanho<lstAlunos.length && !this.existeAluno(a))
            lstAlunos[tamanho++] = a.clone();
    }
    
    /**
     * Obter o i-ésimo aluno da turma.
     * 
     * ATENÇÃO: Falta validar o valor de pos (complete o método!).
     * 
     * @param pos a posição do aluno pretendido
     * @return uma cópia do aluno na posição referida
     */
    public Aluno getAluno(int pos) {
        return lstAlunos[pos].clone();
    }
    
    /**
     * Alterar a nota de um aluno.
     * 
     * ATENÇÃO: Falta validar o valor de pos (complete o método!).
     * 
     * @param pos a posição do aluno
     * @param nota a nova nota
     */
    public void setNota(int pos, double nota) {
        lstAlunos[pos].setNota(nota);
    }
    
    /**
     * Verificar se um aluno existe na turma.
     * 
     * @param a o aluno a procurar
     * @return true se o aluno existe; false noutro caso.
     */
    public boolean existeAluno(Aluno a) {
        boolean enc = false;
        
        for(int i=0; i<tamanho && !enc; i++) 
            if(lstAlunos[i].equals(a))
                enc = true;
        return enc;
                
    }
    
}
