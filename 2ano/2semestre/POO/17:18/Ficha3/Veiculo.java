import java.util.*;
import java.time.LocalDate;
/**
 * Escreva a descrição da classe Veiculo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Veiculo
{
    String marca;
    String modelo;
    int ano;
    double consumo;
    int kmsTot;
    double mediaInicio;
    int kmsUltPer;
    double mediaUltPer;
    int regeneracao;
    boolean estado;
    
    public Veiculo(){
        this.marca="";
        this.modelo="";
        this.ano=0;
        this.consumo=0.0;
        this.kmsTot=0;
        this.mediaInicio=0.0;
        this.kmsUltPer=0;
        this.mediaUltPer=0.0;
        this.regeneracao=0;
        this.estado=false;
    }
    public Veiculo(marca m, modelo s, ano a, consumo b, kmsTot c, mediaInicio d, kmsUltPer e, mediaUltPer f, regeneracao g, estado h){
        this.marca=m;
        this.modelo=s;
        this.ano=a;
        this.consumo=b;
        this.kmsTot=c;
        this.mediaInicio=d;
        this.kmsUltPer=e;
        this.mediaUltPer=f;
        this.regeneracao=g;
        this.estado=h;
    }
    public Veiculo(Veiculo jaguar){
        this.marca=jaguar.getMarca();
        this.modelo=jaguar.getModelo();
        this.ano=jaguar.getAno();
        this.consumo=jaguar.getConsumo();
        this.kmsTot=jaguar.getKmsTot();
        this.mediaInicio=jaguar.getMediaInicio();
        this.kmsUltPer=jaguar.getKmsUltPer();
        this.mediaUltPer=jaguar.getMediaUltPer();
        this.regeneracao=jaguar.getRegeneracao();
        this.estado=jaguar.getEstado();
    }
    
    public int getMarca () {
        return this.marca;
    }
    public int getModelo () {
        return this.modelo;
    }
    public int getAno () {
        return this.ano;
    }
    public int getConsumo () {
        return this.consumo;
    }
    public int getKmsTot () {
        return this.kmsTot;
    }
    public int getMediaInicio () {
        return this.mediaInicio;
    }
    public int getKmsUltPer () {
        return this.kmsUltPer;
    }
    public int getMediaUltPer () {
        return this.mediaUltPer;
    }
    public int getRegeneracao () {
        return this.regeneracao;
    }
    public int getEstado(){
        return this.estado;
    }
    
    public void ligaCarro(){
       this.estado = true;
       //reset ultima viagem
       this.kmsUltPer=0;
       this.mediaUltPer=0;
    }
    public void desligaCarro(){
       this.estado = false;
    }
    void avancaCarro(double metros, double velocidade){
       velocidade = velocidade + (metros * 0.01);
       this.kmsTot += metros;
       this.kmsUltPer += metros;
    }
    
    
    
    
    
    
    public boolean equals(Object o){
       if(o == this){
           return true;
       }

       if(o == null ||  this.getClass() != o.getClass()){
           return false;
       }

       Veiculo v = (Veiculo) o;

       return(v.getMarca().equals(this.marca) &&
              v.getModelo().equals(this.modelo) &&
              v.getAno() ==  this.ano &&
              v.getConsumo() == this.consumo &&
              v.getKmsTot() == this.kmsTot &&
              v.getMediaInicio() == this.mediaInicio &&
              v.getKmsUltPer() == this.kmsUltPer &&
              v.getMediaUltPer() == this.mediaUltPer &&
              v.getRegeneracao() == this.regeneracao);
    }
    
    public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append("Marca: ").append(this.marca).append("\n");
       sb.append("Modelo: ").append(this.modelo).append("\n");
       sb.append("Ano de Contruçao: ").append(this.ano).append("\n");
       sb.append("Consumo 100km: ").append(this.consumo).append("\n");
       sb.append("Kms Total: ").append(this.kmsTot).append("\n");
       sb.append("Consumo Final: ").append(this.mediaInicio).append("\n");
       sb.append("Kms UltimoPercurso: ").append(this.kmsUltPer).append("\n");
       sb.append("Consumo UltimoPercurso: ").append(this.consUltPer).append("\n");
       sb.append("Fator de Regeneracao: ").append(this.regeneracao).append("\n");
       return (sb.toString());
    }
    public Veiculo clone(){
        return new Veiulo(this);
    }
}
