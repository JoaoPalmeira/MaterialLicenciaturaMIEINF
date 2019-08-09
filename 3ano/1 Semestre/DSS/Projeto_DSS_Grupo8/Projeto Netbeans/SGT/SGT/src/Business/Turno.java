package Business;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaopalmeira
 */


public class Turno {
    
    private String nomeTurno;
    private boolean isTeorico;
    private int capacidade;
    private List<Horario> horarios;
    private List<String> alunos;
    //private List<Pair<String, Boolean>> pedidos;
    
    
    public Turno(String nomeTurno, boolean isTeorico, int capacidade) {
        this.nomeTurno = nomeTurno;
        this.isTeorico = isTeorico;
        this.capacidade = capacidade;
        this.horarios = new ArrayList<>();
        this.alunos = new ArrayList<>();
        //pedidos = new ArrayList<>();
    }
    
    
    public Turno(Turno t) {
        nomeTurno = t.getNomeTurno();
        isTeorico = t.isTeorico;
        capacidade = t.getCapacidade();
        horarios = t.getHorarios();
        alunos = t.getAlunos();
        //pedidos = t.getPedidos();
    }
    
    
    public String getNomeTurno() {
        return nomeTurno;
    }
    
    
    public boolean isTeorico() {
        return isTeorico;
    }
    
    
    public int getCapacidade() {
        return capacidade;
    }
    
    
    public List<Horario> getHorarios() {
        List<Horario> list = new ArrayList<>();
        
        for(Horario h:this.horarios)
            list.add(h.clone());

        
        return list;
    }
    
    
    public List<String> getAlunos() {
        return new ArrayList<>(alunos);
    }
    
    
    /*public List<Pair<String, Boolean>> getPedidos() {
        return new ArrayList<>(pedidos);
    }*/
    
    
    public int getNumAlunos() {
        return alunos.size();
    }
    
    
    public boolean turnoCheio() {
        return capacidade <= alunos.size();
    }
    
    
    public boolean sobreposicao(Turno a) {
        List<Horario> horariosT = a.getHorarios();
        boolean compara = false;
        
        for (int i = 0; i < horarios.size() && !compara; i++) {
            Horario b = horarios.get(i);
            
            for (int j = 0; j < horariosT.size() && !compara; j++) {
                compara = b.compara(horariosT.get(j));
            }
        }
        
        return compara;
    }
    
    
    public void adicionarHorario(Horario a) {
        horarios.add(a.clone());
    }
    
    
    public void addAluno(String aluno) {
        alunos.add(aluno);
    }
    
    
    public void removeAluno(String aluno) {
        alunos.remove(aluno);
    }
    
   
    @Override
    public Turno clone() {
        return new Turno(this);
    }
}
