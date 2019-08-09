import java.lang.String;
import java.lang.Boolean;
import java.util.List;
import java.util.ArrayList; 
import java.util.Set;
import java.util.TreeSet;
/**
 * Escreva a descrição da classe Comprador aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Comprador extends Utilizador
{
    // variáveis de instância
    private List<Imovel> favoritos;
    
    // construtores
    public Comprador(){
        super();
        favoritos = new ArrayList<Imovel>();
    }
    
    public Comprador(String em, String no, String passwd, String mora, String dataN, String tipoUtil, ArrayList<Imovel> favor){
        super(em, no, passwd, mora, dataN, tipoUtil);
        favoritos = new ArrayList<Imovel>();
        for(Imovel f : favor){
            favoritos.add(f.clone());
        }
    }
    
    public Comprador (List<Imovel> favorite){
        favoritos = new ArrayList<Imovel>();
        for(Imovel i : favorite) favoritos.add(i.clone());
    }
    
    public Comprador (Comprador comprador){
        super(comprador);
        favoritos = new ArrayList<Imovel>();
        for(Imovel i : comprador.comoArrayList())
            favoritos.add(i.clone());
    }
    
    public List<Imovel> getFavoritos(){
        return favoritos;
    }
    
    public void setFavoritos(List<Imovel> favor){
        favoritos = favor;
    }
    
    // métodos de Instância
    /**
     * Devolve um ArrayList com todos os imóveis copiados
     */
    public ArrayList<Imovel> comoArrayList(){
        ArrayList<Imovel> res = new ArrayList<Imovel>();
        for(Imovel i : favoritos) res.add(i.clone());
        return res;
    }
    
    /**
     * Verifica se um imóvel já existe nos favoritos
     */
    public boolean verificaImovel(Imovel i){
        return favoritos.contains(i);
    }
    
    /**
     * Pesquisa imóveis dado o tipo de imóvel
     */
    public ArrayList<Imovel> pesquisaTipo(String t, ArrayList<Imovel> imoveis){
        ArrayList<Imovel> res = new ArrayList<Imovel>();
        for(Imovel i : imoveis)
            if (i.getTipoImovel().equals(t)) res.add(i.clone());
        return res;
    }
    
    /**
     * Pesquisa imóveis dada a rua
     */
    public ArrayList<Imovel> pesquisaRua(String r, ArrayList<Imovel> imoveis){
        ArrayList<Imovel> res = new ArrayList<Imovel>();
        for(Imovel i : imoveis)
            if (i.getRua().equals(r)) res.add(i.clone());
        return res;
    }
    
    /**
     * Pesquisa imóveis dado o estado do imóvel
     */
    public ArrayList<Imovel> pesquisaEstado(String est, ArrayList<Imovel> imoveis){
        ArrayList<Imovel> res = new ArrayList<Imovel>();
        for(Imovel i : imoveis)
            if (i.getEstado().equals(est)) res.add(i.clone());
        return res;
    }
    
    /**
     * Pesquisa imóveis dado o preço (pedido)
     */
    public ArrayList<Imovel> pesquisaPreco(double p, ArrayList<Imovel> imoveis){
        ArrayList<Imovel> res = new ArrayList<Imovel>();
        for(Imovel i : imoveis)
            if (i.getPrecoPedido() == p) res.add(i.clone());
        return res;
    }
    
    /**
     * Pesquisa imóveis dado o número de visualizações
     */
    public ArrayList<Imovel> pesquisaVisualizacoes(int v, ArrayList<Imovel> imoveis){
        ArrayList<Imovel> res = new ArrayList<Imovel>();
        for(Imovel i : imoveis)
            if (i.getVisualizacoes() == v) res.add(i.clone());
        return res;
    }
    
    /**
     * Verifica se está registado
     */
    public boolean registado(String e, Utilizador u){
        boolean r=false;
        if(u.getEmail().equals(e)) r=true;
        return r;
    }
    
    /**  
     * Insere um novo imóvel favorito
     */
    public void insereImovel(Imovel i, String e, Utilizador u){
        if ((registado(e,u)) == true){ 
            if (verificaImovel(i) == false) favoritos.add(i);
        }
    }
    
    /**
     * Remove um imóvel favorito
     */
    public void retiraImovel(Imovel i){
        favoritos.remove(i);
    }
    
    /**
     * Conta o número total de favoritos
     */
    public int totalFavoritos(){
        int total = 0;
        for(Imovel i : favoritos)
            total+=1;
        return total;
    }
    
    /**
     * Conta o número de favoritos de um dado tipo
     */
    public int totalTipFav(String t){
        int tot = 0;
        for(Imovel i : favoritos){
            if(i.getTipoImovel().equals(t))
                tot+=1;
        }
        return tot;
    }
    
    /**
    * Lista de imóveis favoritos com preço inferior ao dado
    */
    public ArrayList<Imovel> precoInferior(double p){
        ArrayList<Imovel> res = new ArrayList<Imovel>();
        for(Imovel i : favoritos){
            if(i.getPrecoPedido() < p) res.add(i);
        }
        return res;
    }
   
    /**
     * Retorna uma cópia da instância
     */
    public Comprador clone(){
        return new Comprador(this);
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
        Comprador o = (Comprador) obj;
        return super.equals(o) && o.getFavoritos() == favoritos;
    }
    
    /**
     * Devolve uma representação textual do comprador
     */
     public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------ COMPRADOR ------------\n");
        sb.append(super.toString()).append("\n");
        sb.append("Lista de Imóveis favoritos: " + favoritos + "\n");
        return sb.toString();
    }
}    
