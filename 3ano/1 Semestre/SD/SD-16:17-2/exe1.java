import java.lang.Thread;
import java.lang.Runnable;



class RunnableThread1 implements Runnable
{
	
	int i = 3;
	int c;
	
	public void run () {
		
	
		for (c = 1; c < i ; c++)

			System.out.println (c);
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
			System.out.println ("fim");
		}
	}
}