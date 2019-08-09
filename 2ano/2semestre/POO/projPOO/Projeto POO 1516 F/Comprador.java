
/**
 * Tipo de Utilizador envolvido na aplicação
 */
import java.util.*;

public class Comprador extends Utilizador {
    // Variáveis de Instância
    private TreeSet<Imovel> favoritos;
    
    // Construtor
    public Comprador(String m, String n, String p, String mo, int na) {
        super(m,n,p,mo,na);
        favoritos = null;
    }
    
    // Métodos de Instância
    
    public TreeSet<Imovel> getFavoritos() {
        return favoritos;
    }
    
    public void adicionaFavorito(Imovel im) {
        favoritos.add(im);
    }
    
    @Override
    public void showUtilizador() {
        System.out.println("____________________");
        System.out.print("Comprador:\n " + this.getNome() + " - " + this.getNasc() + "\nE-mail: " + this.getMail());

    }
  
}

