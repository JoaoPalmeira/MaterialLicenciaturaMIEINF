class Contador {
	private long i;
	synchronized void incr(){ i+=1;}
	synchronized long value() { return i;}

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

class Aula2{
	public static void main(Strings[] args) throws InterruptedException{
		final long X = 1000000000L;
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

private class Conta{
    
}

class Banco {
	final int NC;
	private Conta[];
	private int S;

	void deposito (int nc, int q){
	for(int i = 0; i<NC; i++)	
		if (i == nc) this.S += q;

	}
    void levantamento(int nc, int q){
    	if (this.NC == nc) this.S -= q;
    }
    int saldo(int nc){
        if (this.NC == nc) return this.S;
    }

    int transferir (int nc1, int nc2){
    	if (this.NC == nc1)
    }
}