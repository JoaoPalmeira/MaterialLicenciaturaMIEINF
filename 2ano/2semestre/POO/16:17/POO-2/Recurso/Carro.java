
public class Carro
{
    private String id;
    
    private Carro(Carro c2)
    {
        this.id=c2.getId();
    }
    
    public String getId()
    {
        return this.id;
    }
    
    public Carro clone()
    {
        return new Carro(this);
    }
}
