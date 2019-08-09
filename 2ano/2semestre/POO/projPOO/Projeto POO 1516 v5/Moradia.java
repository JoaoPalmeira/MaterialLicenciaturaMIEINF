
/**
 * Write a description of class Moradia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Moradia extends Imovel implements Habitavel {
    // Variáveis de Instância
    private String tipoMorad;
    private double areaImp, areaCob, areaEnvol;
    private int wcMorad, portaMorad;
    // Construtor
    public Moradia (String i,String r, double p, double pm, int v, String t, Estado e, double a1, double a2, double a3, int w, int por) {
        super(i,r,p,pm,v,e);
        tipoMorad = t; 
        areaImp = a1;
        areaCob = a2;
        areaEnvol = a3;
        wcMorad = w;
        portaMorad = por;
    }
    
    // Métodos de Instância
    public String getTipoM() { return tipoMorad; }
    public double getAreaImp() { return areaImp; }
    public double getAreaCob() { return areaCob; }
    public double getAreaEnvol() { return areaEnvol; }
    public int getWcMorad() { return wcMorad; }
    public int getPortaMorad() { return portaMorad; }
    
    public void showMoradia() {
        this.incView(); // Incrementar o contador de visitas de um imóvel
        this.showImovel();
        System.out.println("Tipo de Moradia: " + this.getTipoM());
        System.out.println("Área de Implantação: " + this.getAreaImp() + " m2");
        System.out.println("Área Coberta: " + this.getAreaCob() + " m2");
        System.out.println("Área Envolvente: " + this.getAreaEnvol() + " m2");
        System.out.println("Nº de WC: " + this.getWcMorad());
        System.out.println("Nº da Porta: " + this.getPortaMorad() + "\n____________________");
    }
    public void isHabitavel() {
       
    }
}
