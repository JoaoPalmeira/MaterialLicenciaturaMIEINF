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
public class Incrementor1 implements Runnable{

	private Counter1 c;
	int incvalue;
	
	public Incrementor1(Counter1 c,int incvalue){
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
