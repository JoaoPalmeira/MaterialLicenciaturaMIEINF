import java.util.*;
import java.io.*;

public abstract class Barco implements Serializable{
    
    private String id;
    private double milhas;
    private String categoria;
    private double autonomia;
    private ArrayList<RegistoEtapa> listaReg;
    
    public Barco(){
        this.id="";
        this.milhas=0.0;
        this.categoria="";
        this.autonomia=0.0;
        this.listaReg=new ArrayList<>();
    }
    
    public Barco(String id,double milhas,String categoria,double autonomia,ArrayList<RegistoEtapa> listaReg){
        this.id=id;
        this.milhas=milhas;
        this.categoria=categoria;
        this.autonomia=autonomia;
        this.listaReg=listaReg;
    }
    
    public Barco(Barco b){
        this.id=b.getID();
        this.milhas=b.getMilhas();
        this.categoria=b.getCategoria();
        this.autonomia=b.getAutonomia();
        this.listaReg=b.getListaReg();
    }
    
    public String getID(){return this.id;}
    public double getMilhas(){return this.milhas;}
    public String getCategoria(){return this.categoria;}
    public double getAutonomia(){return this.autonomia;}
    public ArrayList<RegistoEtapa> getListaReg(){return this.listaReg;}
     
    public boolean equals(Object o){
        if(o==this) return true;
        if((o==null)||(this.getClass()!=o.getClass())){
            return false;
        }
        Barco a=(Barco) o;
        return (this.getID().equals(a.getID()));
    }
    
    public abstract Barco clone();
}
