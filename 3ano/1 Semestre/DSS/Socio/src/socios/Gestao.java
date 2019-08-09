/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socios;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;


/**
 *
 * @author joaopalmeira
 */
public class Gestao {
    
    private Map<Integer, Aluno> alunos;
    
    private Map<Integer, Quota> quotas;
    
    private Aluno aluno;
    
    public Gestao(){
        alunos = new HashMap<>();
        Aluno u1 = new Aluno(70841,"Joel Morais","MIEI",2019,"Rua de Barros");
        Aluno u2 = new Aluno(73864,"João Palmeira","MIEI",2019,"Rua de Barros");
        Aluno u3 = new Aluno(73974,"Daniel Vieira","MIEI",2019,"Rua de Barros");
        Aluno u4 = new Aluno(70565,"Bruno Arieira","MIEI",2019,"Rua de Barros");
        Aluno u5 = new Aluno(74216,"Rodrigo Ferreira","MIEI",2019,"Rua de Barros");
        alunos.put(u1.getNum(),u1);
        alunos.put(u2.getNum(),u2);
        alunos.put(u3.getNum(),u3);
        alunos.put(u4.getNum(),u4);
        alunos.put(u5.getNum(),u5);
    }
    
   
    public void mudaSocio(Aluno aluno,int num,String nome,String curso,int ano,String morada){
        
        aluno.setNum(num);
        aluno.setNome(nome);
        aluno.setCurso(curso);
        aluno.setAno(ano);
        aluno.setMorada(morada);
        
             
    }
    
    
    public ArrayList<Aluno> getList(){
        return new ArrayList<>(alunos.values());
    }
    
    
    public Aluno getAluno(int num){
        
        try {
            Aluno ret = alunos.get(num);
            return ret;
        } catch (Exception e) {
            return null;
        }
    }
        
    
   // ------------------------------------ Métodos Alunos-------------------------------
   
   public void addAluno(String nome, int numero){
        
        Aluno a = new Aluno();
        a.setNome(nome);
        a.setNum(numero);
        alunos.put(numero,a);
       
   }
        
   public void addAluno(Aluno a){
        alunos.put(a.getNum(),a);
       
   }        

    
        
        
    }

