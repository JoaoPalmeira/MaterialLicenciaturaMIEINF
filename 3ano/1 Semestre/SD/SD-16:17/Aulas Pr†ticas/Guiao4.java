public class BoundedBuffer{

	private final int tamMax;
	private int[] a;
    private int tamAtual;
    private int inicio;
	
    public BoundedBuffer(){
    	


    }



	public synchronized void put(int v){
		while (a.size() == tamMax)
            wait();
        
        notifyAll(); 
	}

	public synchronized int get(){
        while (a.size() == 0)
            wait();
        
        notifyAll();
        return v;
    }
}