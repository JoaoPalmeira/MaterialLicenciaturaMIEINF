public class SMS extends Mensagem
{
    private static int MAX_SIZE = 160;
    private long numberId;
    private int totalParts;
    // um SMS com mais caracteres que MAX_SIZE e partido
    // em varios de ate esse tamanho e custa o valor dos varios
    // SMS de tamanho normal
    private int number;
    
    public int getTotalParts()
    {
        return this.totalParts;
    }
}