
/**
 * Write a description of class Comprador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;
public class Comprador extends Utilizador {
    // Variáveis de Instância
    private List<Imovel> favoritos;
    
    // Construtor
    public Comprador(String m, String n, String p, String mo, int na) {
        super(m,n,p,mo,na);
        favoritos = null;
    }
    
    // Métodos de Instância
    public List<Imovel> getFav() {
        return favoritos;
    }
    public void setFavorito(Imovel im) {
        Imovel i = new Imovel();
        i = im;
        this.favoritos.add(i);
        System.out.println("Imóvel adicionado com sucesso.");
    }
}

