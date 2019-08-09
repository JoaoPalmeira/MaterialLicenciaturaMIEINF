//??
//não deve estar correto
class Conta{
	Lock l = new ReentrantLock();
	int saldo;
	int saldo(){return saldo;}
	void dep(int v) {saldo +=v;}
	void levantamento(int v) {saldo -=v;}
    
}

import java.io*;
import java.net.*;

public class Client{

    public static void main(String[] args) throws Exception{
		String host = args[0];
	    int port = Integer.parseInt(args[1]);
	    Socket s = new Socket(host, port);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream());
        	
        
        while(true){
            String s1 = System.console().readLine();
            if (s1 == null) break;
            out.println(s1);
            out.flush();
            String s2 = in.readLine();
            if (s2 == null) break;
            System.out.println(s2);
       }
       s.close();
    }

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
