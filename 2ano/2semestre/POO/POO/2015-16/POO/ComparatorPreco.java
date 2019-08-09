import java.util.Comparator;
import java.io.Serializable;
public class ComparatorPreco implements Comparator<Imovel>, Serializable
{
    public int compare(Imovel i1,Imovel i2){
        if (i1.getPrecoPed() == i2.getPrecoPed())
            return i1.getId().compareTo(i2.getId());
        else {
            if (i1.getPrecoPed()>i2.getPrecoPed())
                return (-1);
            else return 1;
        }
    }
}
