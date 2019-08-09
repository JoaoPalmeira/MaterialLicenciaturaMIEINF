import java.util.Comparator;
import java.io.Serializable;

public class ComparatorValor implements Comparator<Despesa>, Serializable
{
    public int compare(Despesa d1,Despesa d2){
        if( d1. getValorDespesa() > d2. getValorDespesa()){
            return 1;
        }
        else {return -1;}
    }
}