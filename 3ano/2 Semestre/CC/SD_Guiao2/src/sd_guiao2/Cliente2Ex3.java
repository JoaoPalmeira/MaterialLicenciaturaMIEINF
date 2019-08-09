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
public class Cliente2Ex3 implements Runnable{
	private BancoEx3 banco;

	public Cliente2Ex3(BancoEx3 b){
		this.banco=b; 
	}

	public void run() {
		//levanta 1000 euros da conta 1
		this.banco.levantar(1,1000);		
	} 
}
