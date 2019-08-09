import java.util.Comparator;
/**
 * Write a description of class ComparatorLugarTempo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparatorLugarTempo implements Comparator<Lugar>
{
    public int compare(Lugar l1,Lugar l2){
        if(l1.getMinutos()<l2.getMinutos()){
            return -1;
        }
        else{
            if(l1.getMinutos()>l2.getMinutos()){
                return 1;
            }
            else{
                return l1.getNome().compareTo(l2.getNome());
   
            }
        }
    }
}
