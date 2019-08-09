import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;
/**
 * Escreva a descrição da classe Utilizador aqui.
 * 
 * @sofia carvalho 
 * @version 1 
 */
public class Utilizador implements Serializable
{
   // variáveis de instância
   private String email;
   private String nome;
   private String password;
   private String morada;
   private LocalDate dataNascimento;
   private Map<Integer,Viagem> viagens;
   
   public Utilizador(){
        // inicializa variáveis de instância
        this.email = "";
        this.nome = "";
        this.password = "";
        this.morada = "";
        this.dataNascimento = LocalDate.of(0,1,1);
        this.viagens = new HashMap<>();
   }
   
   public Utilizador(Utilizador u){
       this.email = u.getEmail();
       this.nome = u.getNome();
       this.password = u.getPassword();
       this.morada = u.getMorada();
       this.dataNascimento = u.getDataNascimento();
       this.viagens = u.getViagens();
   }
   
   public Utilizador(String email, String nome, String password, String morada, LocalDate dataNascimento, Map<Integer,Viagem> viagens){
       this.email = email;
       this.nome = nome;
       this.password = password;
       this.morada = morada;
       this.dataNascimento = dataNascimento;
       this.viagens = new HashMap<>();
       for(Viagem v : viagens.values()){
           this.viagens.put(v.getId(),v.clone());
       }
   }
   
   public String getEmail(){
       return this.email;
   }
   
   public String getNome(){
       return this.nome;
   }
   
   public String getPassword(){
       return this.password;
   }
   
   public String getMorada(){
       return this.morada;
   }
   
   public LocalDate getDataNascimento(){
       return this.dataNascimento;
   }
   
   public Map<Integer, Viagem> getViagens(){
        Map<Integer, Viagem> res = new HashMap<>();
        Iterator<Map.Entry<Integer,Viagem>> it = this.viagens.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Integer, Viagem> r = it.next();
            res.put(r.getKey(),r.getValue().clone());
        }
        return res;
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
   
   public void setPassword(String password){
       this.password = password;
   }
   
   public void setDataNascimento(LocalDate dataNascimento){
       this.dataNascimento = dataNascimento;
   }
   
   public void setViagens(Map<Integer,Viagem> viagens){
        this.viagens = new HashMap<>();
        for(Viagem v : viagens.values()){
            this.viagens.put(v.getId(),v.clone());
        }
   } 
   
   public void adicionaViagem(Viagem v){
       this.viagens.put(v.getId(),v.clone());
   }
   
   public Utilizador clone(){
       return new Utilizador(this);
   }
   
   public boolean equals(Object o) {
       if(o == this) {
            return true;
       }
       if(o == null || o.getClass() != this.getClass()) {
            return false;
       }
       Utilizador u = (Utilizador) o;
       return u.getEmail().equals(this.email) && u.getNome().equals(this.nome) && 
              u.getMorada().equals(this.morada) && u.getPassword().equals(this.password) && 
              u.getDataNascimento().equals(this.dataNascimento) && 
              u.getViagens().equals(this.viagens);
   }
   
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ").append(email).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Morada: ").append(morada).append("\n");
        sb.append("Password: ").append(password).append("\n");
        sb.append("Data de Nascimento: ").append(dataNascimento).append("\n");
        sb.append("Viagens: ");
        for(Viagem v: this.viagens.values())
            sb.append(v.toString()).append("\n");
        return sb.toString();
   }
}
