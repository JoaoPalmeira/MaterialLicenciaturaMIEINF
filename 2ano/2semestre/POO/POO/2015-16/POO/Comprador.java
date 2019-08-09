import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class Comprador extends Utilizador
{
    private List<Imovel> imoveisFavoritos;
    
    public Comprador () {
        super();
        this.imoveisFavoritos = new ArrayList<Imovel>();
    }
    public Comprador (String email,
                      String nome,
                      String password,
                      String morada,
                      Date dataNasc,
                      List<Imovel> imoveisFavoritos) {
        super(email,nome,password,morada,dataNasc);
        if (imoveisFavoritos==null) this.imoveisFavoritos = new ArrayList<Imovel>();
        else this.imoveisFavoritos = imoveisFavoritos;
    }
    public Comprador (Comprador c) {
        super(c);
        this.imoveisFavoritos = c.getImoveisFavoritos();
    }
    public List<Imovel> getImoveisFavoritos () {return imoveisFavoritos;}
    public void setImoveisFavoritos (List<Imovel> imoveisFavoritos) {this.imoveisFavoritos=imoveisFavoritos;}
    public Comprador clone(){return new Comprador(this);}
    
    
    public void addFavorito (Imovel im) {
        this.imoveisFavoritos.add(im.clone());
    }
}
