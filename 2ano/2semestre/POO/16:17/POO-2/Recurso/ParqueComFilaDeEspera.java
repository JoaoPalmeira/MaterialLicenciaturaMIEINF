import java.util.Collection;
public class ParqueComFilaDeEspera extends ParqueFechado implements AcessoParque
{
    private Collection<String> ElementosEmFila;
    
    public Collection<String> getElementosEmFila()
    {
        Collection res = null;
        for(String m : this.ElementosEmFila)
            res.add(m);
        return res; 
    }
    
    public void entra(Carro v) throws ParqueCheioException
    {
        try
        { 
            super.entra(v);
        }
        catch(ParqueCheioException e)
        {
            this.ElementosEmFila.add(v.getId());
        }
    }
}
