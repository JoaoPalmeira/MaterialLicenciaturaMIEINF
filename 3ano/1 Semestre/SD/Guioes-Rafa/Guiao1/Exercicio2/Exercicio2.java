
class Counter{

	public int count;

	public Counter(){
		this.count = 1;
	}

	public void inc(){
		this.count++;
	}

	public int getCount(){
		return this.count;
	}
}


class Increment implements Runnable{

	private int max;
	private Counter c;

	public Increment(int m, Counter co){
		this.max = m;
		this.c = co;
	}

	public void run(){
		
		for(int i=1 ; i<=this.max; i++){
			System.out.println("Thread:"+Thread.currentThread().getName()+ " <-> "+ this.c.getCount());
			//this.c.inc();    // Versao 1
			this.c.count++;  // Versao 2
		}
	}
}


class Exercicio2 {

	public static void main(String args[]){

		int N = 10;
		int I = 20;

		Thread[] threads = new Thread[N];

		for(int i=0; i<N; i++){
			threads[i] = new Thread(new Increment(I, new Counter()));
			threads[i].setName(String.valueOf(i));
		}

		for(int i=0; i<N; i++){
			threads[i].start();
					
		}

		try{
			for(int i=0 ; i<N; i++){
				threads[i].join();
			}
		}
		catch(InterruptedException e){
			System.err.println("Erro no Join!");
			e.printStackTrace();
		}
	}
}