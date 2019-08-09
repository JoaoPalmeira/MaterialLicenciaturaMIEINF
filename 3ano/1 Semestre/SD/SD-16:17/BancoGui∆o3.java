class Conta{
	Lock l = new ReentrantLock();
	int saldo;
	int saldo(){return saldo;}
	void dep(int v) {saldo +=v;}
	void levantamento(int v) {saldo -=v;}
    
}

class Banco{
	int nextId;
	Lock lock = new ReentrantLock();
	Map<Integer, Conta> = new HashMap<>()
	Banco(int n){
		contas = new HashMap<>();
	}


public int criarConta(int saldo){
	Conta c = new Conta[];
	lock.lock();
	try{nextId ++;
		contas.put(nextId, c); 
		return nextId;
	}finally{
		lock.unlock();
	}
}

public void dep(int i, int v){
	lock.lock();
	Conta c;33
	try{
	c = get(i);
	c.lock();
	}finally{
	lock.unlock();
	}
	try{
	c.dep(v);
    c.unlock();
}

public void levantamento(int i, int v){
	lock.lock();
	Conta c;
	try{
	c = get(i);
	c.lock();
	}finally{
	lock.unlock();
	}
	try{
	c.lev(v);
    }finally{
	c.lock.unlock();
    }
}


private Conta get(int id) throws ContaInvalida{
	Conta c = map.get(id);
	if(c==null) throw new ContaInvalida(id);
	return c;
}


void transferencia(int i, int j, int v){
	lock.lock();
}

}
