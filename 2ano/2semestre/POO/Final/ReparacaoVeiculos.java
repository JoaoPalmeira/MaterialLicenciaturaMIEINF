import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;

/**
 * Write a description of class ReparacaoVeiculos here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ReparacaoVeiculos extends ActividadeEconomica implements Serializable
{
    private double valorDeducao;
    private String descricao;
    private String tipoVeiculo;         // moto, carro, camiao, etc
    
    public ReparacaoVeiculos(){
        super();
        this.valorDeducao = 0.0;
        this.descricao = " ";
        this.tipoVeiculo = " ";
    }
    
    public ReparacaoVeiculos(boolean deducao,int codigo,double valorDeducao, String descricao, String tipoVeiculo){
        super(deducao,codigo);
        this.valorDeducao = valorDeducao;
        this.descricao = descricao;
        this.tipoVeiculo = tipoVeiculo;
    }
    
    public ReparacaoVeiculos(ReparacaoVeiculos rv){
        super(rv);
        this.valorDeducao = rv.getValorDeducao();
        this.descricao = rv.getDescricao();
        this.tipoVeiculo = rv.getTipoVeiculo();
    }
    
    // metodos getters
    
    public double getValorDeducao(){
        return this.valorDeducao;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public String getTipoVeiculo(){
        return this.tipoVeiculo;
    }
    
    // metodos setters
    
     public void setValorDeducao(double valor){
        this.valorDeducao = valor;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public void setTipoVeiculo(String tipoVeiculo){
        this.tipoVeiculo = tipoVeiculo;
    }
    
    // metodo toString
    
    public String toString(){
        StringBuilder sb =  new StringBuilder();
        sb.append(super.toString());
        sb.append("\n");
        sb.append("Valor da deducao: ").append(this.valorDeducao).append("\n");
        sb.append("Descrição do Serviço: ").append(this.descricao).append("\n");
        sb.append("Tipo de Veiculo: ").append(this.tipoVeiculo);
        return sb.toString();
    }
    
    // metodo clone
    
    public ReparacaoVeiculos clone(){
        return new ReparacaoVeiculos(this);
    }
}
