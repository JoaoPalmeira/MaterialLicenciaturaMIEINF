
/**
 * Write a description of class Apartamento here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Apartamento extends Imovel {
    // Variáveis de Instância
    private String tipoApa;    
    private double areaApa;
    private int wcApa,portaApa;
    private Boolean garagem;
    
    //Construtor
    public Apartamento (String r, double p, double pm, int v, String e, String t, double a, int w, int por, Boolean gar) {
        super(r,p,pm,v,e);
        tipoApa = t;
        areaApa = a;
        wcApa = w;
        portaApa = por;
        garagem = gar;
    }
    // Métodos de Instância
    public String getTipoApa() { return tipoApa; }
    public double getAreaApa() { return areaApa; }
    public int getWcApa() { return wcApa; }
    public int getPortaApa() { return portaApa; }
    public Boolean getGaragem() { return garagem; }
    
    public void showApartamento() {
        this.incView(); // Incrementar o contador de visitas de um imóvel
        this.showImovel();
        System.out.println("Tipo de Apartamento: " + this.getTipoApa());
        System.out.println("Área: " + this.getAreaApa() + " m2");
        System.out.println("Nº de WC: " + this.getWcApa());
        System.out.println("Nº da Porta: " + this.getPortaApa());
        if(this.getGaragem() == true) System.out.println("Este Apartamento tem garagem.\n____________________");
        else System.out.println("Este Apartamento não tem garagem.\n____________________");
    }
        
}