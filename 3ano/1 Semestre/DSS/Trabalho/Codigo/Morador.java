package Diagrama_de_Classe_Métodos;

import java.util.Vector;
import Diagrama_de_Classe_Métodos.Despesa;

public class Morador implements Identificador {
	private String _email;
	private String _password;
	private int _nIF;
	private ArrayList<Despesa> _despesas = new ArrayList<Despesa>();
    private ArrayList<Pagamento> _pagamentos = new ArrayList<Pagamento>();

    public void pagarDespesa(int aId) {
        throw new UnsupportedOperationException();
        try {
            for (Despesa d : _despesas) {
                if (d.getId() == aId) {
                    for (Pagamento p : _pagamentos) id=id+1;
                    pagamento = new Pagamento(d.getTipo(), d.getValor(), d.getDescricao, id);
                    _pagamentos.add(pagamento.clone());
                    d.remove();
                }
            }
        } catch (DespesaInexistenteException e) {
        System.out.println("Despesa Inexistente.");
        }
    }

    public Despesa getDespesa(int aId) {
        throw new UnsupportedOperationException();
        for (Despesa d : _despesas)
            if (d.getId() == aId) return d;
    }

    public void acrescentaDespesa(String aT, double aV, String aD) {
        throw new UnsupportedOperationException();
        for (Despesa d : _despesas) id=id+1;
        despesa = new Despesa(aT, aV, aD, id);
        _despesas.add(despesa.clone());
    }

    public ArrayList<Despesa> consultarDespesas(String aT) {
        throw new UnsupportedOperationException();
        ArrayList<Despesa> aux = new ArrayList<Despesa>();
        for (Despesa d : _despesas) 
            if (d.getTipo() == aT) aux.add(d.clone());
        return aux;
    }

    public ArrayList<Pagamento> consultarPagamentosEfetuados(String aT) {
        throw new UnsupportedOperationException();
        ArrayList<Pagamento> aux = new ArrayList<Pagamento>();
        for (Pagamento p : _pagamentos) 
            if (p.getTipo() == aT) aux.add(p.clone());
        return aux;
    }

    public Pagamento getPagEfetuado(int aId) {
        throw new UnsupportedOperationException();
        for (Pagamento p : _pagamentos)
            if (p.getId() == aId) return p;
    }
}