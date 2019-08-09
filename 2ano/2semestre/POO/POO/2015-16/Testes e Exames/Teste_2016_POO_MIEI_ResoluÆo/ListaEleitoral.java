import java.util.*;

public class ListaEleitoral
{
    
    private String partidoPolitico;
    private TreeSet<Candidato> eleitos;
    private ArrayList<Candidato> porEleger;
    
    ////////////////////////////////////////////////////// PARTE I //////////////////////////////////////////////
    
    public ListaEleitoral(String partido, ArrayList<Candidato> candidatos)
    {
        this.partidoPolitico = partido;
        this.porEleger = new ArrayList<>();
        for(Candidato c : candidatos){
            this.porEleger.add(c.clone());
        }
    }
    
    public Candidato aEleger() throws NoMoreCandidatosException{
        Candidato c = this.porEleger.get(0);
        if(c!=null){
            return c.clone();
        }else throw new NoMoreCandidatosException();
    }
    
    public void elege(){
        Candidato c = null;
        try{
            c = aEleger();
            this.eleitos.add(c);
        }catch (NoMoreCandidatosException e){
            // Não há mais candidatos
        }
    }
    
    public void elege(int n) throws NotEnoughCandidatosException{
        if(porEleger.size() >= n){
            int i;
            for(i=0;i<n;i++){
                elege();
            }
        }else throw new NotEnoughCandidatosException();
    }
   
    public ArrayList<Candidato> candidatos(){
        ArrayList<Candidato> candidatos = new ArrayList<>();
        for(Candidato c : this.porEleger){
            candidatos.add(c.clone());
        }
        for(Candidato c : this.eleitos){
            candidatos.add(c.clone());
        }
        return candidatos;
    }
    
    ////////////////////////////////////////////////////// PARTE II //////////////////////////////////////////////
    
    public TreeSet<Candidato> eleitos(){
        TreeSet<Candidato> candidatos = new TreeSet<Candidato>();
        for(Candidato c : eleitos){
            candidatos.add(c.clone());
        }
        return candidatos;
    }
    
    public TreeSet<Candidato> candidatosOrd(){
        TreeSet<Candidato> candidatos = new TreeSet<Candidato>(new IdadeNomeComparator());
        for(Candidato c : eleitos){
            candidatos.add(c.clone());
        }
        return candidatos;
    }
}