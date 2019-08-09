
/**
 * Sub Classe de Imovel. Adiciona vários parâmetros únicos.
 */
import java.lang.*;
import java.util.*;
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
    public Moradia(Moradia c) {
       super(c.getId(),c.getRua(),c.getPreco(),c.getPrecoMin(),c.getView(),c.retEstado());
       this.comentarios = Collections.<String>emptyList();
       this.tipoMorad = c.getTipoM();
       this.areaImp = c.getAreaImp();
       this.areaCob = c.getAreaCob();
       this.areaEnvol = c.getAreaEnvol();
       this.wcMorad = c.getWcMorad();
       this.portaMorad = c.getPortaMorad();
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
    @Override
    public void isHabitavel() {}
    public Imovel clone() {
        return new Moradia(this);
    }
    
}
