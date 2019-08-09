
import java.util.TreeSet;
import java.util.Iterator;

public class EmpresaSet
{   
    private TreeSet<Empregado> empregados;
    //para ser registada na DGCI
    // alteracao para a classe contribuinte
    private String nif;
    
    
    
    public boolean existeEmpregado(String cod){
        
        boolean res = false;
        for (Empregado emp: this.empregados)
            if(emp.getCodigo() == cod)
                res = true;
                
        return res;
   }
   
   public Empregado getEmpregado(String cod){
      
       for (Empregado emp: this.empregados)
            if(emp.getCodigo() == cod)
                
       
       return emp;
    }
    
    public String getNIF(){
        
        return this.nif;
    }
   
   // void addEmpregado(Empregado e);
   // Iterator<Empregado> listaPorOrdemDecrescenteSalario(); 
}
