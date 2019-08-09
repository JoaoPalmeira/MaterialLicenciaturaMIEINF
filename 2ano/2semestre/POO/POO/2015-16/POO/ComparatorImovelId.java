import java.util.Comparator;
import java.io.Serializable;
public class ComparatorImovelId implements Comparator<Imovel>, Serializable
{
    public int compare(Imovel i1,Imovel i2){
        return i1.getId().compareTo(i2.getId());
    }
}
