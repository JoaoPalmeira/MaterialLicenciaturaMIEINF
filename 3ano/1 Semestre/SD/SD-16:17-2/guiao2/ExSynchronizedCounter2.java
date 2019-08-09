class Contador
{
	long i;

	//deveriamos proteger tbm a consulta 
	public long get()
	{
	   return i;
	}

   public void inc()
	{
	   i++;
	}
	//qd acaba a thread escreve na mem e deixa entrar mais uma thread pois libertou o token
}

class Incrementer implements Runnable
{
	Contador c;

	Incrementer(Contador c1)
	{
	  c = c1;
	}

	public void run()
	{
	 for(long i=0;i<1000000;i++)
	 {
	  synchronized (c) //sinc o bloco de codigo, temos de ter cuidado com a referencia a varios objetos ai perdemos a concorrencia, estavamos sempre a fazer log em objs diffs
	  {
	  	c.inc();
	  	//td este code esta protegido pelo sync
	 }
	}
}
}

class ExSynchronizedCounter2
{
	public static void main(String[] args)
	{
	    try
	    {
	    	int i;
	        Contador c;
	        Incrementer worker;
	        Thread t[] = new Thread[10];

	        c = new Contador();
	        worker = new Incrementer(c);

	        for(i=0;i<10;i++)
	        {
	        	t[i] = new Thread(worker);
	        }

	        for(i=0;i<10;i++)
	        {
	        	t[i].start();
	        }

	        for(i=0;i<10;i++)
	        {
	        	t[i].join();
	        }

	    System.out.println(c.get());
	    }catch(InterruptedException e){}

	}
}