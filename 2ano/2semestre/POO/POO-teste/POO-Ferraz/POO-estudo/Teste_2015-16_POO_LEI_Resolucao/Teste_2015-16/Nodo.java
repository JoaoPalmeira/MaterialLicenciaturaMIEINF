import java.util.*;

public class Nodo
{
    private Nodo prox;
    private Candidato c;
    
    
    public Nodo()
    {
       this.prox = null;
       this.c = null ;
    }
    
    public Nodo(Nodo prox,Candidato c){
        this.prox=prox;
        this.c=c;
    }
    
    public Nodo(Nodo a)
    {
       this.prox = a.getNext();
       this.c = a.getCandidato();
    }

    public Candidato getCandidato(){
        return this.c.clone();
    }
    
    public void setCandidato(Candidato c){
        this.c = c.clone();
    }
    
    public Nodo getNext(){
        return this.prox.clone();
    }
    
    public void setNext(Nodo prox){
        this.prox = prox.clone();
    }
    
    
    
    public boolean equals(Object o) {
        if(o==this) {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) {
            return false;
        }
        Nodo p = (Nodo) o;
        return p.getCandidato().equals(this.c) && p.getNext().equals(this.prox) ;
    }
  
  
    public Nodo clone(){
        return new Nodo(this);
    }
    
    public String toString(){
      StringBuilder sb  = new StringBuilder();  
      sb.append(c.toString());
      sb.append(prox.toString());
      return sb.toString();
      }
    
}
