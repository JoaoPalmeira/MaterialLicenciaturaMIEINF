
/**
 * Tipo de Utilizador envolvido na aplicação
 */
import java.util.*;

public class Comprador extends Utilizador {
    // Variáveis de Instância
    //Lista dos favoritos.
    private TreeSet<Imovel> favoritos;
    
    // Construtor
    //Função que cria o construtor Comprador com as variáveis mail, nome, palavra-passe, morada e data de nascimento.
    public Comprador(String m, String n, String p, String mo, int na) {
        super(m,n,p,mo,na);
        favoritos = null;
    }
    public Comprador() {
        super("","","","",0);
        this.favoritos = null;
    }
    
    //Função que importa os dados do comprador.
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
    
    //Função que adiciona um imóvel aos favoritos.
    public void adicionaFavorito(Imovel im) {
        favoritos.add(im);
    }
    
    @Override
    //Função que mostra que este utilizador é um cliente (comprador) apresentando o nome, a data de nascimento e o email.
    public void showUtilizador() {
        System.out.println("____________________");
        System.out.println("Comprador:\n " + this.getNome() + " - " + this.getNasc() + "\nE-mail: " + this.getMail());

    }
  
}

