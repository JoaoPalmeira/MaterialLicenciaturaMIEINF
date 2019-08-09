/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_guiao2;

/**
 *
 * @author joaopalmeira
 */
public class Counter1 {

	public int counter;

	public Counter1(){
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

	public synchronized int getCounter1(){
		return this.counter;
	}

}

