class Armazem{

	private static class Produto{
		int q;
		Condition c;

		Produto(Lock l) {c = l.newCondition();}
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
}

