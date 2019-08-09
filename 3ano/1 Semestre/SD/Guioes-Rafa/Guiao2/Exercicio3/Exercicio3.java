

class Banco{

	private double[] contas;
	private int nrContas;

	public Banco(int N){
		this.contas = new double[N+1];
		for(int i=1; i<=N; i++){
			this.contas[i] = 0;
		}
		this.nrContas = N;
	}

	synchronized public double consultar(int nrConta){
		return this.contas[nrConta];
	}

	synchronized public void levantar(int nrConta, double valor){
		this.contas[nrConta] = this.contas[nrConta] - valor;
	}

	synchronized public void depositar(int nrConta, double valor){
		this.contas[nrConta] = this.contas[nrConta] + valor;
	}

	synchronized public void transferir(int nrOrigem, int nrDestino, double valor){
		this.levantar(nrOrigem,valor);
		this.depositar(nrDestino,valor);
	}
}

class Cliente1 implements Runnable{

	private Banco banco;

	public Cliente1(Banco ba){
		this.banco = ba;
	}

	public void run(){

		// transferir 5000€ da conta 0 para a 1
		this.banco.transferir(0,1,5000);
		
	}
}

class Cliente2 implements Runnable{

	private Banco banco;

	public Cliente2(Banco ba){
		this.banco = ba;
	}

	public void run(){

		// levantar 5000€ da conta 1
		this.banco.levantar(1,5000);
		
	}
}

class Exercicio3{

	public static void main(String args[]){

		Banco b = new Banco(10);

		// depositar 5000€ na conta 0
		b.depositar(0,5000);

		Thread t1 = new Thread(new Cliente1(b));
		Thread t2 = new Thread(new Cliente2(b));

		t1.start();
		t2.start();

		try{
			t1.join();
			t2.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}

		System.out.println("O valor final da conta 0 é: "+ b.consultar(0));
		System.out.println("O valor final da conta 1 é: "+ b.consultar(1));
	}
}










