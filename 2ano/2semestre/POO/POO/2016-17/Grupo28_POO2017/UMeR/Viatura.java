import java.util.*;
import java.lang.*;
import static java.lang.Math.sqrt;
import java.util.Random;
import java.io.*;
/**
 * Escreva a descrição da classe Viaturas aqui.
 * 
 * @sofia carvalho
 * @version 1 
 */
public class Viatura implements Serializable
{
    // variáveis de instância
    private Map<Integer,Viagem> viagens;
    private double velMedKm;
    private double precoBaseKm;
    private double fiabilidade;
    private Espaco2D localizacao;
    private String matricula;
    
    public Viatura(){
        // inicializa variáveis de instância
        this.viagens = new HashMap<>();
        this.velMedKm = 0.0;
        this.precoBaseKm = 0.0;
        this.fiabilidade = 0.0;
        this.localizacao = new Espaco2D();
        this.matricula = "00-AA-00";
    }
    
    public Viatura(Viatura v){
        this.viagens = v.getViagens();
        this.velMedKm = v.getVelMedKm();
        this.precoBaseKm = v.getFiabilidade();
        this.localizacao = v.getLocalizacao();
        this.matricula = v.getMatricula();
    }
    
    public Viatura(Map<Integer,Viagem> umasViagens, double velMedKm, 
                   double precoBaseKm, double fiabilidade, Espaco2D localizacao, String matricula){
        this.viagens = new HashMap<>();
        for(Viagem v: umasViagens.values())
            this.viagens.put(v.getId(),v.clone());
        this.velMedKm = velMedKm;
        this.precoBaseKm = precoBaseKm;
        this.fiabilidade = fiabilidade;
        this.localizacao = localizacao;
        this.matricula = matricula;
    }
    
    public Map<Integer,Viagem> getViagens(){
        Map<Integer,Viagem> res = new HashMap<>();
        for(Viagem v: this.viagens.values())
            res.put(v.getId(),v.clone());
        return res;
    }
    
    public double getVelMedKm(){
        return this.velMedKm;
    }
    
    public double getPrecoBaseKm(){
        return this.precoBaseKm;
    }
    
    public double getFiabilidade(){
        Random gerador = new Random();
	    return this.fiabilidade = gerador.nextInt(3)+1;
	}
    
    public Espaco2D getLocalizacao(){
        return new Espaco2D(this.localizacao.getX(), this.localizacao.getY());
    }
    
    public String getMatricula(){
        return this.matricula;
    }
    
    public void setViagens(Map<Integer,Viagem> umasViagens){
        this.viagens = new HashMap<>();
        for(Viagem v: umasViagens.values())
            this.viagens.put(v.getId(),v.clone());
    }
    
    public void setVelMedKm(double velMedKm){
        this.velMedKm = velMedKm;
    }
    
    public void setPrecoBaseKm(double precoBaseKm){
        this.precoBaseKm = precoBaseKm;
    }
    
    public void setFiabilidade(double fiabilidade){
        this.fiabilidade = fiabilidade;
    }
    
    public void setLocalizacao(Espaco2D localizacao){
        this.localizacao.setX(localizacao.getX());
        this.localizacao.setY(localizacao.getY());
    }
    
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    
    public double calculardistancia(Espaco2D a, Espaco2D b){
        double dx= a.getX()-b.getX();
        double dy= a.getY()-b.getY();
        dx=dx*dx;
        dy=dy*dy;
        return sqrt(dx+dy);
    }
    
    public double custoEstimado(Viagem v, Cliente c) throws ClienteNaoTemViagemException{
        if (c.getViagens().containsValue(v)) return v.getCusto();
        else throw new ClienteNaoTemViagemException();
    }
    
    public double tempoReal(Viagem v){
        if ( (fiabilidade * v.getDuracao() - v.getDuracao()) > 0.25*v.getDuracao()) return velMedKm * v.getDistancia();
        else return v.getDuracao();
    }
    
    public void adicionaViagemViat(Viagem v){
       this.viagens.put(v.getId(),v.clone());
    }
    
    public Viatura clone(){
       return new Viatura(this);
    }
    
    public boolean equals(Object o) {
       if(o == this) {
            return true;
       }
       if(o == null || o.getClass() != this.getClass()) {
            return false;
       }
       Viatura v = (Viatura) o;
       return v.getViagens().equals(this.viagens) && v.getVelMedKm() == this.velMedKm && 
              v.getPrecoBaseKm() == this.precoBaseKm && v.getFiabilidade() == this.fiabilidade && 
              v.getLocalizacao().equals(this.localizacao) && v.getMatricula().equals(this.matricula);
    }
   
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Viagens: ");
        for(Viagem v: this.viagens.values())
            sb.append(v.toString()).append("\n");
        sb.append("Velocidade Média por Km: ").append(velMedKm).append("\n");
        sb.append("Preço Base por Km: ").append(precoBaseKm).append("\n");
        sb.append("Fiabilidade: ").append(fiabilidade).append("\n");
        sb.append("Localizacao: ").append(localizacao.toString());
        sb.append("Matrícula da viatura: ").append(matricula).append("\n");
        return sb.toString();
    }    
}
