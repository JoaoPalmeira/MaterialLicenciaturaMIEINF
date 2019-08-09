
public class Lugar
{
    /* * Matricula do veiculo alocado */
    private String matricula ;
    /* * Nome do proprietario */
    private String nome ;
    /* * Tempo atribuido ao lugar , em minutos */
    private int minutos ;
    /* * Indica se lugar é permanente , ou de aluguer */
    private boolean permanente;
    
    
    
    protected Lugar clone() 
    {
        return new Lugar (this);
    }  
}
