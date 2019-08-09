class Contador{
	long i;
	public  long get(){
		return i;
	}
	public void inc(){
		i++;
	}
}

class Incrementer implements Runnable{
	Contador c;
	Incrementer(Contador cshared){
		c=cshared;
	}
	public void run(){
		final long I = 100;
		for(int i=0; i<I; i++){
			c.inc();
		}
	}
}

class ExThreads1a{
	public static void main(String[] args){
		try{
			final int N=10;
            Contador c;
			Incrementer worker;
			Thread t[] = new Thread[N];

			c = new Contador();
			worker = new Incrementer();

			for(int i=0; i<N; i++){
				t[i] = new Thread(worker);
			}

			for(int i=0; i<N; i++){
				t[i].start();
			}
			for(int i=0; i<N; i++){
				t[i].join();
			}
		} catch(InterruptedException e) {}
	}
}