
/**
 * Write a description of class Contribuinte here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Contribuinte
{
    // instance variables
    // preciso do nif para o metodo getNIF() optei por fazer o contribuinte desta forma
    //pois têm de servir tanto para empresas como para empregados.
    private String nif;
    private String nome;
    private String morada;

    /**
     * Constructor for objects of class Contribuinte
     */
    public Contribuinte()
    {
       this.nif = "";
       this.nome = "";
       this.morada = ""; 
    }
    
    public Contribuinte(String NIF, String n, String mor)
    {
       this.nif = NIF;
       this.nome = n;
       this.morada = mor; 
    }
    
    public Contribuinte(Contribuinte cn)
    {
       this.nif = cn.getNIF();
       this.nome = cn.getNome();
       this.morada = cn.getMorada(); 
    }

   //metodos
   
   public String getNIF(){
       
       return this.nif;
    }
   public String getNome(){
       
       return this.nome;
    }
    
    public String getMorada(){
       
       return this.morada;
    }
    
    
}
