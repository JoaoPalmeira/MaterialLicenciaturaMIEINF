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
public class Main2 {
    public static void main(String[] args) {
		int NUM_CONTAS = 10;
		Banco b = new Banco(NUM_CONTAS);
		Thread t1=new Thread(new Cliente1Ex1(b));
		Thread t2=new Thread(new Cliente2Ex1(b));
                
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Valor Conta 0 é: " + b.consultar(0));
	}
}
