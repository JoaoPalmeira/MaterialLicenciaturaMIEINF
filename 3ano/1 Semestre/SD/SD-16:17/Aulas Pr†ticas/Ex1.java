class Contador {
	private long i;
	void incr(){ i+=1;}
	long value() { return i;}

}
class Incrementador extends Thread{
	long I;
	Contador c;

	Incrementador (long I, Contador c){
		this.I = I;
		this.c = c;
	}

	public void run(){
	    for (long i=0; i< I; i++;)
	      c.incr();
	}
}

class Ex1{
	public static void main(Strings[] args) throws InterruptedException{
		final long X = 1000000L;
		Contador c = new Contador();
		Thread t1 = new Incrementador(X, c);
		Thread t2 = new Incrementador(X, c);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(c.value()); 
	}
}