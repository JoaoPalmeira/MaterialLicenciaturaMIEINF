
import java.util.concurrent.locks;

public class Conta
{
	private final ReentrantLock lock = new ReentrantLock();
	private double money;

	public Conta();
	{
	  this.money = 0;
	}
	public Conta (double d)
	{
 	  this.money = d;
	}
	public Conta(Conta c)
	{
	  this.money = c.getMoney();
	}
	public double getMoney()
	{
	  return money;
	}
	public void setMoney(double d)
	{
	  money = d;
	}
}

