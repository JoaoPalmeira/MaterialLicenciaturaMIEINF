
/**
 * Escreva a descrição da classe Cliente2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cliente2 implements Runnable {
	
	private Banco banco;

	public Cliente2(Banco b){
		this.banco=b; 
	}
	
	@Override
	public void run() {
		//levanta 5 euros à conta 0 1000x
		int i;
		for(i=0; i<1000; i++){
			this.banco.levantar(0,5);
		}
		
	} 

}
