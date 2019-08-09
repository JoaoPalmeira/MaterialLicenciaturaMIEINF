import java.util.Random;

class Mover implements Runnable
{
	Banco b;

	public Mover(Banco b)
	{
		this.b=b;
	}

	public void run()
	{
		Random rand = new Random();
		int slots = b.slots();
		int f;
		int t,tries;

		for(tries=0;tries<1000000;tries++)
		{
			//e code probabilistico, temos uma tiragem com reposição, quanto mais pequeno for o num de slots maior a probabilidade de retirar o mesmo slot
			f=rand.nextInt(slots);//get one
			while((t=rand.nextInt(slots))==f);//get a different one, este while pode nunca terminar,ex: so temos um slot então nunca vamos terminar o while
			//o num de colisoes aumenta a medida que vamos ficando sem slots
			

				b.debito(f,10);
			//}
			//synchronized(b)
			//{
			 b.credito(f,10);
			//}
		}
	}
}

public class Banco
{
	private int slot;
	private double contas[] = new double[2];

	public Banco()
	{
		slot=2;
		for(int i=0;i<slot;i++)
		{
			contas[i]=0;
		}
	}

	public Banco(int st,double[] c)
	{
		slot=st;
		for(int i=0;i<st;i++)
		{
			c[i]=0;
		}
	}

	public Banco(Banco b)
	{
		slot=b.slots();
		contas=b.getC();
	}

	public double consulta(int f)
	{
		return contas[f];
	}

	public int slots()
	{
		return slot;
	}

	public double[] getC()
	{
		return contas;
	}

    public synchronized void debito(int f,double d)
    {

    	if(contas[f]>0)//podemos ter ou não ja que existem saldos negativos
    	{
    	 contas[f] = contas[f]-d;
    	}
    }

    public synchronized void credito(int f,double c)
    {
    	contas[f] = contas[f]+c;
    }

    public double getST()
    {
      double tot=0;

      for(int i=0;i<2;i++)
      {
      	tot += contas[i];
      }

      return tot;
    }
    
    public void transfere(int c1,int c2,double v1)
    {
       contas[c1].debito(c1, v1);
       contas[c2].credito(c2, v1);
    }

    public static void main(String args[])
  {
       try
       {
           int i;
           Banco b = new Banco();
           Mover banco = new Mover(b);
           Thread t[] = new Thread [10];

           for(i=0;i<10;i++)
           {
           	t[i]=new Thread(banco);
           }

           for(i=0;i<10;i++)
           {
           	t[i].start();
           }

           for(i=0;i<10;i++)
           {
           	t[i].join();
           }
          
          System.out.println(b.getST());

       }catch(InterruptedException e){};
}


