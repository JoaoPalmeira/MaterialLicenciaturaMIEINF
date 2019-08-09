package main.business;

import java.util.ArrayList;
import java.util.List;

public class Aluno implements Identificavel {
	private String _nome;
	private int _numero;
	private String _email;
	public List<Avaliacao> _avaliacoes;
	public List<Falta> _faltas;

    public Aluno(String _nome, int _numero, String _email) {
        this._nome = _nome;
        this._numero = _numero;
        this._email = _email;
        this._avaliacoes = new ArrayList<>();
        this._faltas = new ArrayList<>();
    }
        
        
        
    @Override
    public String getId() {
        return "a"+_numero;
    }

    public void setAvaliacoes(List<Avaliacao> _avaliacoes) {
        this._avaliacoes = _avaliacoes;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public void setFaltas(List<Falta> _faltas) {
        this._faltas = _faltas;
    }

    public void setNome(String _nome) {
        this._nome = _nome;
    }

    public void setNumero(int _numero) {
        this._numero = _numero;
    }

    public List<Avaliacao> getAvaliacoes() {
        return _avaliacoes;
    }

    public String getEmail() {
        return _email;
    }

    public List<Falta> getFaltas() {
        return _faltas;
    }

    public String getNome() {
        return _nome;
    }

    public int getNumero() {
        return _numero;
    }
    
    
           
        
}