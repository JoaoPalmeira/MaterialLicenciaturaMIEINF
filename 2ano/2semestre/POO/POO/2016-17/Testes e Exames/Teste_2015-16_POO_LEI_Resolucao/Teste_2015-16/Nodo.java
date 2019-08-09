import java.util.*;

public class Nodo
{
    private Nodo prox;
    private Candidato c;

    public Nodo(Candidato c)
    {
       prox = null;
       this.c = c;
    }

    public Candidato getCandidato(){
        return c;
    }
    
    public void setCandidato(Candidato c){
        this.c = c;
    }
    
    public Nodo getNext(){
        return prox;
    }
    
    public void setNext(Nodo prox){
        this.prox = prox;
    }
}
