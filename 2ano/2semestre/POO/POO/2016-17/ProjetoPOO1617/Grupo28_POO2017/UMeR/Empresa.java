import java.util.*;
import java.io.*;
/**
 * Escreva a descrição da classe Empresa aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Empresa implements Serializable
{
    // variáveis de instância
    private String nome;
    private Map<String, Viatura> viaturas;
    private Map<String, Motorista> motoristas;

    /**
     * Construtor para objetos da classe Empresa
     * construtor vazio
     */
    public Empresa(){
        this.nome = "";
        this.viaturas = new HashMap<>();
        this.motoristas = new HashMap<>();
    }
    
    /**
     * construtor por parâmetros
     */
    public Empresa(String nome, Map<String, Viatura> viaturas, Map<String, Motorista> motoristas){
       this.nome = nome;
       this.viaturas = new HashMap<>();
       for (Viatura v : viaturas.values()){
           this.viaturas.put(v.getMatricula(), v.clone());
       }
       this.motoristas = new HashMap<>();
       for (Motorista m : motoristas.values()){
           this.motoristas.put(m.getEmail(), m.clone());
       }
    }
    
    /**
     * construtor por cópia
     */
    public Empresa(Empresa e){
        this.nome = e.getNome();
        this.viaturas = e.getViaturas();
        this.motoristas = e.getMotoristas();
    }
    
    /**
     * Métodos - getters 
     */
    public String getNome(){
        return this.nome;
    }
    
    public Map<String, Viatura> getViaturas(){
       Map<String, Viatura> res = new HashMap<>();
       for (Viatura v : this.viaturas.values()){
           res.put(v.getMatricula(), v.clone());
       }
       return res;
    }
    
    public Map<String, Motorista> getMotoristas(){
        Map<String, Motorista> res = new HashMap<>();
        for (Motorista m : this.motoristas.values()){
            res.put(m.getEmail(), m.clone());
        }
        return res;
    }
    
    /**
     * Métodos - setters 
     */
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setViaturas(Map<String, Viatura> viaturas){
       this.viaturas = new HashMap<>();
       for (Viatura v : viaturas.values()){
           this.viaturas.put(v.getMatricula(), v.clone());
       } 
    } 
    
    public void setMotorista(Map<String, Motorista> motoristas){
        this.motoristas = new HashMap<>();
        for (Motorista m : motoristas.values()){
            this.motoristas.put(m.getEmail(), m.clone());
        }
    }
    
    public void adicionaMotorista(Motorista m){
        this.motoristas.put(m.getEmail(),m.clone());
    }
    
    public void adicionaViatura(Viatura v){
        this.viaturas.put(v.getMatricula(),v.clone());
    }
    
    public void associaViatEmp(String matricula,String email){
        for(Motorista m: this.motoristas.values()){
            if(m.getEmail().equals(email)) m.setMatricula(matricula);
        }
    }
    
    public Empresa clone(){
        return new Empresa(this);
    }
    
    public boolean equals(Object o){
        if (o == null || o.getClass() != this.getClass()) return false;
        if (o == this) return true;
        Empresa e = (Empresa) o;
        return e.getNome().equals(this.nome) && e.getViaturas().equals(this.viaturas) && e.getMotoristas().equals(this.motoristas);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Viaturas: ");
        for (Viatura v : this.viaturas.values()){
            sb.append(v.toString()).append("\n");
        }
        sb.append("Motoristas: ");
        for(Motorista m: this.motoristas.values())
            sb.append(m.toString()).append("\n");
        return sb.toString();
    }
}
