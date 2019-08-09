import java.util.*;
import java.io.*;

public class BarcoRemos extends Barco implements Serializable{
    
    private double largura;
    
    public BarcoRemos(String id,double milhas,String categoria,double autonomia,ArrayList<RegistoEtapa> listaReg,double largura){
        super(id,milhas,categoria,autonomia,listaReg);
        this.largura=largura;
    }
    public BarcoRemos(BarcoRemos b){
        super(b);
        this.largura=b.getLargura();
    }

    public double getLargura(){return this.largura;}
    
    
    @Override
    public BarcoRemos clone(){
        return new BarcoRemos(this);
    }
}
