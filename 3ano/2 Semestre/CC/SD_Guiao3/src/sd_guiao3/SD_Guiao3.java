/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_guiao3;

/**
 *
 * @author joaopalmeira
 */
public class SD_Guiao3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Banco banco = new Banco();
        //criar 2 contas com saldo 10
	for(int i = 0; i < 2; i++){
        	banco.criarConta(10);
	}
		
	Thread t1=new Thread(new Cliente1(banco));
	t1.setName("Cliente1");
	Thread t2=new Thread(new Cliente2(banco));
	t2.setName("Cliente2");
		
	t1.start();
	t2.start();
		
	try {
            t1.join();
            t2.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    }
}