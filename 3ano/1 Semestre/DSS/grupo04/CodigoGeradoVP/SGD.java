package Diagrama_de_Classe_Métodos;

import java.lang.String;
import java.lang.Boolean;
import java.util.ArrayList;
import static java.lang.System.out;
import java.util.*;
import java.io.*;

public class SGD {
	private DespesaDAO _unnamed_DespesaDAO_;
	private ApartamentoDAO _unnamed_ApartamentoDAO_;
	private MoradorDAO _unnamed_MoradorDAO_;
	private SenhorioDAO _unnamed_SenhorioDAO_;

	public boolean veSessao;

	public void autenticarMorador(String aEmail, String aPassword) throws SemAutorizacaoException{
		throw new UnsupportedOperationException();
		for (Morador m : moradores) 
			if (m.getEmail().equals(aEmail)) { 
				if (m.getPassword().equals(aPassword)) {
					veSessao = true;
					throw new SemAutorizacaoException("Morador autenticado com sucesso! Bem vindo");
				}
				else{
					veSessao = false;
					throw new SemAutorizacaoException("Password errada.")
				}
			}			
			else{
				veSessao = false;
				throw new SemAutorizacaoException("Email errado.");
			}
	}
}