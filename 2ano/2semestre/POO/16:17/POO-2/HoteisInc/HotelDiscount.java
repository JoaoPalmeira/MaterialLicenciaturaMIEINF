
public class HotelDiscount extends Hotel
{
    private float ocupacao;
    
    public HotelDiscount()
    {
        super();
        ocupacao = 0;
    }
    
    public HotelDiscount(HotelDiscount h)
    {
        super(h);
        this.ocupacao = h.getOcupacao();
    }
    
    public float getOcupacao()
    {
        return this.ocupacao;
    }
    
    public HotelDiscount(String codigo, String nome, String localidade, double precoQuarto, float ocupacao, int nquartos)
    {
        super(codigo, nome, localidade, precoQuarto, nquartos);
        this.ocupacao = ocupacao;
    }
    
    public double precoQuarto() {
        return ((super.getPrecoQuarto()/2)*this.ocupacao);
    }
    
    public HotelDiscount clone() 
    {
        return new HotelDiscount(this);
    }
}
