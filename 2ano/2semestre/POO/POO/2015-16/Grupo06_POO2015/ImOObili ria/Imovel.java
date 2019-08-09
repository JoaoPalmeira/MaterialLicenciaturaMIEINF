import java.lang.String;
import java.lang.Boolean;
/**
 * Escreva a descrição da classe Imovel aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Imovel
{
    /**
     * variáveis de instância
     */ 
    private String codigo;
    private String tipoImovel;
    private String rua;
    private String estado;
    private double precoPedido;
    private double precoMinimo;
    private int visualizacoes;
    private String mail;
    
    /**
     * Construtor por omissão
     */
    public Imovel(){
        codigo = "";
        tipoImovel = "";
        rua = "";
        estado = "";
        precoPedido = 0.0;
        precoMinimo = 0.0;
        visualizacoes = 0;
        mail = "";
    }
    
    /** 
     * Construtor a partir das partes
     */
    public Imovel(String cod, String tipoI, String ru, String est, double precoP, double precoM, int visualiz, String ma){
        codigo = cod;
        tipoImovel = tipoI;
        rua = ru;
        estado = est;
        precoPedido = precoP;
        precoMinimo = precoM;
        visualizacoes = visualiz;
        mail = ma;
    }
    
    /**
     * Constutor por cópia
     */
    public Imovel(Imovel i){
        codigo = i.getCodigo();
        tipoImovel = i.getTipoImovel();
        rua = i.getRua();
        estado = i.getEstado();
        precoPedido = i.getPrecoPedido();
        precoMinimo = i.getPrecoMinimo();
        visualizacoes = i.getVisualizacoes();
        mail = i.getMail();
    }
    
    /**
     * Métodos de instância
     */
    public String getCodigo(){
        return codigo;
    }
    
    public String getTipoImovel(){
        return tipoImovel;
    }
    
    public String getRua(){
        return rua;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public double getPrecoPedido(){
        return precoPedido;
    }
    
    public double getPrecoMinimo(){
        return precoMinimo;
    }
    
    public int getVisualizacoes(){
        return visualizacoes;
    }
    
    public String getMail(){
        return mail;
    }
    
    public void setCodigo(String cod){
        codigo = cod;
    }
    
    public void setTipoImovel(String tImovel){
        tipoImovel = tImovel;
    }
    
    public void setRua(String r){
        rua = r;
    }
    
    public void setEstado(String e){
        estado = e;
    }
    
    public void setPrecoPedido(double pPedido){
        precoPedido = pPedido;
    }
    
    public void setPrecoMinimo(double pMinimo){
        precoMinimo = pMinimo;
    }
    
    public void setVisualizacoes(int v){
        visualizacoes = v;
    }
    
    public void setMail(String m){
        mail = m;
    }
        
    /**
     * Aumenta o número de visualizações
     */
    public void adicionaVisualizacoes(){
        visualizacoes += 1;
    }
    
    /**
     * Retorna uma cópia da instância
     */
    public Imovel clone(){
        return new Imovel(this);
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
        Imovel o = (Imovel) obj;
        return o.getCodigo().equals(codigo) && o.getTipoImovel().equals(tipoImovel) && o.getRua().equals(rua) &&
              o.getEstado().equals(estado) && o.getPrecoPedido() == precoPedido && 
              o.getPrecoMinimo() == precoMinimo && o.getVisualizacoes() == visualizacoes && o.getMail().equals(mail);
    }
    
      /**
     * Devolve sobre a forma de uma string toda a informação 
     * atual sobre um imóvel
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("--------------- IMOVEL ---------------\n");
        s.append("Código do Imóvel: " + codigo + "\n");
        s.append("Tipo do Imóvel: " + tipoImovel + "\n");
        s.append("Rua: " + rua + "\n");
        s.append("Estado do Imóvel: " + estado + "\n");
        s.append("Preço Pedido: " + precoPedido + "\n");
        s.append("Preço Mínimo: " + precoMinimo + "\n");
        s.append("Número de Visualizações: " + visualizacoes + "\n");
        s.append("E-mail do Vendedor: " + mail + "\n");
        return s.toString();
    }
}
