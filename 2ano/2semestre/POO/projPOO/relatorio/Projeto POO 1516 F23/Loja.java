
/**
 * Write a description of class Loja here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Loja extends Imovel {
    // Variáveis de Instância
    private String neg;
    private double areaLoja;
    private int portaLoja;
    private Boolean wcLoja;
    
    //Construtor
    //É o construtor que contém todas as variáveis sobre a loja tais como o tipo de negócio, a área da loja, se tem ou não wc e o número de portas.
    public Loja (String i, String r, double p, double pm, int v, Estado e, String n, double areaL, int portaL, Boolean wcL) {
        super(i,r,p,pm,v,e);
        neg = n;
        areaLoja = areaL;
        portaLoja = portaL;
        wcLoja = wcL;
    }
    
    //Função que importa os dados de uma loja.
    public Loja(Loja c) {
        super(c.getId(),c.getRua(),c.getPreco(),c.getPrecoMin(),c.getView(),c.retEstado());
        this.neg =  c.getNeg();
        this.areaLoja = c.getAreaLoja();
        this.portaLoja = c.getPortaLoja();
        this.wcLoja = c.getWcLoja();
    }
    //Métodos de Instância
    //Retornam as variáveis definidas anteriormente.
    public String getNeg() { return neg; }
    public double getAreaLoja() { return areaLoja; }
    public int getPortaLoja() { return portaLoja; }
    public Boolean getWcLoja() { return wcLoja; }
    public Loja clone() {
        return new Loja(this);
    }
    
    //Função que permite seleccionar um imóvel (neste caso a loja) e fornece ao utilizador as características da loja.
    public void showLoja() {
        this.incView(); // Incrementar o contador de visitas de um imóvel
        this.showImovel();
        System.out.println("Tipo de Negócio: " + this.getNeg());
        System.out.println("Área da Loja: " + this.getAreaLoja() + " m2");
        System.out.println("Nº da Porta: " + this.getPortaLoja());
        if (this.getWcLoja() == true) System.out.println("A Loja tem WC privativo.");
        else System.out.println("A Loja não tem WC privativo.");
    }
   
}