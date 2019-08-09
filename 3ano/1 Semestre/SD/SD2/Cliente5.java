
/**
 * Escreva a descrição da classe Cliente5 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cliente5 implements Runnable {
	
	private Banco3 banco;

	public Cliente5(Banco3 b){
		this.banco=b; 
	}
	
	@Override
	public void run() {
		System.out.println("Cliente 1: Transferência iniciada");
		this.banco.transferir(0,1,1000);
		System.out.println("Cliente 1: Transferência realizada");
		
	} 

}
