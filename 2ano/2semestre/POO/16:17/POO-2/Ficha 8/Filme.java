import java.util.ArrayList;
import java.util.List;
public class Filme extends Entrada
{
    private String realizador;
    private List<String> atores;
    
    public Filme(Filme f2)
    {
        super(f2);
        this.realizador = f2.getRealizador();
        this.atores = f2.getAtores();
    }
    
    public List<String> getAtores()
    {
        ArrayList<String> res = new ArrayList<String>();
        for(String a: this.atores)
        {
            res.add(a);
        }
        return res;
    }
    
    public String getRealizador()
    {
        return this.realizador;
    }
    
    public Filme clone()
    {
        return new Filme(this);
    }
}
