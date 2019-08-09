
/**
 * Write a description of class Lugar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lugar
{
  private String matricula;
  private String nome;
  private int minutos;
  private boolean permanente;
  
  public Lugar(String matricula,String nome,int minutos,boolean permanente){
      this.matricula=matricula;
      this.nome=nome;
      this.minutos=minutos;
      this.permanente=permanente;
  }
  
  public Lugar(){
      this.matricula="n/a";
      this.nome="n/a";
      this.minutos=0;
      this.permanente= false;
  }
  
  public Lugar(Lugar l){
      this.matricula=l.getMatricula();
      this.nome=l.getNome();
      this.minutos=l.getMinutos();
      this.permanente=l.getPermanente();
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
        this.matricula=matricula;
    }
    
    public void setNome(String nome){
       this.nome=nome;
    }
    
    public void setMinutos(int minutos){
        this.minutos=minutos;
    }
    
    public void setPermanente(boolean permanente){
        this.permanente=permanente;
    }
    
    public Lugar clone(){
        return new Lugar(this);
    }
    
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if((o==null)|| (this.getClass()!= o.getClass())){
        return false;
        }
        
        Lugar l= (Lugar) o;
        return(this.matricula.equals(l.getMatricula()) && this.nome.equals(l.getNome()) && this.minutos==l.getMinutos() && this.permanente==l.getPermanente());
    }
    
    public String toString(){
        StringBuffer sb= new StringBuffer();
        sb.append("Matricula ="+this.getMatricula());
        sb.append("Nome ="+this.getNome());
        sb.append("Minutos ="+this.getMinutos());
        sb.append("Pernamente ="+this.getPermanente());
        return sb.toString();
    }
  
      
    
    
}