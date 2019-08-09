package Business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 *
 * @author joaopalmeira
 */
public class Aluno extends Utilizador {
    
    private final boolean isTeorico;
    private final Map<String, List<String>> turnos;
    private final Map<UC,Integer> faltas;
   
    
    
    public Aluno(String nomeUtilizador, String password, boolean isTeorico,Utilizador user) {
        super(nomeUtilizador, password);
        
        this.isTeorico = isTeorico;
        faltas = new HashMap<>();
        turnos = new HashMap<>();
        
    }

    public Aluno(boolean isTeorico,Map<UC,Integer> faltas, Map<String, List<String>> turnos, String nomeUtilizador, String password,Utilizador user) {
        super(nomeUtilizador, password);
        this.isTeorico = isTeorico;
        this.turnos = turnos;
        this.faltas = new HashMap<>();
    
    }

   
   
    public Aluno(Aluno a) {
        super(a);
      
        isTeorico = a.isTeorico;
        turnos = a.getTurnos();
        faltas = a.getFaltas();
       
    }

    public Aluno(boolean isTeorico, Map<String, List<String>> turnos, Map<UC, Integer> faltas, String nomeUtilizador, String password) {
        super(nomeUtilizador, password);
        this.isTeorico = isTeorico;
        this.turnos = turnos;
        this.faltas = faltas;
    }

    public Aluno(boolean isTeorico, Map<String, List<String>> turnos, Map<UC, Integer> faltas, Utilizador u) {
        super(u);
        this.isTeorico = isTeorico;
        this.turnos = turnos;
        this.faltas = faltas;
    }

    public Aluno(String string, String string0, boolean aBoolean) {
        super(string, string0);
        this.isTeorico = aBoolean;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public boolean isTeorico() {
        return isTeorico;
    }

    public Map<UC,Integer> getFaltas() {
        return faltas;
    }

    
    
    
    
    public Map<String, List<String>> getTurnos() {
        Map<String, List<String>> turno = new HashMap<>();
        
        
        for(String s:this.turnos.get(turno)){
            
            turno.put(s, new ArrayList<>(turnos.get(s)) );
        }
        
        return turno;
    }
    
    
   public void registaFalta(String nome_user,UC uc,Turno t){
       
       int num_faltas = faltas.get(uc);
       
       for(UC u:this.faltas.keySet()){
           
           if(nome_user.equals(super.getNomeUtilizador()) && uc.getNome().equals(uc.getNome())){
               
               if(u.getLimite_faltas()>faltas.get(u)){
                   
                   num_faltas ++;
                   
                   faltas.put(u, num_faltas );
                   
               }
               
               else removeTurno(uc.getNome(),t.getNomeTurno());
               
           }
               
               
           
           
           
           
       }
       
       //if(super.getNomeUtilizador().equals(nome_user) && faltas.get(uc).getNome().equals(uc.getNome()))
           
           

   }
    
    
    public void adicionaTurno(String uc, String turno) {
        List<String> turnosUC = turnos.get(uc);
        
        if (turnosUC == null) {
            turnosUC = new ArrayList<>();
            turnosUC.add(turno);
            turnos.put(uc, turnosUC);
        } else {
            turnosUC.add(turno);
        }
    }
    
    
    public void removeTurno(String uc, String turno) {
        List<String> turnosUC = turnos.get(uc);
        
        if (turnosUC != null) {
            turnosUC.remove(turno);
        }
    }
    
    
    
    
    
    private boolean pertenceTurno(String uc, String turno) {
        boolean pertence = false;
        List<String> turnosUC = turnos.get(uc);
        
        if (turnosUC != null) {
            pertence = turnosUC.contains(turno);
        } 
            
        return pertence;
    }
    
    public boolean verificaAluno(String nome_aluno){
        if(super.getNomeUtilizador().equals(nome_aluno))
            return true;
        else return false;
    }
    
    
    
    @Override
    public int hashCode() {
        return super.hashCode() +
                Arrays.hashCode(new Object[] {
                    isTeorico
                });
    }
    
    
    @Override
    public Aluno clone() {
        return new Aluno(this);
    }
}
