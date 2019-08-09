class Armazem{

	private class Produto{
		int q;
		Condition c = l.newCondition();
    }

	Map<String, Produto> produtos = new HashMap<>();
    Lock l = new ReentrantLock();

	private Produto get(String s){
		Produto p = produtos.get(s);
		if(p== null){
			p= new Produto();
			produtos.put(s, p);
		}
	  return p;
	}

	void supply(String s, int q){
		l.lock();
		Produto p = get(s);
		p.q += q;
		p.c.signalAll();
		l.unlock();
	}

	void consume(String[] ss) throws InterruptedException{
		l.lock();
		int i=0;
		while(i<ss.length){
            Produto p = get(ss[i]);
            if(p.q==0){
            	p.c.await(); // métodos bloqueantes como await podem lançar exceções 
            	i=0;
            }else{
             i++;
          }
		}
		for(String s : ss)
			get(s).q--;
		l.unlock();
	}

	void consume(String[] ss) throws InterruptedException{
		l.lock();
		int i=0;
		while(true){
            Produto p = primeiroQueFalta(ss);
            if(p.q==null) break;
            p.c.await();
            i=0;
        }
		for(String s : ss)
			get(s).q--;
		l.unlock();
	}

}

