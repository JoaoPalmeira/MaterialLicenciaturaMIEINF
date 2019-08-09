
/**
 * Sub Classe de Imovel. Adiciona vários parâmetros únicos.
 */
public class Loja extends Imovel {
    // Variáveis de Instância
    private String neg;
    private double areaLoja;
    private int portaLoja;
    private Boolean wcLoja;
    
    //Construtor
    public Loja (String i, String r, double p, double pm, int v, Estado e, String n, double areaL, int portaL, Boolean wcL) {
        super(i,r,p,pm,v,e);
        neg = n;
        areaLoja = areaL;
        portaLoja = portaL;
        wcLoja = wcL;
    }
    public Loja(Loja c) {
        super(c.getId(),c.getRua(),c.getPreco(),c.getPrecoMin(),c.getView(),c.retEstado());
        this.neg =  c.getNeg();
        this.areaLoja = c.getAreaLoja();
        this.portaLoja = c.getPortaLoja();
        this.wcLoja = c.getWcLoja();
    }
    //Métodos de Instância
    public String getNeg() { return neg; }
    public double getAreaLoja() { return areaLoja; }
    public int getPortaLoja() { return portaLoja; }
    public Boolean getWcLoja() { return wcLoja; }
    public Loja clone() {
        return new Loja(this);
    }
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