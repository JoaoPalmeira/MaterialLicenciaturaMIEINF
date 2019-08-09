import java.util.Random;

class Account 
{
  int slot;

  public Account() { slot=0; }

  public synchronized void put (int a) { slot+=a; }	

  public synchronized void take (int a) { slot-=a; }	 

  public synchronized int query() { return slot; }
}

class Bank
{
  final int slots;
  Account[] av;

  public Bank(int n) 
  { 
    int j;
    slots = n; 
    av=new Account[slots];
    for(j=0; j<slots; j++) { av[j]=new Account(); }
  }

  public void put(int i,int a) { av[i].put(a); }

  public void take(int i,int a) { av[i].take(a); }

  public int query(int i) { return av[i].query(); } 

  public int sumn(int i)
  {
    if (i==slots) return 0;
    synchronized(av[i]) 
    {
      return av[i].query() + sumn(i+1);
    }
  }

  public int slots() { return slots; }

}

class Mover implements Runnable 
{
  Bank b;

  public Mover(Bank banco) { b = banco; }

  public void run() 
  {
    Random rand = new Random();
    int slots=b.slots();
    int f;
    int t, tries;
    for(tries=0; tries<1000000; tries++)
    { 
      f=rand.nextInt(slots); // get one
      while((t=rand.nextInt(slots))==f); // get a distinct other
      if (f < t)
      {
        synchronized (b.av[f]) {
          synchronized (b.av[t]) {
            b.take(f,10);
            b.put(t,10);
          }
	}
      }
      else	
      {
        synchronized (b.av[t]) {
          synchronized (b.av[f]) {
            b.take(f,10);
            b.put(t,10);
          }
        }
      }
    }
  }
}

class Adder implements Runnable 
{
  Bank b;
  public Adder(Bank banco) { b = banco; }
  public void run() 
  {
    int slots=b.slots();
    int sum, i, tries, lsum=0;
    for(tries=0; tries<1000000; tries++)
    { 
      sum=b.sumn(0);
      if (lsum!=sum) System.out.println("Total "+sum);
      lsum=sum;
    }
  }
}



class ExBankTransf5 
{

  public static void main(String[] args) 
  {
    int N = 10; // Number of accounts
    Bank b = new Bank(N);
    Mover m = new Mover(b);
    Adder c = new Adder(b);

    new Thread(m).start();
    new Thread(c).start();
  }

}