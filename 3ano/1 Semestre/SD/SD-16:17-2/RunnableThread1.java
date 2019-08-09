import java.lang.Thread;
import java.lang.Runnable;


public class RunnableThread1 implements Runnable
{
	int i = 3;

	public void run () {
		int c = 1;
		
		while (c <= i){

			System.out.println (c);
		}
		c = 1;
	}

	public static void main (String args[]){
		// N = 2; i = 3
		Thread t1 = new Thread (new RunnableThread1());
		Thread t2 = new Thread (new RunnableThread1());

		t1.start();
		t2.start();

		try{
			t1.join();
			t2.join();
		}
		catch (InterruptedException e){
		}
	}
}