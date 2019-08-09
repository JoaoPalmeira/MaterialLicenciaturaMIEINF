
/**
 * Escreva a descrição da classe Main4 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Main4 {

	public static void main(String[] args) {

		Banco3 b = new Banco3(10);	
		Thread t1=new Thread(new Cliente5(b));
		Thread t2=new Thread(new Cliente6(b));
	
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
