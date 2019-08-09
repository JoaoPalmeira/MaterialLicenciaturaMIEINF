
/**
 * Tipo de Utilizador envolvido na aplicação
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
    public List<Imovel> getFavoritos() {
        return favoritos;
    }
    
    public void setFavorito(String idImovel) {
        
    }
    @Override
    public void showUtilizador() {
        System.out.println("____________________");
        System.out.print("Cliente:\n " + this.getNome() + " - " + this.getNasc() + "\nE-mail: " + this.getMail());

    }
  
}

