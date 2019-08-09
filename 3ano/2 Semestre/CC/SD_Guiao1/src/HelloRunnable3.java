/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joaopalmeira
 */
public class HelloRunnable3 implements Runnable{
    int n;
    public void run() {
        System.out.println(n);
        this.set(111);
    }
    HelloRunnable3(int a) {
        n=a;
    }
    public void set(int b) { n=b; }
    public static void main(String args[]) {
        HelloRunnable3 r=new HelloRunnable3(222);
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        System.out.println("Antes");
        t1.start();
        t2.start();
        System.out.println("Depois");
    try {
        t1.join();
        t2.join();
    } catch (InterruptedException e) {}
    }
}
