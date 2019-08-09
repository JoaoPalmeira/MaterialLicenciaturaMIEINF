
/**
 * Escreva a descrição da classe Counter aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Counter {

	public int counter;

	public Counter(){
		this.counter=0;
	}

	//METODO SYNCHRONIZED
	public synchronized void increment(){
		this.counter++;
	}

	//BLOCO SYNCHRONIZED
	public void increment2(){
		synchronized(this){
			this.counter++;
		}
	}	

	public synchronized int getCounter(){
		return this.counter;
	}

}
