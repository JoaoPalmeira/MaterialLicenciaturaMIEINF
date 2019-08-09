
/**
 * Escreva a descrição da classe CartaoCliente aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class CartaoCliente
{
    // variaveis
    private double valorTotal;
    private int pontos;
    private String codigo;
    private String nome;
    private int valorBonus;
    
    // construtores
    public CartaoCliente() {
        new CartaoCliente(0,0,"a00000","Nome",0);
    }
    
    public CartaoCliente(double valorTotal, int pontos, String codigo, String nome, int valorBonus) {  
        this.valorTotal = valorTotal;
        this.pontos = pontos;
        this.codigo = codigo;
        this.nome = nome;
        this.valorBonus = valorBonus;
    }
    
    public CartaoCliente(CartaoCliente c1) {
        this.valorTotal = c1.getValorTotal();
        this.pontos = c1.getPontos();
        this.codigo = c1.getCodigo();
        this.nome = c1.getNome();
        this.valorBonus = c1. getValorBonus();
    }
    
    //getters & setters
    public int getPontos() {
        return this.pontos;
    }
    public double getValorTotal() {
        return this.valorTotal;
    }
    public String getCodigo() {
        return this.codigo;
    }
    public String getNome() {
        return this.nome;
    }
    public int getValorBonus() {
        return this.valorBonus;
    }
    
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setValorBonus(int valorBonus) {
        this.valorBonus = valorBonus;
    }
    
    // exercicios
    public void descontar(int menu) {
        if (menu == 1 && this.pontos >= 10) this.pontos -= 10;
        if (menu == 2 && this.pontos >= 20) this.pontos -= 20;
    }
    
    public void descarregarPontos(CartaoCliente cartao) {
        int ctaux = this.pontos + cartao.getPontos();
        if (this.valorBonus > this.pontos && ctaux >= this.valorBonus) this.pontos = 10 + ctaux;
        else this.pontos = ctaux;
        cartao.setPontos(0);
    }
    
    public void efectuarCompra(double valor) {
        int pontos;
        this.valorTotal += valor;
        if (valor <= 5) pontos = 1;
        else pontos = 2;
        if (this.pontos < this.valorBonus && (this.pontos + pontos) >= this.valorBonus) this.pontos += pontos + 10;
        else this.pontos += pontos;
    }
}
