public class Warehouse {
	private LOck l = new ReentrantLock();
	private Map<String,Product> m = new Hashmap<String,Product>();

	private class Product {
	int q = 0;
	Condition c = l.newCOndition();
	}

	private Product get(String s) {

	Product p = m.get(s);
	if (p!=null) return p
	m.put(s,p);
	return p;
 	}

	public void supply(Strings, int q) throws InterruptedException {
	l.lock();
	try {
		Product p = get(s);
		p.q += q;
		p.c.signalAll();
	    }finally{
		l.unlock();
	    }
	}
	


	public void consume(Strings, int q) throws InterruptedException {
	l.lock();
	try {
		for (int i = 0 ; i < a.lenght; )
		{
		 Product p = get (a[i]);
		 i++;
		 if (p.q == 0) 
		 {
			p.c.await();
			i = 0;
		 }
		}
		for (String s: a)
		get(s).q--;
		} finally {
			l.unlock();
		}
	  }

	public int r() throws InterruptedException {
	 int lv;
	rlock();
	lv = reg;
	runlock();
	return lv;
	}
	public int w(int v) throws InterruptedException {
	wlock();
	reg = v;
	wunlock();
	return v;
	}


	public Writer(RWreg rw)
	{
		this.rw = rw;
	}
	public void run () {
	Random rand = new Random
	int v;
	try{
		while(true) {
			v = rand.nextInt(500);

	class RWstarveR {
	public static void main (STRING[] args )
	{
		final


	class RwReg {
	int reg = 0;
	int readers = 0;
	int writers = 0;
	int ReentrantLock l = new ReentrantLock();
	final Condition OKread = l.newCondition();
	final Condition OKwrite = l.newCondition();

	public void rlock() throws InterruptedException { // lock para os leitores
		l.lock();
			while (writers != 0 || wantWrite > 0)
			OKread.await();
		  readers++;
		  //Okread.signalALl();
		l.unlock();
	}

	public void runlock() throws InterruptedException { // unlock para os leitores
		l.lock();
			readers--;
			if (readers == 0)
				OKwrite.await();
		l.unlock();
	}

	public void wlock()  throws InterruptedException { // lock para um escritor
		l.lock();
			wantWrite++;
			while(readers + writers != 0)
				OKwrite.await();
			wantWrite--;
			writers++;
		l.unlock();
	}

	public void wunlock throws InterruptedException { // unlock para um escritor
		l.lock();
			writers--;
			OKwrite.signalAll()
			OKwrite.signalAll();
		l.unlock();
	}

	class Reader implements Runnable {
	RWreg rw;
	public Reader(RWreg rw) {
		this.rw = rw;
	}

	public int r() throws InterruptedException {
		int lv;
		rlock();
			lv = reg;
		runlock();
		return lv;
	}
	
	public void run() {
		Random rand = new Random();
		try {
		  while(true) {
			Thread.sleep(rand.nextInt(500));
			System.out.print("Read value");
			System.out.print(rw.r());
		  }
		} catch (InterruptedException e) {
		  System.out.println("Interrupted");
		}
