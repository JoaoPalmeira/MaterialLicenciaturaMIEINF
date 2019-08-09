/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

/**
 *
 * @author André
 */
public class Banco {

    
	private static class Conta {
		private int saldo;
		synchronized deposito (int m) {saldo+=m;}
		synchronized levantamento (int m) {saldo -=m;}
	}

	Hashtable<String, Conta> = new Hashtable<String, Conta>();
        
	public Banco (int n){
		lock l = new ReentrantLock();
	}

	public void deposito (int i, int m){
	  l.lock();
          try{
            a[i].deposito[m];
	  }finally{
            l.unlock();
          }
        }

	public levantamento (int i, int m){
            l.lock();
            try{
		a[i].saldo -= m;
            }finally{
                l.unlock();
            }
	}

	public int saldo (int i){
            l.lock();
            try{
                return a[i].saldo;
            }finally{
                l.unlock();
            }
        }
        
        public void transferencia(int i, int j, int m){
            Conta ci = a[i];
            Conta cj = a[j];
            Conta l1, l2;
            if(i<j){l1=ci;l l2=cj;}
            else{l1=cj; l2=ci;}
               l1.lock();
                 try{
                     ci.levantamento(m);
                     finally{l1.unlock();}
                     
                 }
               l2.lock();
               try {cj.deposito(m);
               finally{l2.unlock();}
               }
               
        }    
}

interface Bank{
    int createAccount(float initialBalance){
        
    }
    
    
    float closeAccount(int id) throws InvalidAccount{
        
    }
    void transfer(int from, int to, float amount) throws InvalidAccount, NotEnoughFounds{
        
    }
    float totalBalance(int accounts []){
        
    }
    
}
    

