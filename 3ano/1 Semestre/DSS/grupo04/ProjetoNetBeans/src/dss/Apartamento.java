/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dss;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 *
 * @author sofia
 */
public class Apartamento extends Observable {
    
    //Variáveis de instância
    public String _rua;
	public String _codpostal;
	public int _numporta;
	public int _andar;
	private final ArrayList<Morador> _moradores = new ArrayList<>();
	private final ArrayList<Despesa> _unnamed_Despesa_ = new ArrayList<>();
    
    private Apartamento a;
    
    //Métodos de instância
    
    public ArrayList<Pagamento> consultarPagamentosEfetuados(String aT) throws CloneNotSupportedException {
        throw new UnsupportedOperationException();
        ArrayList<Pagamento> aux = new ArrayList<>();
        Iterable<Pagamento> _pagamentos = null;
        for (Pagamento p : _pagamentos) {
            if (p.getTipo() == aT) aux.add((Pagamento) p.clone());
        }
        return aux;
    }
    
    public ArrayList<Despesa> consultarDespesas(String aT) throws CloneNotSupportedException {
        throw new UnsupportedOperationException();
        ArrayList<Despesa> aux = new ArrayList<>();
        Iterable<Despesa> _despesas = null;
        for (Despesa d : _despesas) {
            if (d.getTipo().equals(aT)) aux.add((Despesa) d.clone());
        }
        return aux;
    }
    
    
}
