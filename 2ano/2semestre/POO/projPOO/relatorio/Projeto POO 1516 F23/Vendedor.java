import java.lang.*;
import java.util.*; 

public class Vendedor extends Utilizador
{
    // Variaveis de Instancia
    //Lista dos imóveis para venda.
    private List<Imovel> pVenda;
    //Lista dos imóveis vendidos.
    private List<Imovel> vendidos;
    
    // Construtor
    //Função que cria o construtor Vendedor com as variáveis mail, nome, palavra-passe, morada e data de nascimento.
    public Vendedor(String m, String n, String p, String mo, int na) {
        super(m,n,p,mo,na);
        pVenda = Collections.<Imovel>emptyList();
        vendidos = Collections.<Imovel>emptyList();
    }
    public Vendedor() {
        super("","","","",0);
        pVenda = Collections.<Imovel>emptyList();
        vendidos = Collections.<Imovel>emptyList();
    }
    
    //Função que importa os dados do vendedor.
    public Vendedor(Vendedor v) {
        super(v.getMail(),v.getNome(),v.getPass(),v.getMorada(),v.getNasc());
        this.pVenda = Collections.<Imovel>emptyList();
        this.vendidos = Collections.<Imovel>emptyList();
    }
    // Métodos de Instancia
    
    //Função que regista o imóvel na lista de imóveis para venda.
    @Override
    public void registaPVenda (Imovel im){
        pVenda.add(im);
        System.out.println("Imóvel registado com sucesso.");
    }
    
    //Função que retorna a lista dos imóveis para venda.
    public List<Imovel> getPVenda() {
        return this.pVenda;
    }
    public Vendedor clone() {
        return new Vendedor(this);
    }
    
    @Override
    //Função que mostra que este utilizador é um cliente (vendedor) apresentando o nome, a data de nascimento e o email.
    public void showUtilizador() {
        System.out.println("\n____________________");
        System.out.println("Vendedor:\n " + this.getNome() + " - " + this.getNasc() + "\nE-mail: " + this.getMail());

    }
}