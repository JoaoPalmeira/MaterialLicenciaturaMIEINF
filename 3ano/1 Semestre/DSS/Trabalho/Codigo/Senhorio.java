package Diagrama_de_Classe_M�todos;

import java.util.Vector;
import Diagrama_de_Classe_M�todos.Apartamento;

public class Senhorio {
	private int _nIF;
	private ArrayList<Apartamento> _apartamentos = new ArrayList<Apartamento>();

	public Apartamento getDetalhes(String aCodPostal) {
		throw new UnsupportedOperationException();
		for (Apartamento a : _apartamentos) 
			if (a.getCodigoPostal() == aCodPostal) 
				return a;
		}	
}