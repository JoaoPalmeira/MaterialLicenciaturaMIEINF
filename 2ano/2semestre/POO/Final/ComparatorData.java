import java.util.Comparator;
import java.io.Serializable;

public class ComparatorData implements Comparator<Despesa>, Serializable
{
    public int compare(Despesa d1,Despesa d2){
        return d1.getDataDespesa().compareTo(d2.getDataDespesa());
    }
}
