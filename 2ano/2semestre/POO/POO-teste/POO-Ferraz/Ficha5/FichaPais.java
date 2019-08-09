
/**
 * Write a description of class FichaPais here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FichaPais
{
  private String nome;
  private String continente;
  private int populacao;
  
  public FichaPais(){
      this.nome="n/a";
      this.continente="n/a";
      this.populacao=0;
}

public FichaPais(String nome, String continente, int populacao){
    this.nome=nome;
    this.continente=continente;
    this.populacao=populacao;
}

public FichaPais(FichaPais fp){
    this.nome=fp.getNome();
    this.continente=fp.getContinente();
    this.populacao=fp.getPopulacao();
}



public String getNome(){
    return this.nome;
}
public  String getContinente(){
    return this.continente;
}
public int getPopulacao(){
    return this.populacao;
}
public void setNome(String nome){
    this.nome=nome;
}
public void setContinente(String continente){
    this.continente=continente;
}
public void setPopulacao(int populacao){
    this.populacao=populacao;
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
            f.getPopulacao()==populacao;
    }

    public FichaPais clone() {
        return new FichaPais(this);
    }

    public String toString() {
        return nome+"->"+continente+"->"+populacao+".";
    }





}




