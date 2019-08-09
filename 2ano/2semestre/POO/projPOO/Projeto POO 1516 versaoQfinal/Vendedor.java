
import java.util.List; 

public class Vendedor extends Utilizador
{
    // Variaveis de Instancia
    private List<Imovel> pVenda;
    private List<Imovel> vendidos;
    
    // Construtor
    public Vendedor(String m, String n, String p, String mo, int na) {
        super(m,n,p,mo,na);
        pVenda = null;
        vendidos = null;
    }
   
    // Métodos de Instancia
    public void registaImovel (Imovel im){
        pVenda.add(im);
        System.out.println("Imóvel registado com sucesso.");
    }
    
    /*public List <Consulta> getConsultas (){
        
    }
    
    public void setEstado (String idImovel, String estado){
        
    }*/
    
    @Override
    public void showUtilizador() {
        System.out.println("____________________");
        System.out.print("Vendedor:\n " + this.getNome() + " - " + this.getNasc() + "\nE-mail: " + this.getMail());

    }
}