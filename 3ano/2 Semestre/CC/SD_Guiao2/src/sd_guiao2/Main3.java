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
public class Main3 {

	public static void main(String[] args) {
		BancoEx3 b = new BancoEx3(10);
		
		Thread t1=new Thread(new Cliente1Ex3(b));
		Thread t2=new Thread(new Cliente2Ex3(b));
		
		//b.depositar(0, 1000);
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("Valor Conta 0 é: " + b.consultar(0));
		System.out.println("Valor Conta 1 é: " + b.consultar(1));
	}
}
