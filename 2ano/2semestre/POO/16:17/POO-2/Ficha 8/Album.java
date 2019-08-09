
public class Album extends Entrada
{
    private String artista;
    private int numeroM;
    
    public Album(Album a2)
    {
        super(a2);
        this.artista = a2.getArtista();
        this.numeroM = a2.getNumeroM();
    }
    
    public int getNumeroM()
    {
        return this.numeroM;
    }
    
    public String getArtista()
    {
        return this.artista;
    }
    
    public Album clone()
    {
        return new Album(this);
    }
}
