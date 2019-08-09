import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

class ValueComparator1 implements Comparator<Empresa> {
    Map<Empresa,List<Despesa>> map;
 
    public ValueComparator1(Map<Empresa,List<Despesa>> map) {
        this.map = map;
    }
    
    public int compare(Empresa e1, Empresa e2) {
        if(map.get(e1).size() < map.get(e2).size()){
            return 1;
        }
        else{
            return -1;
        }
    }
}