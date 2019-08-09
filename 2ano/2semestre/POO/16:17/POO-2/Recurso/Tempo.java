public class Tempo
{
    private int minutos;
    private int segundos;
    
    public Tempo()
    {
        this.minutos=0;
        this.segundos=0;
    }
    
    public int getMinutos()
    {
        return this.minutos;
    }
    
    public int getSegundos()
    {
        return this.segundos;
    }
    
    public int getTempo()
    {
        return this.minutos*60+this.segundos;
    }
}