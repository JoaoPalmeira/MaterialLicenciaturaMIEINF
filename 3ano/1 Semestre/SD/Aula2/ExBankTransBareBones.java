import java.util.Random;

class Bank{
  int slots;
  int[] av;


  public synchronized void put(int i, int a){
    av[i]+=a;
  }
  public synchronized void take(int i, int a){
    av[i]-=a;
  }
  public synchronized int query(int i){
    return av[i];
  }
  public synchronized int slots(){
    return slots;
  }
}

class Mover implements Runnable 
{
  Bank b;

  public Mover(Bank b) { this.b = b; }

  public void run() 
  {
    Random rand = new Random();
    int slots=b.slots();
    int f;
    int t, tries;
    for(tries=0; tries<1000000; tries++)
    { 
      f=rand.nextInt(slots); // get one
      while((t=rand.nextInt(slots))==f); // get a distinct other. Carefull!
      synchronized (b){
        b.take(f,10);
        b.put(t,10);
      }
    }
  }
}

class Adder implements Runnable 
{
  Bank b;

  public Adder(Bank b) { this.b = b; }

  public void run() 
  {
    int slots=b.slots();
    int sum, i, tries, lsum=0;
    synchronized (b){
      for(tries=0; tries<1000000; tries++)
      { 
        sum=0;
        for (i=0; i<slots; i++)
        {
         sum+=b.query(i);
        }
        if (lsum!=sum) System.out.println("Total "+sum);
        lsum=sum;
      }
    }
  }
}



class ExBankTransf3 
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

