import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
/**
 * Escreva a descrição da classe Cliente aqui.
 * 
 * @sofia carvalho 
 * @version 1 
 */
public class Cliente extends Utilizador
{
    // variáveis de instância
    private Espaco2D localizacao;
    
    /**
     * Construtor para objetos da classe Cliente
     */
    //construtor vazio
    public Cliente()
    {
        super();
        this.localizacao = new Espaco2D();
    }
    
    //construtor por parâmetros
    public Cliente(String email, String nome, String password, String morada, LocalDate dataNascimento, Map<Integer,Viagem> viagens, Espaco2D localizacao){
        super(email, nome, password, morada, dataNascimento, viagens);
        this.localizacao = new Espaco2D();
        this.localizacao.setX(localizacao.getX());
        this.localizacao.setY(localizacao.getY());
    }
    
    //construtor por cópia
    public Cliente(Cliente c){
        super(c.getEmail(), c.getNome(), c.getPassword(), c.getMorada(), c.getDataNascimento(), c.getViagens());
        localizacao = c.getLocalizacao();
    }
    
    //getters e setters
    public Espaco2D getLocalizacao(){
        return new Espaco2D(localizacao.getX(), localizacao.getY());
    }
    
    public void setLocalizacao(Espaco2D localizacao){
        this.localizacao.setX(localizacao.getX());
        this.localizacao.setY(localizacao.getY());
    }
    
    public void darNota(Motorista m, int nota){
        int total = m.getViagens().size();
        m.setClassificacao((m.getClassificacao()*total + nota) / (total+1));
    }
    
    public Cliente clone() {
        return new Cliente(this);
    }
    
    public boolean equals(Object o){
        if (o == this) return true;
        if (o.getClass()!=this.getClass() || (o == null)) return false;
        if (!super.equals(o)) return false;
        else {
           Cliente c = (Cliente) o;
           return localizacao.equals(c.getLocalizacao());
        }
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\n");
        sb.append("Localização: " + localizacao + "\n");
        return sb.toString();
    }
}
