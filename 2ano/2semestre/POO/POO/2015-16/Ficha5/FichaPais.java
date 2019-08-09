
/**
 * Escreva a descrição da classe FichaPais aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class FichaPais
{
    private String nome;
    private String continente;
    private double populacao;
    
    public FichaPais(String nome, String continente, double populacao) {
        this.nome = nome;
        this.continente = continente;
        this.populacao = populacao;
    }

    public FichaPais() {
        this.nome = "";
        this.continente = "";
        this.populacao = 0.0;
    }

    public FichaPais(FichaPais f) {
        this.nome = f.getNome();
        this.continente = f.getContinente();
        this.populacao = f.getPopulacao();
    }

    public String getNome() {
        return this.nome;
    }
    
    public String getContinente() {
        return this.continente;
    }
    
    public double getPopulacao() {
        return this.populacao;
    }
        
    public void setNome(String nome) {
        this.nome = nome;
    }
    
     public void setContinente(String continente) {
        this.continente = continente;
    }
    
     public void setPopulacao(double populacao) {
        this.populacao = populacao;
    }

    public boolean equals(Object o) {
        if(o==this) {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) {
            return false;
        }
        FichaPais f = (FichaPais) o;
        return f.getNome().equals(nome) && f.getContinente().equals(continente) && 
            f.getPopulacao() == populacao;
    }

    public FichaPais clone() {
        return new FichaPais(this);
    }

    public String toString() {
        return nome + "," + continente + "," + populacao + ".";
    }

}
