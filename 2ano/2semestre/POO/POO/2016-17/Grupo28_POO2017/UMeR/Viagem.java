import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;
/**
 * Escreva a descrição da classe Viagem aqui.
 * 
 * @sofia carvalho
 * @version 1
 */
public class Viagem implements Serializable
{
   // variáveis de instância
   private double custo;
   private double duracao; //tempo estimado
   private double distancia;
   private int id;
   private Espaco2D destino;
   private LocalDate data;
   
   // Construtores
   //vazio
   public Viagem(){ 
       custo = 0.0;
       duracao = 0.0;
       distancia = 0.0;
       id = 0;
       destino = new Espaco2D();
       data = LocalDate.of(0,1,1);
   }
   
   //por parâmetros
   public Viagem(double custo, double duracao, double distancia, int id, Espaco2D destino, LocalDate data){
       this.custo = custo;
       this.duracao = duracao;
       this.distancia = distancia;
       this.id = id;
       this.destino = new Espaco2D();
       this.destino.setX(destino.getX());
       this.destino.setY(destino.getY()); 
       this.data = data;
   }
   
   //por cópia
   public Viagem(Viagem viagem){
       this.custo = viagem.getCusto();
       this.duracao = viagem.getDuracao();
       this.distancia = viagem.getDistancia();
       this.id = viagem.getId();
       this.destino = viagem.getDestino(); 
       this.data = viagem.getData();
   }
   
   //Métodos de instância
   //getters e setters
   public double getCusto(){
       return custo;
   }
   
   public double getDuracao(){ 
       return duracao;
   }
   
   public double getDistancia(){
       return distancia;
   }
   
   public int getId(){
       return id;
   }
   
   public Espaco2D getDestino(){
       return destino;
   }
   
   public LocalDate getData(){
       return data;
   }
   
   public void setCusto(double Custo){
       custo = Custo;
   }
   
   public void setDuracao(double Duracao){
        duracao = Duracao;
    }
    
   public void setDistancia(double Distancia){
        distancia = Distancia;
   }
   
   public void setId(int id){
       this.id = id;
   }
   
   public void setDestino(Espaco2D destino){
       this.destino = new Espaco2D();
       this.destino.setX(destino.getX());
       this.destino.setY(destino.getY());
   }
   
   public void setData(LocalDate data){
       this.data = data;
   }
    
    public Viagem clone(){
       return new Viagem(this);
   }
   
   public boolean equals(Object o){
       if(o==this){
           return true;
        }
       if(o==null || o.getClass()!=this.getClass()){
            return false;
       }
       Viagem v = (Viagem) o;
       return v.getCusto() == this.custo && v.getDuracao() == this.duracao && v.getDistancia() == this.distancia && v.getId() == this.id && this.destino.equals(v.getDestino()) 
                                && this.data.equals(v.getData());
   }
   
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id da Viagem: ").append(id).append("\n");
        sb.append("Custo: ").append(custo).append("\n");
        sb.append("Duracão: ").append(duracao).append("\n");
        sb.append("Distância: ").append(distancia).append("\n");
        sb.append("Destino: " + destino + "\n");
        sb.append("Data: " + data + "\n");
        return sb.toString();
   }         
}        
