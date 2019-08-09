/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joaopalmeira
 */
public class Main_ex2 {
    public static void main(String[] args){
	int N = 10;
	int I = 20;
	Thread[] ThreadArray= new Thread[N];
	Counter c = new Counter();
			
	for(int i=0; i<N; i++){
		ThreadArray[i]= new Thread(new Incrementor_ex2(c,I));	
		ThreadArray[i].setName(String.valueOf(i));
	}
		
	for(int i=0; i<N; i++){
		ThreadArray[i].start();
	}
			
		
	try {
		for(int i=0; i<N; i++){
			ThreadArray[i].join();
		}
	} catch (InterruptedException e) {
		System.err.println("Erro no Join!");
		e.printStackTrace();
	}
		
	System.out.println("Contador tem o valor: " + c.getCounter());
    }
}
