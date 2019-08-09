package Diagrama_de_Classe_Métodos;

import java.lang.String;
import java.lang.Boolean;
import java.util.ArrayList;
import static java.lang.System.out;
import java.util.*;
import java.io.*;

import Diagrama_de_Classe_Métodos.Morador;
import Diagrama_de_Classe_Métodos.Despesa;

public class Apartamento {
	public String _rua;
	public String _codpostal;
	public int _numporta;
	public int _andar;
	private ArrayList<Morador> _moradores = new ArrayList<Morador>();
	private ArrayList<Despesa> _despesas = new ArrayList<Despesa>();

	public String getCodigoPostal() {
		return _codpostal;
	}

	public ArrayList<Morador> getMoradores() {
		return _moradores;
	}

	public void setMoradores(ArrayList<Moradores> mor) {
		_moradores = mor;
	}

	public ArrayList<Despesa> getDespesas() {
		return _despesas;
	}

}