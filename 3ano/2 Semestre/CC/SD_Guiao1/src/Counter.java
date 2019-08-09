/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joaopalmeira
 */
public class Counter {
    public int counter;
	
    public Counter(){
	this.counter=0;
    }
	
    public void increment(){
	this.counter++;
    }
	
    public int getCounter(){
    	return this.counter;
    }
}
