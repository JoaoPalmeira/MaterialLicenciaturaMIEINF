/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socios;

import java.util.*;

/**
 *
 * @author joaopalmeira
 */
public class Aluno {
    
    private String nome;
    private int num;
    private String curso;
    private int ano;
    private String morada;
    private List<Double> quotas;
    
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String n){
        this.nome=n;
    }
    
    public int getNum(){
        return num;
    }
    
    public void setNum(int n){
        this.num=n;
    }
    
    public String getCurso(){
        return curso;
    }
    
    public void setCurso(String c){
        this.curso=c;
    }
    public int getAno(){
        return ano;
    }
    public void setAno(int a){
        this.ano=a;
    }
    public String getMorada(){
        return morada;
    }
    public void setMorada(String m){
        this.morada=m;
    }
    
    public Aluno(){
        this.nome = " ";
        this.num = 0;
        this.curso = " ";
        this.ano = 0;
        this.morada = " ";
        this.quotas = new ArrayList<>();
    }
    
    
    
    public Aluno(Aluno a){
        this.nome = a.getNome();
        this.num = a.getNum();
        this.curso = a.getCurso();
        this.ano = a.getAno();
        this.morada = a.getMorada();
    }
    
    public Aluno(int num,String nome,String curso,int ano,String morada){
        this.nome = nome;
        this.num = num;
        this.curso = curso;
        this.ano = ano;
        this.morada = morada;
    }
    public static int lerInteiro(String mensagem){  
        Scanner scan = new Scanner(System.in);  
        String numero = scan.nextLine();  
        int num = 0;  
        boolean conversao = true;  
        while(conversao){  
            try{  
                num = Integer.parseInt(numero);  
                conversao = false;  
            }catch(Exception e){  
                System.out.println("Só é válido numeros inteiros!");  
                System.out.println(mensagem);
                numero = scan.nextLine();
            }  
        }
        return num;
    }
    
    @Override
    public Aluno clone(){
       return new Aluno(this);
    }
    
    @Override
    public boolean equals(Object o) {
       if(o == this) {
            return true;
       }
       if(o == null || o.getClass() != this.getClass()) {
            return false;
       }
       Aluno a = (Aluno) o;
       return a.getNome().equals(this.nome) && 
              a.getNum() == this.num && a.getCurso().equals(this.curso) && 
              a.getAno() == this.ano && a.getMorada().equals(this.morada);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.nome);
        hash = 19 * hash + this.num;
        hash = 19 * hash + Objects.hashCode(this.curso);
        hash = 19 * hash + this.ano;
        hash = 19 * hash + Objects.hashCode(this.morada);
        hash = 19 * hash + Objects.hashCode(this.quotas);
        return hash;
    }
   
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Número: ").append(num).append("\n");
        sb.append("Curso: ").append(curso).append("\n");
        sb.append("Ano: ").append(ano).append("\n");
        sb.append("Morada: ").append(morada).append("\n");
        return sb.toString();
    }    
}
