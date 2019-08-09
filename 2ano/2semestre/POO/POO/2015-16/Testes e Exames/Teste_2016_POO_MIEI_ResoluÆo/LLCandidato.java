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
            Nodo temp = new Nodo(c);
            Nodo atual = cabeca;
            
            while(atual.getNext() != null){
                atual = atual.getNext();
            }
            atual.setNext(temp);
            this.count++;
        }else{
            cabeca = new Nodo(c);
            this.count++;
        }
    }
    
    public Candidato get(int i) throws CandidatoException{
        Nodo atual = null;
        if(cabeca != null){
            atual = cabeca;
            int j;
            for(j=0;j<i;j++){
                if(atual.getNext() == null){
                    throw new CandidatoException();
                }
                atual = atual.getNext();
            }
            return atual.getCandidato();
        }
        return null;
    }
    
    /*
    public boolean equals(Object o){
        
    }
    */
}
