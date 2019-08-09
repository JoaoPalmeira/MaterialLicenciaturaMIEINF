
/**
 * Write a description of class Ceo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ceo extends Empregado
{
    // instance variables
    private static int x = 3;

    /**
     * Constructor for objects of class Ceo
     */
    public Ceo()
    {
       super();
    }  
    
    public Ceo(String cod, String n, int dias, double sal){
        super(cod, n, dias, sal);
    }
    
    
    public Ceo(Ceo ceo){
        super(ceo);
    }

    
    
    //Metodos abstractos
   public double salario(){
       return this.x * this.getSalDia() * this.getDiasTrabalhoEfectivoMes();
       // um int é convertido automaticamente para double (autopromocao)
    }
    
   public String toString(){
       StringBuilder s = new StringBuilder();
       //s.append(super.toString());
       //s.append("Nível de preparação mecânica: " + this.preparacaoMecanica + "\n");
       return s.toString();
    }
    
    
   public Ceo clone(){
       
      return new Ceo(this);
    }
    
}
