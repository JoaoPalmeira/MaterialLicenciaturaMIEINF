
/**
 * Escreva a descrição da classe Incrementor aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Incrementor implements Runnable{

	private Counter c;
	int incvalue;
	
	public Incrementor(Counter c,int incvalue){
		this.incvalue=incvalue;
		this.c = c;
	}
	
	@Override
	public void run() {
		for(int i = 0; i<this.incvalue; i++){
			this.c.increment(); 	
		}
	}
}
