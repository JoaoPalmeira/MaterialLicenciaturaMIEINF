package Diagrama_de_Classe_M�todos;

import java.util.Vector;
import Diagrama_de_Classe_M�todos.Morador;

public class Despesa {
	private String _tipo;
	private int _id;
	private String _descricao;
	private double _valor;
	private ArrayList<Morador> _moradores = new ArrayList<Morador>();

	public void setTipo(String tipo) {
		_tipo = tipo;
	}

	public void setDescricao (String descricao) {
		_descricao = descricao;
	}

	public void setValor (doube valor) {
		_valor = valor;
	}

	public String getTipo() {
		return _tipo;
	}
	
	public int getId() {
		return _id;
	}
}