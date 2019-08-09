public interface AcessoParque
{
    public void entra(Carro v) throws ParqueCheioException;
    public void sai(String numeroCarro) throws CarroNaoExisteException;
}