
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;

// exeções

class ContaInvalida extends Exception{

	public ContaInvalida(int id){
		System.out.println("Nao existe a conta com o id: " +id);
	}
}

class Conta{

	private ReentrantLock lockConta;
	private double saldo;
	private int idConta;

	public Conta(){
		this.lockConta = new ReentrantLock();
		this.saldo = 0;
		this.idConta = 0;
	}

	public Conta(int id){
		this.lockConta = new ReentrantLock();
		this.saldo = 0;
		this.idConta = id;
	}

	public Conta(int id, double valor){
		this.lockConta = new ReentrantLock();
		this.saldo = valor;
		this.idConta = id;
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

	// Metodos lock auxiliares

	public void lockConta(){
		this.lockConta.lock();
	}

	public void unlockConta(){
		this.lockConta.unlock();
	}
}

class Banco{

	private ReentrantLock lockBanco;
	private Map<Integer, Conta> contas;
	private int lastId;


	public Banco(){
		this.lockBanco = new ReentrantLock();
		this.contas = new HashMap<Integer, Conta>();
		this.lastId = 0;
	}

	// criar uma conta com um saldo
	public int criarConta(double valor){
		int idAux;

		// lock no banco
		this.lockBanco.lock();

		try{
			// criar a conta
			Conta conta = new Conta(this.lastId, valor);

			// meter no map
			this.contas.put(lastId, conta);

			// incrementar o id
			idAux = this.lastId;
			this.lastId++;

		} finally{
			// unlock do banco
			this.lockBanco.unlock();
		}

		return idAux;
	}

	// fechar uma conta com um id
	public double fecharConta(int id) throws ContaInvalida{
		double saldo;
		
		// lock do banco
		this.lockBanco.lock();

		try{

			if(!this.contas.containsKey(id)){
				// unlock do banco
				this.lockBanco.unlock();

				// lançar exeção
				throw new ContaInvalida(id);
			}

			// lock da conta
			this.contas.get(id).lockConta();

			// consultar o saldo da conta
			saldo = this.contas.get(id).consultar();

			//remover a conta do map
			this.contas.remove(id);

		} finally{

			/// unlock da conta
			this.contas.get(id).unlockConta();
		}

		return saldo;
	}

}

class Exercicio1{

}















