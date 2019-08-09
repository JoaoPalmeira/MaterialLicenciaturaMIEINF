
public class Producer2 implements Runnable {
		
	private BoundedBuffer buffer;
	private int tp;
	private int numberOps;

	public Producer2(BoundedBuffer b, int tw, int numberops){
		this.buffer=b;
		this.tp=tw;
		this.numberOps=numberops;
	}
	
	@Override
	public void run() {
		for(int i=0; i<this.numberOps; i++){	
			this.buffer.put(i);
			System.out.println("Producer "+Thread.currentThread().getName()+": PUT_" + i);
			try {
				Thread.sleep(this.tp);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}  
}
