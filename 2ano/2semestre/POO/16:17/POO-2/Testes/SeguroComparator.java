import java.util.Comparator;
import java.io.Serializable;
public class SeguroComparator implements Comparator<Barco>,Serializable
{
    public int compare(Barco b1, Barco b2)
    {
        double seguro1=b1.getMilhas()*0.02;
        double seguro2=b2.getMilhas()*0.02;
        if(seguro1<seguro2)
        {
            return 1;
        }
        if(seguro1==seguro2)
        {
            return 0;
        }
        if(seguro1>seguro2)
        {
            return -1;
        }
        return 0;
    }
}