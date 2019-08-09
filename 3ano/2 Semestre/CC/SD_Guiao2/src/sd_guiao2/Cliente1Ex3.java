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
public class Cliente1Ex3 implements Runnable{
	private BancoEx3 banco;

	public Cliente1Ex3(BancoEx3 b){
		this.banco=b; 
	}

	public void run() {
            this.banco.depositar(0,1000);
            //transferir 1000 euros da conta 0 para a conta 1
            this.banco.transferir(0,1,1000);		
	} 
}
