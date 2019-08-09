
/**
 * Write a description of class Empregado here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Empregado implements java.io.Serializable
{
    // Variaveies de instancia
    private String codigo;
    private String nome;
    private int diasTrabalhoEfectivoMes;
    private static double salDia = 50.00;
    private String nif; // alteracao para a classe contribuinte
    
    // construtores
    public Empregado(){
        this.codigo = "";
        this.nome = "";
        this.diasTrabalhoEfectivoMes = 0;
        this.salDia = 0.00;
        this.nif = ""; // alteracao para a classe contribuinte
    }
    
    public Empregado(String cod, String n, int dias, double sal, String nf){
        this.codigo = cod;
        this.nome = n;
        this.diasTrabalhoEfectivoMes = dias;
        this.salDia = sal;
        this.nif = nf; // alteracao para a classe contribuinte
    }
    
    
    public Empregado(Empregado emp){
        this.codigo = emp.getCodigo();
        this.nome = emp.getNome();
        this.diasTrabalhoEfectivoMes = emp.getDiasTrabalhoEfectivoMes();
        this.salDia = emp.getSalDia();
        this.nif = emp.getNIF();
    }
    
    
    // metodos abstractos
    // métodos abstratos //

    /*
     * não possuem corpo, da mesma forma que
     * as assinaturas de método de uma interface
    */
   
   public abstract double salario();
   public abstract String toString();
   public abstract Empregado clone();
   
   // metodos
   
   public String getCodigo(){
       
       return this.codigo;
       
    }
    
   public String getNome(){
       
       return this.nome;
       
    }
   
   public int getDiasTrabalhoEfectivoMes(){
       
       return this.diasTrabalhoEfectivoMes;
    }
    
   public double getSalDia(){
       
       return this.salDia;
       
    }
    // alteracao para a classe contribuinte
    public String getNIF(){
        
        return this.nif;
    }
   
}
