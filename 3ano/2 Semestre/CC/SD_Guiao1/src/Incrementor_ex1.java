/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joaopalmeira
 */
public class Incrementor_ex1 implements Runnable{
    private int inc;
    public Incrementor_ex1(int inc){
        this.inc=inc;
    }
    
    public void run(){
        for(int i = 1; i<=this.inc; i++){
            System.out.println("T"+Thread.currentThread().getName()+":"+i);
        }
    }
}
