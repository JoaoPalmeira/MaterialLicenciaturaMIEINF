import java.util.*;
public abstract class ParqueComRecusados extends Parque implements IParque
{
    private HashMap<String,TreeSet<String>> recusas;

    public ParqueComRecusados()
    {
        recusas = new HashMap<>();
    }

    public ParqueComRecusados(ParqueComRecusados p){
        this.recusas = p.getRecusas();
    }
    
    public void entra(String cartao, String matricula) throws SemPermissaoException{
        try{
            super.entra(cartao,matricula);
        }catch (SemPermissaoException e){
            if(recusas.containsKey(matricula)){
                this.recusas.get(matricula).add(cartao);
            }else{
                TreeSet<String> aux = new TreeSet<>();
                aux.add(cartao);
                this.recusas.put(matricula,aux);
            }
            throw new SemPermissaoException(matricula);
        }
    }
    
    public boolean equals(Object o){
        if(o==this) return true;
        if((o==null)||(this.getClass()!=o.getClass())){
            return false;
        }
        ParqueComRecusados p = (ParqueComRecusados) o;
        return super.equals(p) && this.recusas.equals(p.getRecusas()); 
    }
    
    public HashMap<String,TreeSet<String>> getRecusas(){
        HashMap<String,TreeSet<String>> aux= new HashMap<>();
        return aux;
    }
}
