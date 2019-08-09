
/**
 * Write a description of class Produto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Produto
{
    // instance variables
    private String codigo;
    private String nome;
    private int stock;
    private int min;
    private double precoCompra;
    private double precoVenda;
    
    // constructors
    public Produto() {
        new Produto("","",0,0,0,0);
    }
    
    public Produto(String codigo, String nome, int stock, int min, double precoCompra, double precoVenda) {
        this.codigo = codigo;
        this.nome = nome;
        this.stock = stock;
        this.min = min;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
    }
    
    public Produto(Produto p2) {
        this.codigo = p2.getCodigo();
        this.nome = p2.getNome();
        this.stock = p2.getStock();
        this.min = p2.getMin();
        this.precoCompra = p2.getPrecoCompra();
        this.precoVenda = p2.getPrecoVenda();
    }
    
    // getters & setters
    
    public String getCodigo() {
        return this.codigo;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public int getStock() {
        return this.stock;
    }
    
    public int getMin() {
        return this.min;
    }
    
    public double getPrecoCompra() {
        return this.precoCompra;
    }
    
    public double getPrecoVenda() {
        return this.precoVenda;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public void setMin(int min) {
        this.min = min;
    }
    
    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }
    
    public void setPrecoVenda(double precoVenda) {
        if (precoVenda >= this.precoCompra) this.precoVenda = precoVenda;
    }
    
    // other methods
    public void modificaStock(int valor) {
        if ((this.stock + valor) >= 0) this.stock += valor;
    }
    
    public void alteraCodigo(String codigo) {
        if (codigo.length() >= 8) this.codigo = codigo;
    }
    
    public void defineMargemLucro(double percentagem) {
        this.precoVenda = this.precoCompra * (1+(percentagem/100));
    }
    
    public void efectuaCompra(double valor) {
        if ((this.stock - (valor / this.precoVenda)) >= 0) this.stock -= valor / this.precoVenda;
    }
    
    public double lucroTotal() {
        return (this.precoVenda * this.stock);
    }
    
    public double precoTotal(int encomenda) {
        return (encomenda * this.precoVenda);
    }
    
    public boolean abaixoValor() {
        return (this.stock < this.min);
    }
}
