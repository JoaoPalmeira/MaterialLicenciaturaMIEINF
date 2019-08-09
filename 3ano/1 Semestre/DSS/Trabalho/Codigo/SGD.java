package Diagrama_de_Classe_Métodos;

public class SGD {
	private DespesaDAO _unnamed_DespesaDAO_;
	private ApartamentoDAO _unnamed_ApartamentoDAO_;
	private MoradorDAO _unnamed_MoradorDAO_;
	private SenhorioDAO _unnamed_SenhorioDAO_;

	public void autenticarMorador(Object aEmail, Object aPassword) {
		throw new UnsupportedOperationException();
	}

	public boolean acrescentarMorador(String aEmail, String aPassword, int aNIF) {
		throw new UnsupportedOperationException();
		try{ 
			morador = new Morador(aEmail, aPassword, aNIF);
   			_moradores.add(morador);
		}catch(MoradorExistenteException e){
		System.out.println("Morador já existente");}
   	}

	public void removerMorador(int aNIF) {
		throw new UnsupportedOperationException();
	}

	public void existeMorador(int aNIF) {
		throw new UnsupportedOperationException();
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
}