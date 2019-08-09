import java.util.*;

public class LLCandidato
{
    private int count;
    private Nodo cabeca;

    public LLCandidato()
    {
        cabeca = null;
        count = 0;
    }

    public int size(){
        return this.count;
    }
    
    public void add(Candidato c){
        if(cabeca != null){
            Nodo temp = new Nodo(null,c);
            Nodo atual = cabeca;
            
            while(atual.getNext() != null){
                atual = atual.getNext();
            }
            atual.setNext(temp);
            this.count++;
        }else{
            cabeca = new Nodo(cabeca);
            this.count++;
        }
    }
    
    public Candidato get(int i) throws CandidatoException{
        Nodo atual = null;
        Candidato aux= new Candidato();
        if(cabeca != null){
            atual = cabeca;
            int j;
            for(j=0;j<i;j++){
                if(atual.getNext() == null){
                    throw new CandidatoException();
                }
                atual = atual.getNext();
            }
            aux= atual.getCandidato();
        }
        return aux;
    }
    
    /*
    public boolean equals(Object o){
        
    }
    */
}
