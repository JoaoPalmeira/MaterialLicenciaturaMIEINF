

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
}

class Cliente1 implements Runnable{

	private Banco banco;

	public Cliente1(Banco ba){
		this.banco = ba;
	}

	public void run(){

		//depositar 5€ 500 vezes na conta i
		for(int i=1; i<=10; i++){
			for(int j=0; j<500; j++){
				this.banco.depositar(i,5);
			}
		}
	}
}

class Cliente2 implements Runnable{

	private Banco banco;

	public Cliente2(Banco ba){
		this.banco = ba;
	}

	public void run(){

		//levantar 5€ 500 vezes na conta i
		for(int i=1; i<=10; i++){
			for(int j=0 ; j<500; j++){
				this.banco.levantar(i,5);
			}
		}
	}
}

class Exercicio2{

	public static void main(String args[]){

		Banco b = new Banco(10);

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

		for(int i=1; i<=10; i++){
			System.out.println("O valor final da conta "+i+" é: "+ b.consultar(i));
		}
	}
}










