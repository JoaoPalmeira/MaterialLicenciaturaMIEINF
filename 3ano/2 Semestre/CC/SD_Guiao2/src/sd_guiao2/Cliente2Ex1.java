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
public class Cliente2Ex1 implements Runnable{
    private Banco banco;

	public Cliente2Ex1(Banco b){
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
