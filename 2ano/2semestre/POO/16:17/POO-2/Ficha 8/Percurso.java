import java.util.Stack;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
public class Percurso
{
    private String nomePercurso;
    // localidades do percurso já visitadas
    private Stack<String> locsVisitadas;
    // localidades ainda por visitar
    private Stack<String> locsPorVisitar;
    
    public Percurso(Collection<String> localidades)
    {
        this.nomePercurso="";
        this.locsVisitadas=new Stack<String>();
        this.locsPorVisitar=new Stack<String>();
        for(String p: localidades)
        {
            this.locsPorVisitar.push(p);
        }
    }
    
    public String locAVisitar() throws Exception
    {
        if(this.locsPorVisitar.empty()) throw new Exception("Stack Vazia");
        else return this.locsPorVisitar.peek();
    }
    
    public void proxLoc() throws Exception
    {
        if(this.locsPorVisitar.empty()) throw new Exception("Stack Vazia");
        else
        {
            this.locsVisitadas.push(this.locsPorVisitar.peek());
            this.locsPorVisitar.pop();
        }
    }
    
    public void removeLocalidade(String loc) throws Exception
    {
        ArrayList res = new ArrayList<String>();
        for(String p: this.locsPorVisitar)
        {
            res.add(p);
        }
        res.remove(loc);
        for(String p: this.locsPorVisitar)
        {
            this.locsVisitadas.push(p);
        }
    }
}