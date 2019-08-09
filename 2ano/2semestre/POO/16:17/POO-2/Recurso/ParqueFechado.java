import java.util.Map;
import java.util.HashMap;
public class ParqueFechado implements AcessoParque
{
    private static int MAX_SIZE;
    
    private HashMap<String,Carro> carros;
    private int tamanho;
    
    public void entra(Carro v) throws ParqueCheioException
    {
        if(this.tamanho<this.MAX_SIZE) throw new ParqueCheioException();
        this.carros.put(v.getId(),v.clone());
        this.tamanho++;
    }
    
    public void sai(String numeroCarro) throws CarroNaoExisteException
    {
        if(!this.carros.containsKey(numeroCarro)) throw new CarroNaoExisteException();
        this.tamanho--;
        this.carros.remove(numeroCarro);
    }
}

