
/**
 * Escreva a descrição da classe Main2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Main2 {

	public static void main2(String[] args) {
		int NUM_CONTAS = 10;
		Banco b = new Banco(NUM_CONTAS);
		Thread t1=new Thread(new Cliente1(b));
		Thread t2=new Thread(new Cliente2(b));
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Valor Conta 0 é: " + b.consultar(0));
	}
}
