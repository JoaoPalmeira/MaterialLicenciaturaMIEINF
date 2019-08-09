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
public class Cliente1Ex4 implements Runnable{
	private BancoEx4 banco;

	public Cliente1Ex4(BancoEx4 b){
		this.banco=b; 
	}
	
	@Override
	public void run() {
		System.out.println("Cliente 1: Transferência iniciada");
		this.banco.transferir(0,1,1000);
		System.out.println("Cliente 1: Transferência realizada");
        }
}
