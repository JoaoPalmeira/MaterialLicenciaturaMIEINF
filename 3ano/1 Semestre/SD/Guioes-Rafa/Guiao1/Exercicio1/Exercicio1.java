
class Increment implements Runnable{

	private int max;

	public Increment(int m){
		this.max = m;
	}

	public void run(){
		int i;
		
		for(i=1 ; i<=this.max; i++){
			System.out.println("Thread:"+Thread.currentThread().getName()+ " <-> "+i);
		}
	}
}


class Exercicio1 {

	public static void main(String args[]){

		int N = 10;
		int I = 40;

		Thread[] threads = new Thread[N];

		for(int i=0; i<N; i++){
			threads[i] = new Thread(new Increment(I));
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