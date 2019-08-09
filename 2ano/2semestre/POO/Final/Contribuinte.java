import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;

/**
 * Classe dos contribuintes individuais
 *
 * @author 
 * @version (a version number or a date)
 */

public class Contribuinte extends Utilizador implements Serializable{
    // variáveis de instância
    private int nAgrFam;                                // numero de dependentes do agregrado familiar
    private int coefi;                                  // coeficiente fiscal para efeitos de deduçao fiscal
    private List<Integer> nFa;                          // numeros fiscais do agregrado familiar(nifs) 
    private Map<Integer,ActividadeEconomica> codAct;    // lista das actividades economicas para as quais os contribuintes tem deduçao
    
 
    public Contribuinte(){
        super();
        this.nAgrFam = -1;
        this.coefi = -1;
        this.nFa = new ArrayList<>();
        this.codAct = new HashMap<>();
    }
    
     public Contribuinte(int nif, String email, String nome, String morada, String pass,
                         int nAgrFam, int coef, List<Integer> numeroFiscais, Map<Integer,ActividadeEconomica> codiActividades) {
        super(nif,email,nome,morada,pass);
        this.nAgrFam = nAgrFam;
        this.coefi = coef;
        this.nFa = new ArrayList<>();
        for(Integer i : numeroFiscais){
            this.nFa.add(i);
        }
        this.codAct = new HashMap<>();
        for(ActividadeEconomica ae : codiActividades.values()){
            this.codAct.put(ae.getCodigo(),ae.clone());
        }  
    }
    
    public Contribuinte(Contribuinte c){
        super(c);
        this.nAgrFam = c.getNAgrFam();
        this.coefi = c.getCoef();
        this.nFa = c.getNfa(); 
        this.codAct = c.getCodAct();
    }
  
    // metodos getters
    
    public int getNAgrFam(){
        return this.nAgrFam;
    }
    
    public int getCoef(){
        return this.coefi;
    }
    
    public List<Integer> getNfa(){
        List<Integer> res = new ArrayList<>();
        for(Integer i: this.nFa){
            res.add(i);
        }
        return res;
    }
    
    public Map<Integer,ActividadeEconomica> getCodAct(){
        Map<Integer,ActividadeEconomica> res = new HashMap<>();
        for(ActividadeEconomica ae : this.codAct.values()){
            if(ae.getDeduzivel() == true){
                res.put(ae.getCodigo(),ae.clone());
           }
        }   
        return res;
    }
    
    // metodo setters
    
    public void setCodAct(Map<Integer,ActividadeEconomica> act){
        this.codAct = act;
    }
    
    // adiciona actividade economica
    
    public boolean adicionaActividade(ActividadeEconomica ae){
        boolean res;
        if(this.codAct.containsKey(ae.getCodigo())){
            res=true;
        }
        else{
            this.codAct.put(ae.getCodigo(),ae.clone());
            res=false;
        }
        return res;
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\n");
        sb.append("Numero do Agregrado: ").append(this.nAgrFam).append("\n");
        sb.append("Coeficiente Fiscal: ").append(this.coefi).append("\n");
        for(Integer i : this.nFa){
            sb.append("Numero Fiscal do Agregrado: \n");
            sb.append(i).append("\n");
        }
        for(ActividadeEconomica ae : this.codAct.values()){
            sb.append("Codigos das ActividadesEconomicas: \n");
            sb.append(ae.getCodigo()).append("\n");
        }
        return sb.toString();
    }
  
    // clone 
    
    public Contribuinte clone(){
        return new Contribuinte(this);
    }
}

