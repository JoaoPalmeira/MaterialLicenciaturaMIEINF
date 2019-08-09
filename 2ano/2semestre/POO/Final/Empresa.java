import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;

/**
 *
 * @author rafaelsilva
 */
public class Empresa extends Utilizador implements Serializable{
    // variáveis de instância  
    private String designacao;                                // designacao da funcao da empresa em questao
    private String localizacao;                               // interior / litoral (preveligiar interior)
    private int factDeducao;                                  // fator de deduçao de uma empresa
    private Map<Integer,ActividadeEconomica> activEcono;      // lista de actividade economicas para que a empresa deduz
    
    public Empresa(){
        super();
        this.designacao = " ";
        this.localizacao = " ";
        this.factDeducao = 0;
        this.activEcono = new HashMap();
    }
    

    public Empresa(int nif, String email, String nome, String morada, String pass,
                  String designacao, String localizacao, int factDeducao, Map<Integer,ActividadeEconomica> actEcono){
        super(nif,email,nome,morada,pass);
        this.designacao = designacao;
        this.localizacao = localizacao;
        this.factDeducao = factDeducao;
        this.activEcono = new HashMap<>();
        for(ActividadeEconomica ae : actEcono.values()){
            this.activEcono.put(ae.getCodigo(),ae.clone());
        }
    }
    
    public Empresa(Empresa e){
        super(e);
        this.designacao = e.getDesignacao();
        this.localizacao = e.getLocalizacao();
        this.factDeducao = e.getFactDeducao();
        this.activEcono = e.getActivEcono();
    }
        
    public String getDesignacao(){
        return this.designacao;
    }
    
    public String getLocalizacao(){
        return this.localizacao;
    }
    
    public int getFactDeducao(){
        return this.factDeducao;
    }
    
    public void setDesignacao(String designacao){
        this.designacao = designacao;
    }
    
    public Map<Integer,ActividadeEconomica> getActivEcono(){
        Map<Integer,ActividadeEconomica> res = new HashMap <>();
        for(ActividadeEconomica ae: this.activEcono.values()){
            res.put(ae.getCodigo(),ae.clone());
        }
        return res;
    }
    
    // adicionais
    
    public boolean adicionaActividade(ActividadeEconomica ae){
        boolean res;
        if(this.activEcono.containsKey(ae.getCodigo())){
            res=true;
        }
        else{
            this.activEcono.put(ae.getCodigo(),ae.clone());
            res=false;
        }
        return res;
    }
    
    // equals
    
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(this == null || o.getClass() != getClass()){
            return false;
        }
        Empresa e = (Empresa) o;
        return (this.designacao.equals(e.getDesignacao()) &&
                this.localizacao.equals(e.getLocalizacao()) &&
                this.factDeducao == e.getFactDeducao() &&
                this.activEcono == e.getActivEcono());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\n");
        sb.append("Designacao: ").append(this.designacao).append("\n");
        sb.append("Localizacao: ").append(this.localizacao).append("\n");
        sb.append("Fator de deducao: ").append(this.factDeducao).append("\n");
        for(ActividadeEconomica ae : this.activEcono.values()){
            sb.append(ae.toString()).append("\n");
        }
        return sb.toString();
    }
    
    // clone
    
    public Empresa clone(){
        return new Empresa(this);
    }
}
