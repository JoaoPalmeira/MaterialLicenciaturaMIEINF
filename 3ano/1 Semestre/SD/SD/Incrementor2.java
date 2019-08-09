
/**
 * Escreva a descrição da classe Incrementor2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Incrementor2 implements Runnable{

	private Counter c;
	int incvalue;
	
	public Incrementor2(Counter c,int incvalue){
		this.incvalue=incvalue;
		this.c = c;
	}
	
	@Override
	public void run() {
		for(int i = 0; i<this.incvalue; i++){
			//this.c.increment(); 	//VERSAO 1
			this.c.counter++;		//VERSAO 2
		}
	}
}
