import java.util.*;
import java.io.*;

public class Equipa implements Serializable{

    private String nome;
    private HashMap<String,Barco> barcos;

    public Equipa(){
        this.nome="";
        this.barcos=new HashMap<String,Barco>();
    }
    public Equipa(String nome,HashMap<String,Barco> barcos){
        this.nome=nome;
        this.barcos=barcos;
    }
    public Equipa(Equipa e){
        this.nome=e.getNome();
        this.barcos=e.getBarcos();
    }

    public String getNome(){return this.nome;}
    public HashMap<String,Barco> getBarcos(){
        HashMap<String,Barco> aux=new HashMap<String,Barco>();
        for(Barco b:barcos.values())
            aux.put(b.getID(),b.clone());
        return aux;
    }

    
    public double totalEmProva(String idBarco){
        double res=0;
        if(barcos.containsKey(idBarco)){
            for(Barco b:barcos.values()){
                if(b.getID().equals(idBarco)){
                    for(RegistoEtapa r:b.getListaReg()){
                        res+=r.dif();
                    }
                    return res;
                }
            }
        }
        return res;
    }

    public double registoMaisLongo(){
        double max=0;
        for(Barco b:barcos.values()){
            if(this.totalEmProva(b.getID())>max){
                max=this.totalEmProva(b.getID());
            }
        }
        return max;
    }
    
    public TreeSet<Barco> getBarcosOrdBySeguro(){
        TreeSet<Barco> barcosSeguro = new TreeSet<Barco> (new SalarioComparator());
        for(Barco b:barcos.values()){
            if(b instanceof BarcoCatamaran || b instanceof BarcoHibrido){
                barcosSeguro.add(b.clone());
            }
        }
        return barcosSeguro;
    }

    
    public boolean equals(Object o){
        if(o==this) return true;
        if((o==null)||(this.getClass()!=o.getClass())){
            return false;
        }
        Equipa a=(Equipa) o;
        return (this.getNome().equals(a.getNome()));
    }

    public Equipa clone(){
        return new Equipa(this);
    }
}