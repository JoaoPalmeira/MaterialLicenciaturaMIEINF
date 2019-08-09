import java.lang.String;
import java.lang.Boolean;
import java.util.List;
import java.util.ArrayList;
/**
 * Escreva a descrição da classe Vendedor aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Vendedor extends Utilizador
{
    // variáveis de instância
    private List<Imovel> vendidos;
    private List<Imovel> paraVenda;
    
    // construtores
    public Vendedor(){
        super();
        vendidos = new ArrayList<Imovel>();
        paraVenda = new ArrayList<Imovel>();
    }
    
    public Vendedor(String em, String no, String passwd, String mora, String dataN, String tipoUtil, ArrayList<Imovel> vend, ArrayList<Imovel> pVend){
        super(em, no, passwd, mora, dataN, tipoUtil);
        vendidos = new ArrayList<Imovel>();
        for(Imovel iv : vend){
            vendidos.add(iv.clone());
        }
        paraVenda = new ArrayList<Imovel>();
        for(Imovel ipv : pVend){
            paraVenda.add(ipv.clone());
        }
    }
    
    public Vendedor (List<Imovel> imoveis){
        vendidos = new ArrayList<Imovel>();
        paraVenda = new ArrayList<Imovel>();
        for(Imovel i : imoveis) 
            if (i.getEstado().equals("Em Venda")) paraVenda.add(i.clone());
            else vendidos.add(i.clone());
    }
    
    public Vendedor (Vendedor vendedor){
        super(vendedor);
        vendidos = new ArrayList<Imovel>();
        paraVenda = new ArrayList<Imovel>();
        for(Imovel i : vendedor.comoArrayList())
            if (i.getEstado().equals("Em Venda")) paraVenda.add(i.clone());
            else vendidos.add(i.clone());
    }
    
    // métodos de Instância
    public List<Imovel> getVendidos(){
        return vendidos;
    }
    
    public List<Imovel> getParaVenda(){
        return paraVenda;
    }
    
    public void setVendidos(List<Imovel> vend){
        vendidos = vend;
    }
    
    public void setParaVenda(List<Imovel> pVend){
        paraVenda = pVend;
    }
    
    /**
     * Devolve um ArrayList com todos os imóveis copiados
     */
    public ArrayList<Imovel> comoArrayList(){
        ArrayList<Imovel> res = new ArrayList<Imovel>();
        for(Imovel i : paraVenda) res.add(i.clone());
        for(Imovel i : vendidos) res.add (i.clone());
        return res;
    }
    
    /**
     * Verifica se um imóvel está à venda
     */
    public boolean verificaImovelParaVenda(Imovel i){
        return paraVenda.contains(i);
    }
    
    /**
     * Verifica se um imóvel está vendido
     */
    public boolean verificaImovelVendido(Imovel i){
        return vendidos.contains(i);
    }
    
     /**  
     * Insere um novo imóvel para venda
     */
    public void insereImovelParaVenda(Imovel i, String e){ 
        if (i.getEstado().equals("Em Venda")){     
        if (verificaImovelParaVenda(i) == false) paraVenda.add(i);
        }
    }
    
     /**  
     * Insere um novo imóvel na lista dos vendidos 
     */
    public void insereImovelVendido(Imovel i, String e){ 
        if (i.getEstado().equals("Vendido")){     
        if (verificaImovelVendido(i) == false) vendidos.add(i);
        }
    }
    
    /**
     * Remove um imóvel da lista de imóveis para venda
     */
    public void retiraImovelParaVenda(Imovel i){
        paraVenda.remove(i);
    }
    
     /**
     * Remove um imóvel da lista de imóveis vendidos
     */
    public void retiraVendido(Imovel i){
        vendidos.remove(i);
    }
    
    /**
     * Consulta um imóvel
     */
    public String consultaImovel(Imovel i){
        return i.toString();
    }
    
    /**
     * Altera o estado de um imóvel
     */
    public void alteraEstado(Imovel i, String est){
        i.setEstado(est);
    }
    
    /**
     * Número de anúncios criados
     */
    public int numAnuncios(){
       return paraVenda.size()+ vendidos.size();
    }
    
    /**
     * Número de visualizações dos anúncios
     */
    public int visuaAnuncios(Imovel i){
       return i.getVisualizacoes();  
    }
    
    /**
     * Número de vendas de imóveis
     */
    public int numVendas(){
        return vendidos.size();
    }    
    
     /**
     * Retorna uma cópia da instância
     */
    public Vendedor clone(){
        return new Vendedor(this);
    }
    
    /**
     * Compara a igualdade com outro objeto
     */
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Vendedor o = (Vendedor) obj;
        return super.equals(o) && o.getVendidos() == vendidos && o.getParaVenda() == paraVenda;
    }
    
    /**
     * Devolve uma representação textual do vendedor
     */
     public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------ VENDEDOR ------------\n");
        sb.append(super.toString()).append("\n");
        sb.append("Lista de Imóveis vendidos: " + vendidos + "\n");
        sb.append("Lista de Imóveis para venda: " + paraVenda + "\n");
        return sb.toString();
    }
    
}
