
/**
 * Write a description of class Motorista here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Motorista extends Empregado
{
    // instance variables - replace the example below with your own
    private static double valKm = 10.00 ;
    private double kmPercorridos;

   /**
     * Constructor for objects of class Motorista
     */
    public Motorista()
    {
       super();
    }  
    
    public Motorista(String cod, String n, int dias, double sal,double kmPer){
        super(cod, n, dias, sal);
        this.kmPercorridos = kmPer;
    }
    
    
    public Motorista(Motorista motorista){
        super(motorista);
    }
    //metodos
    
    public double getKmPercorridos(){
        
        return this.kmPercorridos;
    }
    
     //Metodos abstractos
   public double salario(){
      return (this.valKm * this.getKmPercorridos()) +  (this.getSalDia() * this.getDiasTrabalhoEfectivoMes());
       // um int é convertido automaticamente para double (autopromocao)
    }
    
   public String toString(){
       StringBuilder s = new StringBuilder();
       //s.append(super.toString());
       //s.append("Nível de preparação mecânica: " + this.preparacaoMecanica + "\n");
       return s.toString();
    }
    
     public Motorista clone(){
       
      return new Motorista(this);
    }
}
