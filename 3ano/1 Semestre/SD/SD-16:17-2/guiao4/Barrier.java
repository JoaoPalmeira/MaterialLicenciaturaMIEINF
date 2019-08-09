
import java.util.Random;

public class Barrier {
	int N;
	int size;

	public Barrier (int n)
	{ 
		this.N = n; 
		size = 0;
	}

	public synchronized void esperar() throws InterruptedException{

		this.size++;
		if (this.size == this.N){
			this.size = 0;
			System.out.println ("acorda!");
			notifyAll();
		}
		else
			this.wait();
		
	}
class Client implements Runnable {
	
	Barrier b;
	int n;
	public Client (Barrier b , int n) {
		this.b = b;
		this.n = n;
	}
	public void run() {
	Random rand = new Random();
	int i = 0;
	try {
		while(true){
		i++;
		System.out.println("Thread" + n + "started stage" + i);
		Thread.sleepc(rand.nextInt(5000));
		System.out.println("Thread" + n + "finished stage" + i);
		b.Barrier(n);
	}
  }catch (InterruptedException e) {
  	System.out.println("Interrupted");
   }
  }
 }
 
 class Main {
   
   public static void main (String[] args) {
   	
   	final int N = 5;
   	Barrier b = new Barrier(N);
   	for (int i = 0 ; i < N ; i++)
   		new Thread(new Client(b,i)).start();
   	}
}
