
/**
 * Escreva a descrição da classe Cliente3 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cliente3 implements Runnable {

	private Banco2 banco;

	public Cliente3(Banco2 b){
		this.banco=b; 
	}

	@Override
	public void run() {
		//transferir 1000 euros da conta 0 para a conta 1
		this.banco.transferir(0,1,1000);		
	} 

}
