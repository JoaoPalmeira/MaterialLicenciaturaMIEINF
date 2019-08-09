import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;

/**
 * 
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public class Utilizador implements Serializable{
    // variáveis de instância   
    private int nif;
    private String email;
    private String nome;
    private String morada;
    private String pass;
    
    public Utilizador(){
        this.nif = 000000000;
        this.email = " ";
        this.nome = " ";
        this.morada = " ";
        this.pass = " ";
    }
    
    /**
     * construtor por parâmetros
     */
    
    public Utilizador(int nif, String email, String nome, String morada, String pass){
        this.nif = nif;
        this.email = email;
        this.nome =nome;
        this.morada = morada;
        this.pass = pass;
    } 
    
      /**
     * construtor por cópia
     */
    
    public Utilizador(Utilizador u){
        this.nif = u.getNif();
        this.email = u.getEmail();
        this.nome = u.getNome();
        this.morada = u.getMorada();
        this.pass = u.getPass();
    }
    
    /**
     * Métodos - getters 
     */
    
    public int getNif(){
        return this.nif;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getMorada(){
        return this.morada;
    }
    
    public String getPass(){
        return this.pass;
    }
    
    //metodos setters
    
    public void setNif(int nif){
        this.nif = nif;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setMorada(String morada){
        this.morada = morada;
    }
    
    public void setPass(String pass){
        this.pass = pass;
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("NIF: ").append(this.nif).append("\n");
        sb.append("Email: ").append(this.email).append("\n");
        sb.append("Nome: ").append(this.nome).append("\n");
        sb.append("Morada: ").append(this.morada).append("\n");
        sb.append("Password: ").append(this.pass);
        return sb.toString();
    }
    
    // clone
    
    public Utilizador clone(){
        return new Utilizador(this);
    }
}


