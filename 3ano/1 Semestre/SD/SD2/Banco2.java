
/**
 * Escreva a descrição da classe Banco2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Banco2 {

	private double[] contas;
	
	Banco2(int numContas){
		contas = new double[numContas];
		for(int i=0; i<numContas; i++){
			contas[i]=0;
		}	
	}
	
	public double consultar(int nr_conta){
		return this.contas[nr_conta];
	}
	
	public synchronized void levantar(int nr_conta, double valor){
		this.contas[nr_conta]=this.contas[nr_conta]-valor;
	}
	
	
	public synchronized void depositar(int nr_conta, double valor){
		this.contas[nr_conta]=this.contas[nr_conta]+valor;
	}
	
	public void transferir(int conta_origem, int conta_destino, double valor){
		this.levantar(conta_origem, valor);
		this.depositar(conta_destino, valor);
	}
}
