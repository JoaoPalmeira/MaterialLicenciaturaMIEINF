
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
    // Falta a parte dos apartamentos
    
    //Construtor
    public Loja (String r, double p, double pm, int v, String e, String t, String n, double areaL, int portaL, Boolean wcL) {
        super(r,p,pm,v,e);
        neg = n;
        areaLoja = areaL;
        portaLoja = portaL;
        wcLoja = wcL;
    }
    //Métodos de Instância
    public String getNeg() { return neg; }
    public double getAreaLoja() { return areaLoja; }
    public int getPortaLoja() { return portaLoja; }
    public Boolean getWcLoja() { return wcLoja; }
    
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