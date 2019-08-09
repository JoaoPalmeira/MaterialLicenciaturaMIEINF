/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dss;

import java.util.ArrayList;

/**
 *
 * @author sofia
 */
public class Despesa {
    
    //Variáveis de instância
    private String _tipo;
    private int _id;
    private String _descricao;
    private double _valor;
    private final ArrayList<Morador> _moradores = new ArrayList<>();
    
    //Métodos de instância
    
    public String getTipo() {
		return _tipo;
	}
}
