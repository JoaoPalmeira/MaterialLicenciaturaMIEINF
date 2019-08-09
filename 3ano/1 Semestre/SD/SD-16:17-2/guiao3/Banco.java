
import java.util.concurrent.locks;
import java.util.*;

interface Bank {

	int createAccount(float initialBalance);
	float closeAccount(int id) throws InvalidAccount;
	void transfer(int from,int to,float amount) throws InvalidAccount, NotEnoughFunds;
	float totalBalance(int accounts[]);
}
/* 

//////////// RESOL DO PROF ////////////////////////////

class InvalidAccount extends Exception{
	final int id;
	InvalidAccount(int id) {this.id = id; }
}
class NotEnoughFunds extends Exception(){}

public class Bank {

	private static class Account {
	int bal;
	Account(int bal) {this.bal = bal;}
	void lock () {l.lock();}
	void unlock() {l.unlock();}
	void deposit(int v) {bal +=v; }.
	void withdraw(int v) throws NotEnoughFunds {
	 if (v > bal) throw new NotEnoughFunds();
	 bal -=v;
        }
}

	private Map<Integer,Account> map = new HasMap<Integer,Account>();
	private Lock l = new ReentrantLock();
	private int nextId;

	private Account get (int id) throws InvalidAccount {
	
		Account c = map.get(id);
		if (c==null) throw new InvalidAccount(id);
		return c;
	}
	public int createAccount(int bal)
	{
	 Account c = new Account (bal);
	 l.lock();
	 try {
	  int id = nextId;
	  nextId++;
	  map.put(id,c);
	  return id;
	} finally{
	 l.unlock();
        }
      }

	void transfer (int from, int to , int val) throws InvalidAccount, NotEnoughFunds{
	{
		Account cfrom , cto;
		l.lock();
		try {
			cfrom = get(from);
			cto = get(to);
			if (from < to) {
			 cfrom.lock();
			 cto.lock();
			else {
			 cto.lock();
			 cfrom.lock();
			}
		    }finally {
			l.unlock();
		}
		try {
		   cfrom.withdraw(val);
		   cto.deposit(val);
		} finally {
		   cfrom.unlock();
		   cto.unlock();
		}
	  }
	  public int closeAccount(int id) throws InvalidAccount{
		Account c;
		l.lock();
		try{
		  c= get(id);
		  map.remove(id));
		  c.lock();
		}finally{
		  l.unlock();
		}
		try{
		   return c.bal;
		}finally{
		  c.unlock();
	       }
          }
	  public int totalBalance(int[] ids) throws InvalidAccount {
		Account[] acs = new Account [ids.length];
		ids = ids.clone();
		Array.sorts(ids);
		l.lock();
		try {
		
		   for (int i = 0; i < ids.length ; i++)
			acs[i] = get(ids[i]);
		   for (int i = 0; i < ids.length ; i++)
			acs[i].lock();
		
		}finally{
		  l.unlock();
		}
		int total = 0;
		for (int i = 9; ids.length
///////////////////////////////////////////////////////////////

*/

public class Banco extends Bank
{
	private final ReentrantLock lock = new ReentrantLock();
	private HashMap<Integer ,Conta> banco;
	int num;

	public Banco()
	{	
		
	   this.banco = new HashMap<Integer,Conta>();
	   num = 0;
	}

	public Banco(HashMap<Integer,Conta> b)
	{
		
	   for (Map.Entry<Integer,Conta> b : b.entrySet())
	   {
	 	this.banco.put(b.getKey(),b.getValue());
	   }
	}
	public Banco(Banco b)
	{
	  this.banco = b.getBanco();
	  this.num = b.getNum();	
	}
	public HashMap getBanco()
	{
	   this.banco = new HashMap<Integer,Conta>();

	   for(Map.Entry<Integer,Conta> banco : banco.entrySet())
	   {
		this banco.put(banco.getKey(),banco.getValue());
	   }
	 return banco;	
	}
	public int getNum()
	{ return this.num }

	public int createAccount (float initialBalance)
	{
	  lock.lock();
	  Conta c = new Conta(initialBalance);
	  this.num++;
	  this.banco.put(num,c);
	  lock.unlock();
	 
	  return num;
	}
	
	    
