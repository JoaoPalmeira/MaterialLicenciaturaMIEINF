package Business;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joaopalmeira
 */
public class UC {
    
    private final String nome;
    private final int ano;
    private final int semestre;
    private final Map<String, Turno> turnos;
    private final List<String> alunos;
    private final int limite_faltas;
    
    
    public UC(String nome, int ano, int semestre, int limite_faltas) {
        this.nome = nome;
        this.ano = ano;
        this.limite_faltas = limite_faltas;
        this.semestre = semestre;
        this.turnos = new HashMap<>();
        this.alunos = new ArrayList<>();
    }
    
    
    public UC(String nome, int ano, int semestre, List<Turno> turnos, int limite_faltas) {
        this.nome = nome;
        this.ano = ano;
        this.limite_faltas = limite_faltas;
        this.semestre = semestre;
        this.turnos = new HashMap<>();
        this.alunos = new ArrayList<>();
        
        for(Turno t:this.turnos.values()){
            
            this.turnos.put(t.getNomeTurno(), t);
        }
        
       
    }
    
    
    public UC(UC u) {
        nome = u.getNome();
        ano = u.getAno();
        semestre = u.getSemestre();
        turnos = u.getTurnos();
        alunos = u.getAlunos();
        limite_faltas = u.getLimite_faltas();
    }

    public UC(String string, int aInt, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public String getNome() {
        return nome;
    }

    public int getLimite_faltas() {
        return limite_faltas;
    }
    
    
    public int getAno() {
        return ano;
    }
    
    
    public int getSemestre() {
        return semestre;
    }
    
    
    public Map<String, Turno> getTurnos() {
        Map<String, Turno> turno = new HashMap<>();
        
        for(Turno t:this.turnos.values()){
            
            turno.put(t.getNomeTurno(), t);
            
        }
    
        return turno; 
    }
    
    
    public Map<String, Turno> getTurnosTeoricos() {
        Map<String, Turno> teo = new HashMap<>();
        
        for(Turno t:this.turnos.values()){
            
            if(t.isTeorico())
                teo.put(t.getNomeTurno(),t);
            
        }

        return teo;
    }
    
    
    public Map<String, Turno> getTurnosPraticos() {
        Map<String, Turno> pratico = new HashMap<>();
        
        for(Turno t:this.turnos.values()){
            
            if(!t.isTeorico())
                pratico.put(t.getNomeTurno(),t);
            
        }

     return pratico;
    }
    
    
    public List<String> getAlunos() {
        return new ArrayList<>(alunos);
    }
    
    
    public int getNumPessoasTurno(String turno) {
        Turno t = turnos.get(turno);
       
        if (t==null) return 0;
        else return t.getNumAlunos();
        
    }
    
    
    public void addTurno(Turno t) {
        turnos.put(t.getNomeTurno(), t.clone());
    }
    
    
    public boolean alunoInscrito(String aluno) {
        return alunos.contains(aluno);
    }
    
    
    public void addAluno(String aluno) {
        alunos.add(aluno);
    }
    
    
    public void addAlunoTE(String alunoTE) {
        alunos.add(0, alunoTE);
    }
    
    
    public void adicionarAlunoTurno(String aluno, String turno) {
        Turno t = turnos.get(turno);
        
        if (t != null) {
            t.addAluno(aluno);
        }
    }
    
    
    public void removeAlunoTurno(String aluno, String turno) {
        Turno t = turnos.get(turno);
        
        if (t != null) {
            t.removeAluno(aluno);
        }
    }

    public boolean verificaUC(String uc){
        if(nome.equals(uc))
            return true;
        else return false;
    }

   
    /*
    public boolean verificaTurno_inserir(String turno){
        if(turnos.Constains(turno))
            return False;
        else return Turno;
    }
    */
 public boolean verifica_Turno(String nome){
        if(turnos.containsKey(nome))
            return true;
        else return false;
    }    
    
    
    
    
    
    public void trocarAlunos(String tOrigem, String tDestino, 
        Aluno aOrigem, Aluno aDestino) {
        String alunoOrigem = aOrigem.getNomeUtilizador();
        String alunoDestino = aDestino.getNomeUtilizador();
        Turno turnoOrigem = this.getTurnos().get(tOrigem);
        Turno turnoDestino = this.getTurnos().get(tDestino);

       
        turnoOrigem.addAluno(alunoDestino);
        turnoDestino.addAluno(alunoOrigem);    
        turnoDestino.removeAluno(alunoDestino);
        turnoOrigem.removeAluno(alunoOrigem);

   
        adicionarAlunoTurno(alunoOrigem, tDestino);
        adicionarAlunoTurno(alunoDestino, tOrigem);
        removeAlunoTurno(alunoOrigem, tOrigem);
        removeAlunoTurno(alunoDestino, tDestino);

        
    }

    public void troca_estatuto(String tOrigem,String tDestino,Aluno aOrigem){
        String aluno = aOrigem.getNomeUtilizador();
        Turno turnoOrigem = this.getTurnos().get(tOrigem);
        Turno turnoDestino = this.getTurnos().get(tDestino);

        turnoOrigem.addAluno(aluno);
        turnoDestino.removeAluno(aluno);

        adicionarAlunoTurno(aluno, tDestino);
        removeAlunoTurno(aluno, tOrigem);
    }
    
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {
            nome,
            ano,
            semestre,
            turnos,
            alunos
        });
    }
    
    
    @Override
    public UC clone() {
        return new UC(this);
    }
    
    
}
