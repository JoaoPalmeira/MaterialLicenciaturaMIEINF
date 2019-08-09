import java.util.Scanner;
import java.util.Arrays;
/**
 * Escreva a descrição da classe CartaoCliente aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class CartaoCliente
{
   private int pontos;
   private double valorTotal;
   private String codigo;
   private String nome;
   private int valorBonus;

   public CartaoCliente(){
        pontos = 0;
        valorTotal = 0.0;
        codigo = "a00000";
        nome = "Nome";
        valorBonus = 0;
   }
    
   public CartaoCliente (int pont, double valorT, String cod, String no, int valorB){
       pontos = pont;
       valorTotal = valorT;
       codigo = cod;
       nome = no;
       valorBonus = valorB;
   }
    
   public CartaoCliente (CartaoCliente c2) {
       pontos = c2.getPontos();
       valorTotal = c2.getValorTotal();
       codigo = c2.getCodigo();
       nome = c2.getNome();
       valorBonus = c2.getValorBonus();
   }
    
    public int getPontos(){
       return pontos;
   }
   
   public double getValorTotal(){
       return valorTotal;
   }
    
   public String getCodigo(){
       return codigo;
   }
   
   public String getNome(){
       return nome;
   }
   
   public int getValorBonus(){
       return valorBonus;
   }
   
   public void setPontos (int pontos ) {
       this.pontos = pontos;
   }
   
   public void setValorTotal (double valorTotal) {
       this.valorTotal = valorTotal;
   }
   
   public void setCodigo (String codigo) {
       this.codigo = codigo;
   }
   
   public void setNome (String nome) {
       this.nome = nome;
   }
   
   public void setValorBonus (int valorBonus) {
       this.valorBonus = valorBonus;
   }
   
   public void descontar (int menu){
       if (menu == 1 && pontos >= 10) pontos -= 10;
       if (menu == 2 && pontos >= 20) pontos -= 20;
   }
   
   public void descarregarPontos (CartaoCliente cartao){
       int somaPontos = pontos + cartao.getPontos();
       if (pontos > valorBonus && somaPontos >= valorBonus) pontos = somaPontos + 10;
       else pontos = somaPontos;
       cartao.setPontos(0);
   }
   
   public void efectuarCompra (double valor){
       int pontosNovos;
       valorTotal += valor;
       if (valor <=5) pontosNovos = 1;
       else pontosNovos = 2;
       if (pontos < valorBonus && (pontos + pontosNovos) >= valorBonus ) pontos += pontosNovos + 10;
       else pontos += pontosNovos;
   }
   
   public CartaoCliente clone (){
        return new CartaoCliente (this);
   }
    
   public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if ((o == null) || o.getClass() != this.getClass()){
            return false;
        }
        CartaoCliente n = (CartaoCliente) o;
        return n.getPontos() == pontos && n.getValorTotal() == valorTotal && n.getCodigo().equals(codigo) && n.getNome().equals(nome) && n.getValorBonus() == valorBonus;
   }
    
   public String toString(){
        return pontos + "," + valorTotal + "," + codigo + "," + nome + "," + valorBonus;
   }
}
