
public abstract class Entrada
{
    private String id;
    private String titulo;
    private double tempoT;
    private boolean possui;
    private String comentario;
    
    public String getId()
    {
        return this.id;
    }
    
    public String getTitulo()
    {
        return this.titulo;
    }
    
    public double getTempoT()
    {
        return this.tempoT;
    }
    
    public boolean getPossui()
    {
        return this.possui;
    }
    
    public String getComentario()
    {
        return this.comentario;
    }
    
    public Entrada(Entrada e2)
    {
        this.id = e2.getId();
        this.titulo = e2.getTitulo();
        this.tempoT = e2.getTempoT();
        this.possui = e2.getPossui();
        this.comentario = e2.getComentario();
    }
    
    public abstract Entrada clone();
}
