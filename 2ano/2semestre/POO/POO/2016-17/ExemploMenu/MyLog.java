
/**
 * Classe que guardar uma lista de valores.
 * Utilizada para ter alguma lógica de negócio no exemplo de
 * utilização de menus.
 * 
 */
import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
public class MyLog {
    private List<Integer> lista;
    
    public MyLog() {
        this.lista = new ArrayList<>();
    }
    
    public void registaValor(int i) {
        this.lista.add(i);
    }
    
    public int getUltValor() {
        return this.lista.get(this.lista.size()-1);
    }
    
    public List<Integer> getValores() {
        return this.lista.stream().collect(toList()); // Integer são imutáveis 
    }
}
