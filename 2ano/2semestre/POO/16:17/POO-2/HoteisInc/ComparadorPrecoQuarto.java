import java.util.Comparator;
import java.io.Serializable;
public class ComparadorPrecoQuarto implements Comparator<Hotel>,Serializable
{
    public int compare(Hotel h1,Hotel h2)
    {
        if(h1.getPrecoQuarto()==(h2.getPrecoQuarto())) return 1;
        else return -1;
    }
}
