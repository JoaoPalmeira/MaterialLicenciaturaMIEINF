
/**
 * Write a description of class Gestor here.
 * 
 * @author anr
 * @version 2014.05.07
 * @version 2015.05.11
 */
public  class Gestor extends Empregado {
    // Vars de instância
   private double premio;  //factor multiplicativo do prémio

    // Construtores
    
   public Gestor() {
     super();
     this.premio = 0.0;
    } 
    
   public Gestor(String cod, String nom, int dias, double prem) {
       super(cod, nom, dias); 
       this.premio = prem;
   }   
   
   public Gestor(Gestor gest) {
       super(gest); 
       this.premio = gest.getPremio();
   }
   
   // Métodos de instância
   public double getPremio() { 
       return this.premio; 
   }  
   
   public void setPremio(double nPremio) {
      this.premio = nPremio;
   }
   
  
   public double salario() { 
     return this.getDias()*getSalDia()*this.premio; 
   }
  
   
   public String toString() {
      return "Gestor: " + this.getNome() + " - " + this.getCodigo() + " - " +
              this.salario() + " euros";
              
              
     //podiamos ter feito versão com StringBuilder!!         
   }
   
   public boolean equals(Object g) {
       
       if (this == g)
         return true;
       if ((g == null) || (this.getClass() != g.getClass()))
         return false;
       else  
         return this.premio == ((Gestor)g).getPremio() && super.equals(g);
   }

   public Gestor clone() { return new Gestor(this); }
   
    
    
}
