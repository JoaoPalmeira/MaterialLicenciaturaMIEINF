public class Banco {
	private static class Conta {
		private int saldo;
		synchronized deposito (int m) {saldo+=m;}
		synchronized levantamento (int m) {saldo -=m;}
	}

	Conta [] a;

	public Banco (int n){
		a = new Conta[n];
		for (int i=0; i<n; i++)
			a[i] = new Conta[];
	}

	public synchronized void deposito (int i, int m){
		a[i].deposito[m];
	}

	public synchronized void levantamento (int i, int m){
		a[i].saldo -= m;
	}

	public synchronized int saldo (int i){
		return a[i].saldo;
	}

}

