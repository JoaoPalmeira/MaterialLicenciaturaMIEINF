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
public class Cliente2Ex4 implements Runnable{
	private BancoEx4 banco;

	public Cliente2Ex4(BancoEx4 b){
		this.banco=b; 
	}
	
	@Override
	public void run() {
		System.out.println("Cliente 2: Transferência iniciada");
		this.banco.transferir(1, 0, 1000);
		System.out.println("Cliente 2: Transferência realizada");
		
	} 
}
