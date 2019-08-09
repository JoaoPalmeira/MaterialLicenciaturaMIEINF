public class HelloRunnableSingle implements Runnable{

	public static void run(String args[]){
		System.out.println("Hello from main thread!");
	}

	public static void main(String args[]){
		(new Thread(new HelloRunnableSingle())).start();
	}
}