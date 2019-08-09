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
		for (int i = 9; ids.length ...
