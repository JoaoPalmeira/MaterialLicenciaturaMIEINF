class Contador {
	long i;
	}
class Incrementador extends Thread{
	long I;
	Contador c;

	Incrementador (long I, Contador c){
		this.I = I;
		this.c = c;
	}

	public void run(){
	   for (long i=0; i< I; i++)
	      c.i += 1;
	}
}

class aula1{
	public static void main(String args[]){
		final long X = 1000000L;
		Contador c = new Contador();
		Thread t1 = new Incrementador(X, c);
		Thread t2 = new Incrementador(X, c);
		t1.start();
		t2.start();

		try{
			t1.join();
			t2.join();
		}
		catch (InterruptedException e){}
		System.out.println(c.i);
	}

}