import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;

/**
 * Aticidade economica de Servicos
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Servicos extends ActividadeEconomica implements Serializable 
{
    private double valorDeducao;
    private String descricao;        
    private String tipoServico;         // agua/eletricidade/gas/saneamento
    
    public Servicos(){
        super();
        this.valorDeducao = 0.0;
        this.descricao = " ";
        this.tipoServico = " ";
    }
    
    public Servicos(boolean deducao,int codigo, double valorDeducao, String descricao, String tipoServico){
        super(deducao,codigo);
        this.valorDeducao = valorDeducao;
        this.descricao = descricao;
        this.tipoServico = tipoServico;
    }
    
    public Servicos(Servicos s){
        super(s);
        this.valorDeducao = s.getValorDeducao();
        this.descricao = s.getDescricao();
        this.tipoServico = s.getTipoServico();
    }
    
    // metodos getters
    
    public double getValorDeducao(){
        return this.valorDeducao;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public String getTipoServico(){
        return this.tipoServico;
    }
    
    // metodos setters
    
     public void setValorDeducao(double valor){
        this.valorDeducao = valor;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public void setTipoServico(String tipoServico){
        this.tipoServico = tipoServico;
    }
    
    // metodo toString
    
    public String toString(){
        StringBuilder sb =  new StringBuilder();
        sb.append(super.toString());
        sb.append("\n");
        sb.append("Valor da deducao: ").append(this.valorDeducao).append("\n");
        sb.append("Descrição do Serviço: ").append(this.descricao).append("\n");
        sb.append("Tipo de Serviço: ").append(this.tipoServico);
        return sb.toString();
    }
    
    // metodo clone
    
    public Servicos clone(){
        return new Servicos(this);
    }
}
