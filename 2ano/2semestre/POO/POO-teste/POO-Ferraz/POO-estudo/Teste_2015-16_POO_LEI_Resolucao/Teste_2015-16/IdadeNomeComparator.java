import java.util.*;
public class IdadeNomeComparator implements Comparator<Candidato>{
    public int compare(Candidato c1, Candidato c2){
        String nome1 = c1.getNome();
        String nome2 = c2.getNome();
        int idade1 = c1.getIdade();
        int idade2 = c2.getIdade();
       int res=1;
        if(idade1 < idade2){res= 1;}
        if(idade1 > idade2){res= -1;}
        if(idade1 == idade2){
            if(c1.getNome().equals(c2.getNome())){res= 0;}
            if( !(c1.getNome().equals(c2.getNome()))){
                res= c1.getNome().compareTo(c2.getNome());
            }
        }
        return res;
    }
}