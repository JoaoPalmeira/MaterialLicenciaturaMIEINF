class BoundedBuffer {

	private int arr[];
	int tam;
	int counter = 0;

	public BoundedBuffer(int t) {

		tam = t;
		arr = new arr[t];

	}
	public synchronized void put (int v){

			while (counter == tam)
			{
				try{
						wait();
				   }
				catch (InterruptedException e){};
			}
			arr[tam] = v;
			
			if (conta < tam)
			counteŕ++;
			
			notifyAll();
	}
	public synchronized int get(){

			int var;
			int narr[] = new narr[tam-1];
			int i , j;

			while (counter == 0)
			{	
				try{
						wait();
				   }
				catch (InterruptedException e){};
			}
		
			var = arr[counter-1];
			
			for (i = 0 ; i < tam-1 ; i++)
			 	narr[i] = arr[i];
		
			for (j = 0 ; j < tam ; j++)
				arr[j] = narr[j];			

		

			notifyAll();
			
			return var;
	}

	public void imprime()
	{
		int i;

		for (i = 0 ; i < tam ; i++)
			System.out.println (arr[i]);

	}

public static void main(String args[])
{
	 try {
	 		int i;

	 		Thread t[] = new Thread[2];
	 		BoundedBuffer b = new BoundedBuffer(4);
	 		Exec e = new Exec(b);
	 		
	 		for (i = 0 ; i < 2 ; i++)
	 			t[i] = new Thread(e);

	 		for (i = 0 ; i < 2 ; i++)
	 			t[i].start();

	 		for (i = 0 ; i < 2 ; i++)
	 			t[i].join();

	 	  }
	 	catch(InterruptedException e){};


	 		System.out.println(b.imprime());
	}
}
