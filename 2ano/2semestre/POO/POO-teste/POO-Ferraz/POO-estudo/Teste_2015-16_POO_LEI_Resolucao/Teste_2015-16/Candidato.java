import java.util.*;

public class Candidato implements Comparable
{
    private String nome;
    private int idade;

    public Candidato(Candidato c)
    {
        this.nome = c.getNome();
        this.idade = c.getIdade();
    }
     public Candidato()
    {
        this.nome = null;
        this.idade = 0;
    }

    public String getNome(){
        return this.nome;
    }
    
    public int getIdade(){
        return this.idade;
    }
    
    public int compareTo(Candidato c) {
        return this.nome.compareTo(c.getNome());
    }
    
    public Candidato clone(){
        return new Candidato(this);
    }
}
