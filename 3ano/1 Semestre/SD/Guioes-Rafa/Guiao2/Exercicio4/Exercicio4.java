
class Conta{

	private double saldo;

	public Conta(){
		this.saldo = 0;
	}

	public double consultar(){
		return this.saldo;
	}

	public void levantar(double valor){
		this.saldo = this.saldo - valor;
	}

	public void depositar(double valor){
		this.saldo = this.saldo + valor;
	}


}

class Banco{

	private Conta[] contas;
	private int nrContas;

	public Banco(int N){
		this.contas = new Conta[N];
		for(int i=0; i<N; i++){
			this.contas[i] = new Conta();
		}
		this.nrContas = N;
	}

	public double consultar(int nrConta){
		synchronized(this.contas[nrConta]){
			return this.contas[nrConta].consultar();
		}
	}

	public void levantar(int nrConta, double valor){
		synchronized(this.contas[nrConta]){
			this.contas[nrConta].levantar(valor);	
		}
	}

    public void depositar(int nrConta, double valor){
    	synchronized(this.contas[nrConta]){
    		this.contas[nrConta].depositar(valor);
    	}
	}

	synchronized public void transferir(int nrOrigem, int nrDestino, double valor){
		synchronized(this.contas[nrOrigem]){
			synchronized(this.contas[nrDestino]){
				this.contas[nrOrigem].levantar(valor);
				this.contas[nrDestino].depositar(valor);
			}
		}
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

class Exercicio4{

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










