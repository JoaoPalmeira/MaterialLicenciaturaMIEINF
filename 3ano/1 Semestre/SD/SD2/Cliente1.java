
/**
 * Escreva a descrição da classe Cliente1 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cliente1 implements Runnable {
	
	private Banco banco;

	public Cliente1(Banco b){
		this.banco=b; 
	}
	
	@Override
	public void run() {
		//adiciona 5 euros à conta 0 1000x
		int i;
		for(i=0; i<1000; i++){
			this.banco.depositar(0,5);
		}
		
	} 

}
