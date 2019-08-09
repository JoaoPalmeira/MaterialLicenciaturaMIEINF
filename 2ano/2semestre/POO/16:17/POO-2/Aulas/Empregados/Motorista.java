
/**
 * Write a description of class Motorista here.
 * 
 * @author anr
 * @version 20140.05.07
 * @version 2015.05.11
 */
public class Motorista extends Empregado {
  private static double valorKm = 0.5;
  
  public static double getValorKm() {
    return valorKm;
  }
  
  public static void setValorKm(double nValorKm) {
    valorKm = nValorKm;
  }
  
  private double nKms;
  
  
  public Motorista() {
    super();
    this.nKms = 0.0;
  }  
  
  public Motorista(String cod, String nome, int dias, double nKms) {
    super(cod,nome,dias);
    this.nKms = nKms;
  }

  public Motorista(Motorista m) {
    super(m);
    this.nKms = m.getNKms();
  }  
  
  public double getNKms() {
    return this.nKms;  
  }
  
  public void setNKms() {
    this.nKms = nKms;
  }
  
  public double salario() { 
     return this.getDias()*getSalDia() + getValorKm()*this.nKms; 
   }
   
   public String toString() {
      return "Motorista: " + this.getNome() + " - " + this.getCodigo() + " - " +
              this.salario() + " euros";
              
              
     //podiamos ter feito versão com StringBuilder!!         
   }
   
   public boolean equals(Object m) {
       
       if (this == m)
         return true;
       if ((m == null) || (this.getClass() != m.getClass()))
         return false;
       else  
         return this.nKms == ((Motorista)m).getNKms() && super.equals(m);
   }

   public Motorista clone() { return new Motorista(this); }
   
    
  
  
  
}  