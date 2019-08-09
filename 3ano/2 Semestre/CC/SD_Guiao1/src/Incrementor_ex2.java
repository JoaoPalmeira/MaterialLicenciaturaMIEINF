/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joaopalmeira
 */
public class Incrementor_ex2 implements Runnable{
    private Counter c;
    int incvalue;
	
    public Incrementor_ex2(Counter c,int incvalue){
	this.incvalue=incvalue;
	this.c = c;
    }
    public void run() {
    	for(int i = 0; i<this.incvalue; i++){  
            //this.c.increment(); 	//VERSAO 1
            this.c.counter++;		//VERSAO 2
	}
    }
}
