import java.util.Scanner;
import java.util.Arrays;
/**
 * Escreva a descrição da classe Produto aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Produto
{
   private String codigo;
   private String nome;
   private int stock;
   private int min;
   private double precoCompra;
   private double precoVenda;

   public Produto(){
        codigo = "000aaa";
        nome = "Produto";
        stock = 0;
        min = 0;
        precoCompra = 0.0;
        precoVenda = 0.0;
   }
    
   public Produto (String cod, String no, int st, int mi, double precoC, double precoV){
        codigo = cod;
        nome = no;
        stock = st;
        min = mi;
        precoCompra = precoC;
        precoVenda = precoV;
   }
    
   public Produto (Produto p2) {
       codigo = p2.getCodigo();
       nome = p2.getNome();
       stock = p2.getStock();
       min = p2.getMin();
       precoCompra = p2.getPrecoCompra();
       precoVenda = p2.getPrecoVenda();
   }
     
   public String getCodigo(){
       return codigo;
   }
   
   public String getNome(){
       return nome;
   }
   
   public int getStock(){
       return stock;
   }
   
   public int getMin(){
       return min;
   }
   
   public double getPrecoCompra(){
       return precoCompra;
   }
   
   public double getPrecoVenda(){
       return precoVenda;
   }
   
   public void setCodigo (String codigo) {
       this.codigo = codigo;
   }
   
   public void setNome (String nome) {
       this.nome = nome;
   }
   
   public void setStock (int stock) {
       this.stock = stock;
   }
   
   public void setMin (int min) {
       this.min = min;
   }
   
   public void setPrecoCompra (double precoCompra) {
       this.precoCompra = precoCompra;
   }
   
   public void setPrecoVenda (double precoVenda) {
       if (precoVenda >= this.precoCompra) this.precoVenda = precoVenda;
   }
   
   public void modificaStock (int valor) {
       if (stock + valor >= 0) stock += valor;
   }
   
   public void alteraCodigo (String codig) {
       if (codig.length() >= 8) codigo = codig;
   }
   
   public void defineMargemLucro (double percentagem) {
       precoVenda = precoCompra * (1 + (percentagem/100));
   }
   
   public void efectuaCompra (double valor) {
       if (stock - (valor/precoVenda) >= 0) stock -= (valor/precoVenda);
   }
   
   public double lucroTotal(){
       double lucro = stock * precoVenda;
       return lucro;
   }
       
   public double precoTotal (int encomenda){
       double preco = encomenda * precoVenda;
       return preco;
   }
   
   public boolean abaixoValor(){
       if (stock < min) return true;
       else return false;
   }
   
      public Produto clone (){
        return new Produto (this);
   }
    
   public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if ((o == null) || o.getClass() != this.getClass()){
            return false;
        }
        Produto n = (Produto) o;
        return n.getCodigo().equals(codigo) && n.getNome().equals(nome) && n.getStock() == stock && n.getMin() == min && n.getPrecoCompra() == precoCompra && n.getPrecoVenda() == precoVenda;
   }
    
   public String toString(){
        return codigo + "," + nome + "," + stock + "," + min + "," + precoCompra + "," + precoVenda;
   }
}