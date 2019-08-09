/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joaopalmeira
 */
public class HelloRunnable2 implements Runnable{
    int n;
    public void run() {
        System.out.println(n);
    }
   HelloRunnable2(int a) {
   n=a;
   }
   public static void main(String args[]) {
    HelloRunnable2 r222 = new HelloRunnable2(222);
    HelloRunnable2 r111 = new HelloRunnable2(111);
    Thread t1=new Thread(r222);
    Thread t2=new Thread(r111);;
       System.out.println("Antes");
    t1.start();
    t2.start();
       System.out.println("Depois");
    try {
        //join: O método pode ser usado para pausar a execução do thread 
        //atual até que o thread especificado esteja inativo. 
       t2.join();
       t1.join();
    } catch (InterruptedException e) {}
       System.out.println("Fim");
     }
}
