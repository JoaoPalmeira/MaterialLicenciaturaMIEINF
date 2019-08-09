import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
/**
 * Escreva a descrição da classe Motorista aqui.
 * 
 * @sofia carvalho 
 * @version 1 
 */
public class Motorista extends Utilizador
{
   // variáveis de instância
   private int grauCumprimento;
   private int classificacao;
   private double nKms;
   private boolean disponivel;
   private String matricula;

   //construtor vazio
   public Motorista(){
        super();
        this.grauCumprimento = 0;
        this.classificacao = 0;
        this.nKms = 0.0;
        this.disponivel = true;
        this.matricula = "";
   }
   
   //construtro por cópia 
   public Motorista(Motorista m){
        super(m);
        this.grauCumprimento = m.getGrauCumprimento();
        this.classificacao = m.getClassificacao();
        this.nKms = m.getNKms();
        this.disponivel = m.getDisponivel();
        this.matricula = m.getMatricula();
   }
    
   //construtor por parâmetros
   public Motorista(String email, String nome, String password, String morada, LocalDate dataNascimento,  Map<Integer,Viagem> umasViagens, 
                    int grauCumprimento, int classificacao, double nKms, boolean disponivel, String matricula){
        super(email,nome,password,morada,dataNascimento,umasViagens);
        this.grauCumprimento = grauCumprimento;
        this.classificacao = classificacao;
        this.nKms = nKms;
        this.disponivel = disponivel;
        this.matricula = matricula;
   }
   
   //getters
   public int getGrauCumprimento(){
       return this.grauCumprimento;
   }
   
   public int getClassificacao(){
       return this.classificacao;
   }
   
   public double getNKms(){
       return this.nKms;
   }
   
   public boolean getDisponivel(){
       return this.disponivel;
   }
   
   public String getMatricula(){
       return this.matricula;
   }
   
   //setters
   public void setGrauCumprimento(int grauCumprimento){
       this.grauCumprimento = grauCumprimento;
   }
      
   public void setClassificacao(int classificacao){
       this.classificacao = classificacao;
   }
   
   public void setNKms(double nKms){
       this.nKms = nKms;
   }
   
   public void setDisponivel(boolean disponivel){
       this.disponivel = disponivel;
   }
   
   public void setMatricula(String matricula){
       this.matricula = matricula;
   }
  
   public Motorista clone(){
       return new Motorista(this);
   }
   
   public boolean equals(Object o) {
       if(o == this) {
            return true;
       }
       if(o == null || o.getClass() != this.getClass()) {
            return false;
       }
       if(!super.equals(o)){
           return false;
       }
       else{
          Motorista m = (Motorista) o;
          return m.getGrauCumprimento() == this.grauCumprimento && 
                 m.getClassificacao() == this.classificacao && m.getNKms() == this.nKms && 
                 m.getDisponivel() == this.disponivel && m.getMatricula().equals(this.matricula);
       }
   }
   
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Grau de Cumprimento: ").append(grauCumprimento).append("\n");
        sb.append("Classificação: ").append(classificacao).append("\n");
        sb.append("Kms Realizados: ").append(nKms).append("\n");
        sb.append("Disponível: ").append(disponivel).append("\n");
        sb.append("Táxi :").append(matricula);
        return sb.toString();
   }
}
