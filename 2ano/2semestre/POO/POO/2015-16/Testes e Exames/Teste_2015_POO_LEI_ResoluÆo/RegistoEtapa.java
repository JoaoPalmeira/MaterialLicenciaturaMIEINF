import java.util.*;
import java.io.*;

public class RegistoEtapa implements Serializable{
    
    private String nome;
    private double milhas;
    private GregorianCalendar inicio;
    private GregorianCalendar fim;
    
    public RegistoEtapa(){
        this.nome="";
        this.milhas=0.0;
        this.inicio=null;
        this.fim=null;
    }
    
    public RegistoEtapa(String nome,double milhas,GregorianCalendar inicio,GregorianCalendar fim){
        this.nome=nome;
        this.milhas=milhas;
        this.inicio=inicio;
        this.fim=fim;
    }
    
    public RegistoEtapa(RegistoEtapa r){
        this.nome=r.getNome();
        this.milhas=r.getMilhas();
        this.inicio=r.getInicio();
        this.fim=r.getFim();
    }
    
    public String getNome(){return this.nome;}
    public double getMilhas(){return this.milhas;}
    public GregorianCalendar getInicio(){return (GregorianCalendar) this.inicio.clone();}
    public GregorianCalendar getFim(){return (GregorianCalendar) this.fim.clone();}
    
    
    public double dif(){
        long init,fin,dif;
        init=this.inicio.getTimeInMillis();
        fin=this.fim.getTimeInMillis();
        dif=fin-init;
        return (double)dif;
    }
    
    
    public boolean equals(Object o){
        if(o==this) return true;
        if((o==null)||(this.getClass()!=o.getClass())){
            return false;
        }
        RegistoEtapa a=(RegistoEtapa) o;
        return (this.getNome().equals(a.getNome()));
    }
    
    public RegistoEtapa clone(){
        return new RegistoEtapa(this);
    }
}