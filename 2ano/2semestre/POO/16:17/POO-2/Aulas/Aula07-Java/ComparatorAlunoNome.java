
/**
 * Comparator de Aluno - ordenação por nome.
 * 
 * @author José Creissac Campos 
 * @version 20160403
 */

import java.util.Comparator;
public class ComparatorAlunoNome implements Comparator<Aluno> {
    
    public int compare(Aluno a1, Aluno a2) {
        return a1.getNome().compareTo(a2.getNome());
    }
}


