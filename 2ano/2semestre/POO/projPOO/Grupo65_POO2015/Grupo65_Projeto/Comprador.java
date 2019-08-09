
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
    public Comprador() {
        super("","","","",0);
        this.favoritos = null;
    }
    public Comprador(Comprador v) {
        super(v.getMail(),v.getNome(),v.getPass(),v.getMorada(),v.getNasc());
        this.favoritos = v.verFavoritos();
    }
    // Métodos de Instância
    public Comprador clone() {
        return new Comprador(this);
    }
    public TreeSet<Imovel> verFavoritos() {
        return this.favoritos;
    }
    /**
     * Função auxiliar que adiciona um imóvel aos favoritos de um Comprador.
     */
    public void adicionaFavorito(Imovel im) {
        this.favoritos.add(im);
    }
    /**
     * Função de print dos dados de um comprador.
     */
    @Override
    public void showUtilizador() {
        System.out.println("____________________");
        System.out.println("Comprador:\n " + this.getNome() + " - " + this.getMorada() + "\nE-mail: " + this.getMail());

    }
  
}

