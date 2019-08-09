import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;

/**
 * Write a description of class ActividadeEconomica here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ActividadeEconomica implements Serializable{
    private boolean deduzivel; // true-> é deduzivel false -> nao é deduzivel
    private int codigo;        // codigo da Actividade Economica // 1-Restauraçao , 2-Educaçao , etc
    
    public ActividadeEconomica(){
        this.deduzivel = false;
        this.codigo = 0;
    }
    
    public ActividadeEconomica(boolean deduzivel, int codigo){
        this.deduzivel = deduzivel;
        this.codigo = codigo;
    }
    
    public ActividadeEconomica(ActividadeEconomica ae){
        this.deduzivel = ae.getDeduzivel();
        this.codigo = ae.getCodigo();
    }
    
    // metodo getters
    
    public boolean getDeduzivel(){
        return this.deduzivel;
    }
    
    public int getCodigo(){
        return this.codigo;
    }
    
    // metodo setters
    
    public void setDeduzivel(boolean deduzivel){
        this.deduzivel = deduzivel;
    }
    
     public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    
    // equals
    
    public boolean equals(Object o){
       if( o == this){
           return true;
       }
       
       if(o == null || o.getClass() != getClass()){
           return false;
       }
        
       ActividadeEconomica ae = (ActividadeEconomica) o;
       
       return (this.codigo == ae.getCodigo() &&
               this.deduzivel == ae.getDeduzivel());         
    } 
    
    // toString()
    
    public String toString(){
        StringBuilder sb = new StringBuilder();    
        if(this.codigo==1){sb.append("Restauracao ");}
        if(this.codigo==2){sb.append("Educacao ");}
        if(this.codigo==3){sb.append("Servicos ");}
        if(this.codigo==4){sb.append("Saude ");}
        if(this.codigo==5){sb.append("ReparacaoVeiculos ");}
        if(this.codigo==6){sb.append("Transportes ");}
        if(this.codigo==7){sb.append("Habitacao ");}
        return sb.toString();
    }
    
    // clone
    
    public ActividadeEconomica clone(){
        return new ActividadeEconomica(this);
    }
    
    
}
