
/**
 * A classe Normal não acrescenta informação a nível de variáveis 
 * instância, mas implementa os métodos herdados de Empregado.
 * 
 * @author anr
 * @version 2014.05.07
 * @version 2015.05.11
 */
public class Normal extends Empregado {
    
  public Normal() {
    super();
  }
  
  public Normal(String cod, String nome, int dias) {
    super(cod,nome,dias);
  }
  
  public Normal(Normal n) {
    super(n);
  }
  
  
  
  public boolean equals(Object obj) {
      if(this == obj) return true; 
      if((obj == null) || (this.getClass() != obj.getClass())) return false;
      Normal e = (Normal) obj;
      return super.equals(e);
  } 
  
   public double salario() {
     return this.getDias()*getSalDia();  
   }
   
   public String toString() {
      return "Empregado Normal: " + this.getNome() + " - " + this.getCodigo() + " - " +
              this.salario() + " euros";
   }
  
   public Normal clone() {return new Normal(this);}
    
}
