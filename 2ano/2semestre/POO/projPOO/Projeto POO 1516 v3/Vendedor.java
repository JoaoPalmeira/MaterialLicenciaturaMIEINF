
import java.util.List; 
public class Vendedor extends Utilizador{
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
    public class ImovelExisteException extends Exception {
        public ImovelExisteException(String m) {
            super(m); // Não funciona corretamente.
        }
    }
    //isto
    public class SemAutorizacaoException extends Exception {
        public SemAutorizacaoException(String m) {
            super(m); // Não funciona corretamente.
        }
    }
    
    public void registaImovel (Imovel im) throws ImovelExisteException{
        if (pVenda.contains(im)){ 
            throw new ImovelExisteException("Imovel já existe.");
        }
        else pVenda.add(im);
        System.out.println("Imóvel registado com sucesso.");
    }
    
    /*public List <Consulta> getConsultas (){
        
    }*/
}