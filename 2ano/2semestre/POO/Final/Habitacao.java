import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;

/**
 * Write a description of class Habitacao here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Habitacao extends ActividadeEconomica implements Serializable
{
    private double valorDeducao;
    private String descricao;
    private String tipoHabitacao;       // apartamento, hotel, casa, etc
    
    public Habitacao(){
        super();
        this.valorDeducao = 0.0;
        this.descricao = " ";
        this.tipoHabitacao = " ";
    }
    
    public Habitacao(boolean deducao, int codigo,double valorDeducao, String descricao, String tipoHabitacao){
        super(deducao,codigo);
        this.valorDeducao = valorDeducao;
        this.descricao = descricao;
        this.tipoHabitacao = tipoHabitacao;
    }
    
    public Habitacao(Habitacao h){
        super(h);
        this.valorDeducao = h.getValorDeducao();
        this.descricao = h.getDescricao();
        this.tipoHabitacao = h.getTipoHabitacao();
    }
    
    // metodos getters
    
    public double getValorDeducao(){
        return this.valorDeducao;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public String getTipoHabitacao(){
        return this.tipoHabitacao;
    }
    
    // metodo setters
    
     public void setValorDeducao(double valor){
        this.valorDeducao = valor;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public void setTipoHabitacao(String tipoHabitacao){
        this.tipoHabitacao = tipoHabitacao;
    }
    
    // metodo toString
    
    public String toString(){
        StringBuilder sb =  new StringBuilder();
        sb.append(super.toString());
        sb.append("\n");
        sb.append("Valor da deducao: ").append(this.valorDeducao).append("\n");
        sb.append("Descrição do Serviço: ").append(this.descricao).append("\n");
        sb.append("Tipo de Habitção: ").append(this.tipoHabitacao);
        return sb.toString();
    }
    
    // metodo clone
    
    public Habitacao clone(){
        return new Habitacao(this);
    }
}
