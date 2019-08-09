import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;

/**
 * Write a description of class Transportes here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Transportes extends ActividadeEconomica implements Serializable
{
    private double valorDeducao;
    private String descricao;
    private String tipoTransporte;       // metro, autocarro, etc
    
    public Transportes(){
        super();
        this.valorDeducao = 0.0;
        this.descricao = " ";
        this.tipoTransporte = " ";
    }
    
    public Transportes(boolean deducao,int codigo,double valorDeducao, String descricao, String tipoTransporte){
        super(deducao,codigo);
        this.valorDeducao = valorDeducao;
        this.descricao = descricao;
        this.tipoTransporte = tipoTransporte;
    }
    
    public Transportes(Transportes t){
        super(t);
        this.valorDeducao = t.getValorDeducao();
        this.descricao = t.getDescricao();
        this.tipoTransporte = t.getTipoTransporte();
    }
    
    // metodos getters
    
    public double getValorDeducao(){
        return this.valorDeducao;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public String getTipoTransporte(){
        return this.tipoTransporte;
    }
    
    // metodos setters
    
     public void setValorDeducao(double valor){
        this.valorDeducao = valor;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public void setTipoTransporte(String tipoTransporte){
        this.tipoTransporte = tipoTransporte;
    }
    
    // metodo toString
    
    public String toString(){
        StringBuilder sb =  new StringBuilder();
        sb.append(super.toString());
        sb.append("\n");
        sb.append("Valor da deducao: ").append(this.valorDeducao).append("\n");
        sb.append("Descrição do Serviço: ").append(this.descricao).append("\n");
        sb.append("Tipo de Transporte: ").append(this.tipoTransporte);
        return sb.toString();
    }
    
    // metodo clone
    
    public Transportes clone(){
        return new Transportes(this);
    }
}
