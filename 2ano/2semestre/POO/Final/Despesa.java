import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;

/**
 * Write a description of class Despesa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Despesa implements Serializable
{
   // variaves de instancia criadas pelo grupo
   private int id;                                      // id da fatura
   private boolean validade;                            // se a despesa foi validada pelo contribuinte
    
   // variaveis de instancia pedidas no enunciado
   private int nifE;                                    // numero fiscal do emitente
   private String designacaoE;                          // designaçao do emitente 
   private LocalDate data_despesa;                      // data da despesa 
   private int nifC;                                    // numero fiscal do cliente
   private String designacaoD;                          // Descriçao da despesa
   private ActividadeEconomica naturezaDespesa;         // Atividade economica da despesa (ex:Saude) 
   private double valor_despesa;                        // motante da despesa
   
   public Despesa(){
       this.id = 0;
       this.validade = false;
       this.nifE = 0;
       this.designacaoE = " ";
       this.data_despesa = null;
       this.nifC = 0;
       this.designacaoD = " ";
       this.naturezaDespesa = new ActividadeEconomica();
       this.valor_despesa = 0.0;
    }
   
   public Despesa(Despesa d){
       this.id = d.getId();
       this.validade = d.getValidade();
       this.nifE = d.getNifE(); 
       this.designacaoE = d.getDesignacaoEmpresa(); 
       this.data_despesa = d.getDataDespesa(); 
       this.nifC = d.getNifC();
       this.designacaoD = d.getDesignacaoD(); 
       this.naturezaDespesa = d.getNaturezaDespesa(); 
       this.valor_despesa = d.getValorDespesa();
   }
   
   public Despesa(int id, boolean validade, int nifE, String designacaoE, LocalDate data_despesa, int nifC, 
                  String designacaoD, ActividadeEconomica natureza_despesa, double valor_despesa){
       this.validade = validade;
       this.id = id;              
       this.nifE = nifE;
       this.designacaoE = designacaoE;
       this.data_despesa = data_despesa;
       this.nifC = nifC;
       this.designacaoD = designacaoD;
       this.naturezaDespesa = natureza_despesa;
       this.valor_despesa = valor_despesa;
   }
   
   // getters
   
   public int getId(){
       return this.id;
   }
   
   public boolean getValidade(){
       return this.validade;
   }
   
   public int getNifE(){
       return this.nifE;
   }
   
   public String getDesignacaoEmpresa(){
       return this.designacaoE;
   }
   
   public LocalDate getDataDespesa(){
       return this.data_despesa;
   }
   
   public int getNifC(){
       return this.nifC;
   }
   
   public String getDesignacaoD(){
       return this.designacaoD;
    }
   
   public ActividadeEconomica getNaturezaDespesa(){
       return this.naturezaDespesa;
    }
   
   public double getValorDespesa(){
       return this.valor_despesa;
   }
   
   //setters
   
   public void setId(int id){
       this.id = id;
    }
    
    public void setValidade(boolean validade){
        this.validade = validade;
    }
    
    public void setDesignacao(String designacao){
        this.designacaoE = designacao;
    
    }
    
    public void setActividadeEconomica(ActividadeEconomica ae){
        this.naturezaDespesa = ae;
    }
   
   // auxiliares
   
   public Map<Integer,Despesa> getDespesasValidas(){
       Map<Integer,Despesa> res = new HashMap<>();
       for(Despesa d : res.values()){
           if(d.getValidade()==true){
               res.put(d.getNifC(),d.clone());
           }
       }
       return res;
   }
   
   // equals
   
   public boolean equals(Object o){
       if( this == o){
           return true;
       }
       
       if(this == null || o.getClass() != this.getClass()){
           return false;
        }
        
       Despesa d = (Despesa) o;
       
       return (this.nifE == d.getNifE() &&
               this.designacaoE.equals(d.getDesignacaoEmpresa()) &&
               this.nifC == d.getNifC() &&
               this.data_despesa == d.getDataDespesa() &&
               this.nifC == d.getNifC() &&
               this.naturezaDespesa.equals(d.getNaturezaDespesa()) &&
               this.valor_despesa == d.getValorDespesa());
                
   }
   
   
   // toString()
   
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nID: ").append(this.id).append("\n");
        sb.append("Nº Fiscal Emitente: ").append(this.nifE).append("\n");
        sb.append("Designacao Emitente: ").append(this.designacaoE).append("\n");
        sb.append("Data Despesa: ").append(this.data_despesa).append("\n");
        sb.append("Nº Fiscal Contribuinte: ").append(this.nifC).append("\n");
        sb.append("Designacao Despesa: ").append(this.designacaoD).append("\n");
        sb.append("Natureza Despesa: ").append(this.naturezaDespesa).append("\n");
        sb.append("Valor da Despesa: ").append(this.valor_despesa).append("\n");
        sb.append("Validade: ").append(this.validade).append("\n");
        sb.append("\n");
        return sb.toString();
   }
   
   // clone()
   
   public Despesa clone(){
       return new Despesa(this);
   }
}
