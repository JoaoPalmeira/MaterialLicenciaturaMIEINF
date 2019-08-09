import java.util.Date;
import java.io.Serializable;
public abstract class Utilizador implements Serializable
{
    private String email;
    private String nome;
    private String password;
    private String morada;
    private Date dataNasc;
    
    public Utilizador () {
        this.email = "n/a";
        this.nome = "n/a";
        this.password = "n/a";
        this.morada = "n/a";
        this.dataNasc = new Date ();
    }
    public Utilizador (String email,
                       String nome,
                       String password,
                       String morada,
                       Date dataNasc) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNasc = dataNasc;
    }
    public Utilizador (Utilizador c) {
        this.email = c.getEmail();
        this.nome = c.getNome();
        this.password = c.getPassword();
        this.morada = c.getMorada();
        this.dataNasc = c.getDataNasc();
    }
    public String getEmail() {return email;}
    public String getNome() {return nome;}
    public String getPassword() {return password;}
    public String getMorada() {return morada;}
    public Date getDataNasc() {return dataNasc;}
    public void setEmail(String email) {this.email = email;}
    public void setNome(String nome) {this.nome = nome;}
    public void setPassword(String password) {this.password = password;}
    public void setMorada(String morada) {this.morada = morada;}
    public void setDataNasc(Date dataNasc) {this.dataNasc = dataNasc;}
    public abstract Utilizador clone();
}