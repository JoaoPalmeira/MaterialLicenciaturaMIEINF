
/**
 * Write a description of class Terreno here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Terreno extends Imovel {
    // Variávies de Instância
    private String tipoTerreno;
    private int diametro;
    private float kwh;
    private Boolean esgoto;
    
    // Construtor
    public Terreno (String i, String r, double p, double pm, int v, Estado es, String t, int d, float k, Boolean e) {
        super(i,r,p,pm,v,es);
        tipoTerreno = t;
        diametro = d;
        kwh = k;
        esgoto = e;
    }
    
    //Métodos de Instância
    public String getTipoTerreno() { return tipoTerreno; }
    public int getDiametro() { return diametro; }
    public float getKwh() { return kwh; }
    public Boolean getEsgoto() { return esgoto; }
    
    public void showTerreno() {
        this.incView(); // Incrementar o contador de visitas de um imóvel
        this.showImovel();
        System.out.println("Tipo de Terreno: " + this.getTipoTerreno());
        System.out.println("Diâmetro da canalização: " + this.getDiametro() + " mm");
        System.out.println("Potência Máxima suportada: " + this.getKwh() + " kWh");
        if (this.getEsgoto() == true) System.out.println("Tem acesso à rede de esgotos.\n____________________");
        else System.out.println("Não tem acesso à rede de esgotos.\n____________________");
    }
}
