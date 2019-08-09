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
public class Main4 {
	public static void main(String[] args) {

		BancoEx4 b = new BancoEx4(10);	
		Thread t1=new Thread(new Cliente1Ex4(b));
		Thread t2=new Thread(new Cliente2Ex4(b));
	
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
}
