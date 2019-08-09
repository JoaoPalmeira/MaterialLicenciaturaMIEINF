package Diagrama_de_Classe_Métodos;

import java.lang.String;
import java.lang.Boolean;
import java.util.ArrayList;
import static java.lang.System.out;
import java.util.*;
import java.io.*;

import Diagrama_de_Classe_Métodos.Morador;

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