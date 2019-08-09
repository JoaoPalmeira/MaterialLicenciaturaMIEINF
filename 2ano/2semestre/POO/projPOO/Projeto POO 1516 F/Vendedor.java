import java.lang.*;
import java.util.*; 

public class Vendedor extends Utilizador
{
    // Variaveis de Instancia
    private List<Imovel> pVenda;
    private List<Imovel> vendidos;
    
    // Construtor
    public Vendedor(String m, String n, String p, String mo, int na) {
        super(m,n,p,mo,na);
        pVenda = Collections.<Imovel>emptyList();
        vendidos = Collections.<Imovel>emptyList();
    }
    public Vendedor(Vendedor v) {
        super(v.getMail(),v.getNome(),v.getPass(),v.getMorada(),v.getNasc());
        this.pVenda = Collections.<Imovel>emptyList();
        this.vendidos = Collections.<Imovel>emptyList();
    }
    // Métodos de Instancia
    @Override
    public void registaPVenda (Imovel im){
        pVenda.add(im);
        System.out.println("Imóvel registado com sucesso.");
    }
    public List<Imovel> getPVenda() {
        return this.pVenda;
    }
    public Vendedor clone() {
        return new Vendedor(this);
    }
    @Override
    public void showUtilizador() {
        System.out.println("\n____________________");
        System.out.print("Vendedor:\n " + this.getNome() + " - " + this.getNasc() + "\nE-mail: " + this.getMail());

    }
}