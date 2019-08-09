package Diagrama_de_Classe_Métodos;

import java.lang.String;
import java.lang.Boolean;
import java.util.ArrayList;
import static java.lang.System.out;
import java.util.*;
import java.io.*;

import Diagrama_de_Classe_Métodos.Apartamento;

public class Senhorio {
	private int _nIF;
	private ArrayList<Apartamento> _apartamentos = new ArrayList<Apartamento>();

	public Apartamento getDetalhes(String aCodPostal) {
		throw new UnsupportedOperationException();
		for (Apartamento a : _apartamentos) 
			if (a.getCodigoPostal() == aCodPostal) 
				return a;
		}	
	
	public void acrescentarMorador(String aEmail, String aPassword, int aNIF, String aCodPostal) {
		throw new UnsupportedOperationException();
		try{
			for (Apartamento a : _apartamentos) 
				if a.getCodigoPostal().equals(aCodPostal) {
					aux = new ArrayList<Morador>();
					aux = a.getMoradores();
					m1 = new Morador(aEmail, aPassword, aNIF);
					aux.add(m1.clone());
					a.setMoradores(aux);
				}
		} catch(MoradorExistenteException e){
		System.out.println("Morador já existente");
		}
	}

	public void removerMorador(int aNIF, String aCodPostal) {
		throw new UnsupportedOperationException();
		try{
			for (Apartamento a : _apartamentos) 
				if (a.getCodigoPostal().equals(aCodPostal)) {
					maux = new ArrayList<Morador>();
					maux = a.getMoradores();
					for (Morador m : maux)
						if (m.getNIF().equals(aNIF)) m.remove();
					a.setMoradores(maux);
				}
		} catch(MoradorNãoExistenteException e){
		System.out.println("Morador não existe.");
		}
	}

	public ArrayList<Despesa> consultDespesasAp(String aCodPostal, String aTipo) {
		throw new UnsupportedOperationException();
		try {
			for (Apartamento a : _apartamentos) 
				if (a.getCodigoPostal().equals(aCodPostal)){
						despaux = new ArrayList<Despesa>();
						for (Despesa d : a.getDespesas())
							if (d.getTipo().equals(aTipo))
								 despaux.add(d);
				}
			return despaux;	
		} catch (TipoDespesaInexistente e) {
			System.out.println("Não existem despespesas do tipo fornecido.")
		}
	}


	public ArrayList<Pagamento> consultPagEfetuadosMorador(String aCodPostal, int aNIF, String aTipo) {
		throw new UnsupportedOperationException();
		try {
			for (Apartamento a : _apartamentos) 
				if (a.getCodigoPostal().equals(aCodPostal)){
						for (Morador m : a.getMoradores()) {
							if (m.getNIF() == aNIF) {
								pagaux = new ArrayList<Pagamento>();
								for (Pagamento p : m.getPagamentos()) {
									if (p.getTipo().equals(aTipo)) pagaux.add(p);
								}
							}
						}
				}
			return pagaux;	
		} catch(TipoPagamentoInexistente e){
			System.out.println("O Morador dado não tem pagamentos do tipo fornecido.")
		}
	}

}
