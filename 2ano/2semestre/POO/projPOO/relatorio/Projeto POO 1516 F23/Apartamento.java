
/**
 * Write a description of class Apartamento here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.*;
import java.util.*;
import java.io.*;
public class Apartamento extends Imovel implements Habitavel{
    // Variáveis de Instância
    private String tipoApa;    
    private double areaApa;
    private int wcApa,portaApa;
    private Boolean garagem;
    
    //Construtor
    //É o construtor que contém todas as variáveis sobre o apartamento tais como o tipo de apartamento, a área do apartamento, número de wc’s, número de portas e se tem ou não garagem.
    public Apartamento (String i,String r, double p, double pm, int v, Estado e, String t, double a, int w, int por, Boolean gar) {
        super(i,r,p,pm,v,e);
        tipoApa = t;
        areaApa = a;
        wcApa = w;
        portaApa = por;
        garagem = gar;
    }
    
    //Função que importa os dados de um apartamento.
    public Apartamento(Apartamento c) {
       super(c.getId(),c.getRua(),c.getPreco(),c.getPrecoMin(),c.getView(),c.retEstado());
       this.comentarios = Collections.<String>emptyList();
       this.tipoApa = c.getTipoApa();
       this.areaApa = c.getAreaApa();
       this.wcApa = c.getWcApa();
       this.portaApa = this.getPortaApa();
       this.garagem = c.getGaragem();
    }
    // Métodos de Instância
    //Retornam as variáveis definidas anteriormente.
    public String getTipoApa() { return tipoApa; }
    public double getAreaApa() { return areaApa; }
    public int getWcApa() { return wcApa; }
    public int getPortaApa() { return portaApa; }
    public Boolean getGaragem() { return garagem; }
    
    //Função que permite seleccionar um imóvel (neste caso a apartamento) e fornece ao utilizador as características do apartamento.
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
    public void isHabitavel() {
       
    }
    public Apartamento clone() {
        return new Apartamento(this);
    }
    
        
}