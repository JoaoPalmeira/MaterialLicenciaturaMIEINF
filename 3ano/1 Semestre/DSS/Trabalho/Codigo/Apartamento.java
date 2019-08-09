package Diagrama_de_Classe_Métodos;

import java.util.Vector;
import Diagrama_de_Classe_Métodos.Morador;
import Diagrama_de_Classe_Métodos.Despesa;

public class Apartamento {
	public String _rua;
	public String _codpostal;
	public int _numporta;
	public int _andar;
	private Vector<Morador> _moradores = new Vector<Morador>();
	private Vector<Despesa> _unnamed_Despesa_ = new Vector<Despesa>();
}