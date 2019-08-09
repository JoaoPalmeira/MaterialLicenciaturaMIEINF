
/**
 * Escreva a descrição da classe Lugar aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Lugar
{
    private String matricula;
    private String nome;
    private int minutos;
    private boolean permanente;
    
    public Lugar(String matricula, String nome, int minutos, boolean permanente){
        this.matricula = matricula;
        this.nome = nome;
        this.minutos = minutos;
        this.permanente = permanente;
    }
    
    public Lugar(){
        this.matricula = "00-00-AA";
        this.nome = "";
        this.minutos = 0;
        this.permanente = false;
    }
    
    public Lugar(Lugar l){
        this.matricula = l.getMatricula();
        this.nome = l.getNome();
        this.minutos = l.getMinutos();
        this.permanente = l.getPermanente();
    }
    
    public String getMatricula(){
        return this.matricula;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public int getMinutos(){
        return this.minutos;
    }
    
    public boolean getPermanente(){
        return this.permanente;
    }
    
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setMinutos(int minutos){
        this.minutos = minutos;
    }
    
    public void setPermanente(boolean permanente){
        this.permanente = permanente;
    }
    
    public Lugar clone(){
        return new Lugar(this);
    }
    
    public String toString(){
        return matricula+" "+nome+" "+minutos+" "+permanente;
    }
    
    public boolean equals(Object o){
        if(o==this) {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) {
            return false;
        }
        Lugar l = (Lugar) o;
        return l.getMatricula().equals(matricula) && l.getNome().equals(nome) && 
               l.getMinutos() == minutos && l.getPermanente() == permanente;
    }
    
    public int compareTo(Lugar l){
        if(this.minutos<l.getMinutos()) return -1;
        else if(this.minutos==l.getMinutos()) return 0;
        else return 1;
    }
}
