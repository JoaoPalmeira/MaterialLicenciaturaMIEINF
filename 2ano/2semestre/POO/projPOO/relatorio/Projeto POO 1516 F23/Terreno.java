
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
    //É o construtor que contém todas as variáveis sobre o terreno tais como o tipo de terreno, diâmetro da canalização, consumo energético e se tem ou não esgoto.
    public Terreno (String i, String r, double p, double pm, int v, Estado es, String t, int d, float k, Boolean e) {
        super(i,r,p,pm,v,es);
        tipoTerreno = t;
        diametro = d;
        kwh = k;
        esgoto = e;
    }
    public Terreno (){
        super("","",0.0,0.0,0,Estado.OCUPADO);
        this.tipoTerreno = "";
        this.diametro = 0;
        this.kwh = 0;
        this.esgoto = false;
    }
    
    //Função que importa os dados de um terreno.
    public Terreno (Terreno c) {
        super(c.getId(),c.getRua(),c.getPreco(),c.getPrecoMin(),c.getView(),c.retEstado());
        this.tipoTerreno = c.getTipoTerreno();
        this.diametro = c.getDiametro();
        this.kwh = c.getKwh();
        this.esgoto = c.getEsgoto();
    }
    //Métodos de Instância
    //Retornam as variáveis definidas anteriormente.
    public String getTipoTerreno() { return tipoTerreno; }
    public int getDiametro() { return diametro; }
    public float getKwh() { return kwh; }
    public Boolean getEsgoto() { return esgoto; }
    public Terreno clone() {
        return new Terreno(this);
    }
    
    //Função que permite seleccionar um imóvel (neste caso a terreno) e fornece ao utilizador as características da terreno.
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
