
/**
 * Escreva a descrição da classe Cliente4 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cliente4 implements Runnable {

	private Banco2 banco;

	public Cliente4(Banco2 b){
		this.banco=b; 
	}

	@Override
	public void run() {
		//levanta 1000 euros da conta 1
		this.banco.levantar(1,1000);		
	} 
}
