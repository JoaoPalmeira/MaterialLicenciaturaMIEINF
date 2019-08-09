
public class CartaoCliente
{
     private int pontos;
     private double valorTotal;
     private String codigo;
     private String nome;
     private int valorBonus;
     
     public CartaoCliente(double valorTotal, int pontos, String codigo, String nome, int valorBonus) 
     {
        this.valorTotal = valorTotal;
        this.pontos = pontos;
        this.codigo = codigo;
        this.nome = nome;
        this.valorBonus = valorBonus;
    }
    
    public CartaoCliente() 
    {
        new CartaoCliente(0,0,"a00000","Nome",0);
    }
    
    public CartaoCliente(CartaoCliente c2) 
    {
        this.valorTotal = c2.getValorTotal();
        this.pontos = c2.getPontos();
        this.codigo = c2.getCodigo();
        this.nome = c2.getNome();
        this.valorBonus = c2. getValorBonus();
    }
    
    public int getPontos() 
    {
        return this.pontos;
    }
    
    public double getValorTotal() 
    {
        return this.valorTotal;
    }
    
    public String getCodigo() 
    {
        return this.codigo;
    }
    
    public String getNome() 
    {
        return this.nome;
    }
    
    public int getValorBonus() 
    {
        return this.valorBonus;
    }
    
    public void descontar(int menu) {
        if (menu == 1 && this.pontos >= 10) this.pontos -= 10;
        if (menu == 2 && this.pontos >= 20) this.pontos -= 20;
    }
    
     public void setNome(String nome) 
     {
        this.nome = nome;
    }
    
    public void setValorBonus(int valorBonus) 
    {
        this.valorBonus = valorBonus;
    }
    
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    public void descarregarPontos(CartaoCliente cartao)
    {
        int aux = this.pontos + cartao.getPontos();
        if (this.valorBonus > this.pontos && aux >= this.valorBonus) this.pontos = 10 + aux;
        else this.pontos = aux;
        cartao.setPontos(0);
    }
    
    public void efectuarCompra(double valor)
    {
        int pontos;
        this.valorTotal += valor;
        if (valor <= 5) pontos = 1;
        else pontos = 2;
        if (this.pontos < this.valorBonus && (this.pontos + pontos) >= this.valorBonus) this.pontos += pontos + 10;
        else this.pontos += pontos;
    }
}

