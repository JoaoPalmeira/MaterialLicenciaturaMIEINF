
/**
 * Write a description of class Empregado here.
 * 
 * @author anr
 * @version 2013.05.06
 * @version 2014.05.07
 * @version 2015.05.11
 */
import java.io.Serializable;

public abstract class Empregado implements Serializable, Contribuinte
{

   // de classe
   private static double salDia = 50.00;
   public static double getSalDia() { return salDia; }
   public static void setSalDia(double nvSalDia) { 
     salDia = nvSalDia; 
   }
   
   // de instância
   private String NIF;
   public String getNIF()
   {
       return this.NIF;
   }
   private String codigo;
   private String nome;
   private int dias;
   
   // Construtores
   public Empregado() {
     this.codigo = "";
     this.nome = "";
     this.dias = 0;
   }
   
   public Empregado(String cod, String nom, int dias) {
       this.codigo = cod; this.nome = nom; this.dias = dias;
   }   
   public Empregado(Empregado emp) {
       this.codigo = emp.getCodigo(); this.nome = emp.getNome(); 
       this.dias = emp.getDias();
   }
   
   
   public String getNome() { return this.nome; }
   public String getCodigo() { return this.codigo; }
   public int getDias() { return this.dias; }
   
   public void setNome(String nome) {
       this.nome = nome;
    }
   
   public boolean equals(Object obj) {
      if(this == obj) return true; 
      if((obj == null) || (this.getClass() != obj.getClass())) return false;
      Empregado e = (Empregado) obj;
      return this.nome.equals(e.getNome()) && this.codigo.equals(e.getCodigo()) && this.dias == e.getDias();
   } 
  
   public abstract double salario();
   
   public abstract String toString();
   
   public abstract Empregado clone();
   
   
         
}
  